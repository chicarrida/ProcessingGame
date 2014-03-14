package audioSpielchen;

import ddf.minim.*;
import ddf.minim.analysis.*;
import processing.core.*;

/**
 * Singleton class that does the Audio analysis
 * @author Melanie Krauth
 *
 */
public class AudioAnalysis {

	private static AudioAnalysis instance = null; 

	private PApplet p;
	private Minim minim;
	private AudioInput input;
	private FFT fft;
	private int index; 
	private float[] baender;
	private boolean mic = false; 

	/**
	 * 
	 * @param parent
	 */
	private AudioAnalysis(PApplet parent)
	{
		p = parent;
		minim = new Minim(p);
		try
		{
			input = minim.getLineIn();			
			fft = new FFT(input.bufferSize(), input.sampleRate());
			baender = new float[fft.specSize()];
			mic = true; 
		}catch(Exception e)
		{
			System.out.println("Missing microphone - something needs to be done in that case..."); 
		}
	}

	/**
	 * 
	 * @param parent
	 * @return
	 */
	public static AudioAnalysis getInstance(PApplet parent)
	{
		if(instance == null)
			instance = new AudioAnalysis(parent);
		else 
			instance.setParent(parent);

		return instance;
	}
	
	/**
	 * 	Does a forward FFT and stores the results in a float[]
	 */
	public void run(){
		//System.out.println("Spectrum Size: "+fft.specSize());
		fft.forward(input.mix);
		index = getMaxBand();		
	}	

	/**
	 * Return the volume value
	 */
	public float getVol()
	{		
		return input.mix.level () * p.width;
	}

	/**
	 * Returns the amplitude values for all bands
	 * @return
	 */
	public float[] getBaender()
	{
		return baender;
	}

	/**
	 * Get the index of the most present frequency band 
	 * @return
	 */		
	public int getMaxIndex()
	{
		return index; 
	}
	
	/**
	 * Returns false if no microphone could be found or any other error occured
	 * @return
	 */
	public boolean available()
	{		
		return mic; 
	}
	
	/**
	 * 
	 */
	public void finalize()
	{
		minim.stop();
	}

	/**
	 * 
	 * @param parent
	 */
	private void setParent(PApplet parent) 
	{
		p = parent;		
	}



	/**
	 * 
	 * @return
	 */
	private int getMaxBand(){		  

		int mx = -1;
		float cmpVal = 0;
		//skip 0 - it always seems to have the highest value...
		for(int i = 1; i < fft.specSize(); i++)
			//for(int i = 0; i < 25/*fft.specSize()*/; i++)
		{
			baender[i] = fft.getBand(i); 
			//get the band with the highest Amplitude
			if(fft.getBand(i)>cmpVal){
				mx = i;
				cmpVal = fft.getBand(i);
			}
		}
		return mx;
	}
}


