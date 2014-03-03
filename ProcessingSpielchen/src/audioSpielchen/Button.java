package audioSpielchen;

import processing.core.*;
/**
 * Base taken from http://processing.org/learning/topics/buttons.html 
 * @author Melanie Krauth
 *
 */

public class Button {
	protected PApplet p;
	protected int x, y;
	protected int size;
	protected int basecolor, highlightcolor;
	protected int currentcolor;
	protected boolean over = false;
	protected boolean pressed = false; 
	protected boolean active;

	public Button(Dialog parent)
	{
		p = parent;
		active = true; 
	}

	public void update() 
	{		
			if(over()) {
				currentcolor = highlightcolor;
			} 
			else {
				currentcolor = basecolor;
			}
	}

	public boolean pressed() 
	{
		if(over) {
			//locked = true;
			return true;
		} 
		else {
			//locked = false;
			return false;
		}    
	}

	/**
	 * Override this
	 * @return
	 */
	protected boolean over() 
	{ 
		return true; 
	}

	protected boolean overRect(int x, int y, int width, int height) 
	{
		if (p.mouseX >= x && p.mouseX <= x+width && 
				p.mouseY >= y && p.mouseY <= y+height) {
			return true;
		} 
		else {
			return false;
		}
	}
}
