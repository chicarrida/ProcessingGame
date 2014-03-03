package audioSpielchen;

import processing.core.*;

/**
 * Simple Class that just holds a rectangle 
 * @author Melanie Krauth
 *
 */
public class Obstacle {

  float x,y,w,h;
  PApplet p;
  
  /**
   * 
   * @param ix
   * @param iy
   * @param iw
   * @param ih
   * @param parent
   */
  Obstacle(float ix, float iy, float iw, float ih, PApplet parent)
  {
	  x = ix;
	  y = iy;
	  w = iw;
	  h = ih;
	  p = parent; 
  }
  
  /**
   * Draw the object
   */
  void drawObj()
  {
    p.rect(x, y, w, h);
  }

}
