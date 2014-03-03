package audioSpielchen;

import processing.core.*;

/**
 * Simple class to hold text
 * @author Melanie Krauth
 *
 */
public class TextLabel {
	
	PApplet p; 
	String t;
	int x,y,w,h;
	
	/**
	 * 
	 * @param ix
	 * @param iy
	 * @param iwidth
	 * @param iheight
	 * @param parent
	 * @param text
	 */
	TextLabel(int ix, int iy, int iwidth, int iheight, PApplet parent, String text)
	{
		p = parent;
		t = text;
		x = ix; 
		y = iy;
		w = iwidth;
		h = iheight;
	}
	
	/**
	 * Set new text
	 * @param s
	 */
	public void setText(String s)
	{
		t = s;
	}
	
	public void appendText(String s)
	{
		t+=s;
		System.out.println(t);
	}
	
	/**
	 * Draw the label
	 */
	public void display()
	{
		p.stroke(150);
		p.rect(x, y, w, h);
		p.fill(255);
		
		p.textAlign(p.CORNER);
		p.text(t,x+5, y+5,w-10, h-10);
		
	}
}