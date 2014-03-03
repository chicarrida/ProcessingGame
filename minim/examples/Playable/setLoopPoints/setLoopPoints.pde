/** 
  * This sketch demonstrates how to use <code>setLoopPoints</code>
  * using an <code>AudioSnippet</code>. Left-click with the mouse
  * to set the start point of the loop and right-click to set the
  * end point of the loop. <code>setLoopPoints</code> can also be
  * called on an <code>AudioPlayer</code>, but you will likely find
  * there to be a break during loops while the code seeks from the 
  * beginning of the file to the start of the loop. This seek time
  * can become quite noticable if you are using an mp3 file 
  * because it will need to decode as it seeks.
  */

import ddf.minim.*;

Minim minim;
AudioSnippet snip;

int loopBegin;
int loopEnd;

void setup()
{
  size(512, 200, P3D);
  textMode(SCREEN);
  minim = new Minim(this);
  snip = minim.loadSnippet("groove.mp3");
  snip.loop();
  
  textFont(loadFont("ArialMT-14.vlw"));
}

void draw()
{
  background(0);
  fill(255);  
  text("Loop Count: " + snip.loopCount(), 5, 20);
  text("Looping: " + snip.isLooping(), 5, 40);
  text("Playing: " + snip.isPlaying(), 5, 60);
  int p = snip.position();
  int l = snip.length();
  text("Position: " + p, 5, 80);
  text("Length: " + l, 5, 100);
  float x = map(p, 0, l, 0, width);
  stroke(255);
  line(x, height/2 - 50, x, height/2 + 50);
  float lbx = map(loopBegin, 0, snip.length(), 0, width);
  float lex = map(loopEnd, 0, snip.length(), 0, width);
  stroke(0, 255, 0);
  line(lbx, 0, lbx, height);
  stroke(255, 0, 0);
  line(lex, 0, lex, height);
}

void mousePressed()
{
  int ms = (int)map(mouseX, 0, width, 0, snip.length());
  if ( mouseButton == RIGHT )
  {
    snip.setLoopPoints(loopBegin, ms);
    loopEnd = ms;
  }
  else
  {
    snip.setLoopPoints(ms, loopEnd);
    loopBegin = ms;
  }
}

void stop()
{
  // always close Minim audio classes when you are done with them
  snip.close();
  minim.stop();
  
  super.stop();
}
