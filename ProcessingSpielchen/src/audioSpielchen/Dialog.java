package audioSpielchen;

import processing.core.*;
/**
 * The dialog to determine the frequency bands used for moving the ball up or down
 * @author Melanie Krauth
 *
 */

public class Dialog extends PApplet implements LabelText {

	//to hold the band number
	private int up;
	private int down;
	private int sum; 
	private int counter; 	
	private int index; 
	private int maxBand; 
	
	private int color;	
	//set to true when start button is pressed - set to false after pressing stop button  
	private boolean analyse;

	private float[] baender;

	private AudioAnalysis analyseObject;
	private RectButton playButton, stopButton, setUpButton, setDownButton;
	private TextLabel label;
	private boolean done = false; 
	private boolean setUp = true; 
	private boolean setDown = false; 
	private boolean playTextSet = false; 

	/**
	 * Default Processing Loop
	 */
	public void setup(){
		up = -1;
		down = -1;
		index = -1; 
		size(600,400);
		color = 0;
		background(color);

		//Initialize Buttons
		//startButton = new RectButton(44, 330, 50,80, 0, 255, this, "start");
		setUpButton = new RectButton(44, 330, 50,80, 0, 255, this, "Set up val");
		setDownButton = new RectButton(188, 330, 50,80, 0, 255, this, "Set down val");
		
		stopButton = new RectButton(332, 330, 50,80, 0, 255, this, "Stop");
		playButton = new RectButton(476, 330, 50,80, 0, 255, this, "play");
		

		playButton.setActive(true);

		label = new TextLabel(43,15,513,100,this, "");

		//Initialize Audio Object and check if Microphone is available
		analyseObject = AudioAnalysis.getInstance(this);
		if(!analyseObject.available())
			label.setText(NO_MIC);
		else
			label.setText(INFO_TEXT);

		analyse = false;
	}

	/**
	 * Default Processing loop
	 */
	public void draw()
	{
		//Draw all the stuff
		background(color);
		playButton.display();		
		stopButton.display();
		setUpButton.display();
		setDownButton.display();

		label.display();

		//check if any button is pressed
		update(mouseX, mouseY);
	}

	//
	public boolean isDone()
	{
		return done; 
	}

	/**
	 * Check if any button is pressed 
	 * @param x x-position of cursor
	 * @param y y-position of cursor
	 */
	private void update(int x, int y)
	{	
		//update buttons
		playButton.update();			
		stopButton.update();
		setUpButton.update();
		setDownButton.update();

		//Do analysis and draw spectrum 
		if(analyse)
		{
			if(analyseObject.available())
			{
				analyseObject.run();								
				baender = analyseObject.getBaender();
				maxBand = analyseObject.getMaxIndex();
				sum += maxBand;
				counter ++;
					
				drawSpectrum();
			}
			else
				label.setText(NO_MIC);
		}
		
		//Button Events if mouse Pressed
		if(mousePressed) {

			if(playButton.pressed()) 
			{
				done = true;					
			} 

			//stop analysis and make button active again
			else if (stopButton.pressed())
			{				
				if(analyse)
					stopAnalysis();
				//check if a band was determined and which one is already set
				if(index != -1){

					if(setUp)
					{
						up = sum/counter;
						System.out.println(sum/counter);
						System.out.println("index: "+index);
						label.setText(UP_SET+up+"."+SET_INFO);
						setUpButton.setActive(false);
					}
					else if (setDown)
					{
						if(up != index)
						{
							down = sum/counter;
							System.out.println(sum/counter);
							//System.out.println("index: "+index);
							label.setText(DOWN_SET+down+"."+SET_INFO);
							setDownButton.setActive(false);
						}
						else
							label.setText(UP_EQUALS_DOWN);
					}									
				}else 
					label.setText(NO_BAND);
			}
			else if(setUpButton.pressed())
			{
				//reset the Value
				//up = -1;  
				setUp = true;
				setDown = false;
				label.setText(INFO_TEXT_UP);
				if(!analyse)
				{
					analyse = true;
					setUpButton.setActive(true);
					counter = 0; 
					sum = 0; 
				}						
			}
			else if(setDownButton.pressed())
			{	//reset the Value
				//down = -1; 
				setDown = true; 
				setUp = false; 
				label.setText(INFO_TEXT_DOWN);
				
				if(!analyse)
				{
					analyse = true;
					setDownButton.setActive(true);
					counter = 0; 
					sum = 0; 
				}		
			}
		}
		
		if(up != -1 && down != -1)
		{
			playButton.setActive(false);			
			if(!playTextSet)
			{
				label.appendText("\n"+BOTH_SET);
				playTextSet = true; 
			}
		}		
	}

	/**
	 * 
	 */
	private void stopAnalysis() {
		analyse = false;
		//startButton.setActive(false); 
		if(analyseObject.available())
			index = analyseObject.getMaxIndex();
	}

	/**
	 * Draw the frequency spectrum
	 * @param baender
	 */
	private void drawSpectrum()
	{	
		int startingX = 43;
		int y1 = 310;
		float y2;
		int yMax = 130;
		
		stroke(220);
		
		for(int i = 0; i < baender.length; i++)
		{	//clip
			if(i == maxBand)
				fill(255,0,0);
			else fill(180);
			
			y2 = y1-baender[i];
			if(y2 < yMax)
				y2 = yMax;
			
			rect(startingX+(i)*27, y2, 27, y1-y2 );
				
		}
	}

}	