package audioSpielchen;

import processing.core.*;

/**
 * Rectangle Button with text, rollover and active modus
 * Base taken from http://processing.org/learning/topics/buttons.html
 * @author Melanie Krauth
 *
 */

public class RectButton extends Button{
	protected String t;
	protected PFont font;	
	protected int width, height, textColor;

	/**
	 * 
	 * @param ix
	 * @param iy
	 * @param iheight
	 * @param iwidth
	 * @param icolor
	 * @param ihighlight
	 * @param p
	 * @param text
	 */
	public RectButton(int ix, int iy, int iheight, int iwidth, int icolor, int ihighlight, Dialog p, String text) 
	{
		super(p);
		t = text;
		x = ix;
		y = iy;
		width = iwidth;
		height = iheight;		
		basecolor = icolor;
		highlightcolor = ihighlight;
		currentcolor = basecolor;	    
		//Font needs to be in the workspace/project/bin/data-folder
		font = p.loadFont("Arial-Black-20.vlw");
		textColor = 200;		
	}

	/**
	 * Change colors if mouse is hovering over rectangle
	 */
	public boolean over() 
	{		
		if( overRect(x, y, width, height)) {
			over = true;
			textColor = 50;
			return true;
		} 
		else {
			over = false;
			textColor = 200; 
			return false;
		}				
	}

	/**
	 * Toggle active mode
	 * @param b
	 */
	public void setActive(boolean b)
	{
		active = b;
		if(active){
			basecolor = 150;
			highlightcolor = 150;
		}
		else 
		{
			basecolor = 0;
			highlightcolor = 255;
		}			
	}

	/**
	 * Draws the Button
	 */
	public void display() 
	{
		//update();
		p.stroke(255);
		p.fill(currentcolor);
		p.rect(x, y, width, height);

		//display text 
		p.fill(textColor);
		p.textAlign(p.CENTER);
		p.text(t,(x +width/2), y + (height -height/3)); 
		p.fill(0);
	}
}
