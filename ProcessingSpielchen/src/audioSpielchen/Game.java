package audioSpielchen;

import processing.core.*;
/**
 * The game
 * @author Melanie Krauth
 *
 */

public class Game extends PApplet{

	private float rectH = 30;

	private Obstacle[] rects = new Obstacle[4];
	private float energy = 100;
	private float x;
	private float y;
	private float xSpeed;
	private float ySpeed; 

	private float rad;
	private float maxDim = 0;
	private int upBand = 7;
	private int downBand = 5;
	private int currentBand;
	private int strk = 150;
	private boolean pTrigger = true;
	private int col_val = 0; 

	private AudioAnalysis analysisObject; 
	private ParticleSystem psys = null;

	//for collision

	private static final int COL_DOWN = 1;
	private static final int COL_FRONT = 2;
	private static final int COL_UP = 3;


	/**
	 * Default Processing loop, setup sketch, load all objects
	 */
	public void setup () {

		size (620, 480, P2D);
		smooth();
		stroke (strk,strk,strk);

		rects = new Obstacle[6];

		//Setup Obstacles - this could be read from an XML file
		rects[0] = new Obstacle(120, 240, 200, 30, this);
		rects[1] = new Obstacle(320, 80, 200, 30, this);

		rects[2] = new Obstacle(490, 80, 30, 100, this);
		rects[3] = new Obstacle(290, 170, 30, 100, this);

		rects[4] = new Obstacle(50, 350, 100, 30, this);
		rects[5] = new Obstacle(120, 270, 30, 100, this);

		noFill ();

		// Startposition
		x = 0;
		y = 200;
		xSpeed = 0.3f;
		ySpeed = 0.3f; 

		//Get Audio Object
		analysisObject = AudioAnalysis.getInstance(this);
		if(!analysisObject.available())
			System.exit(-1); 

		//setup font
		textMode(SCREEN);  
		textFont(createFont("Arial", 10));
		//background (0);
	}
	/**
	 * Default Processing loop, like the "game loop"
	 */
	public void draw () {

		background (60,60,60);
		smooth();

		fill(150+rectH*3,rectH*5,200-rectH*2);

		float dim = analysisObject.getVol();	

		if(dim > maxDim)
			maxDim = dim;

		text("Dim: "+Math.log(dim), width-80, 20);

		for(int i = 0; i < 6; i++){
			rects[i].drawObj();
		}
		analysisObject.run();
		currentBand = analysisObject.getMaxIndex();

		//Here its decided whether the ball goes up or down
		if(currentBand >= upBand)
			y -= ySpeed;
		else if(currentBand <= downBand && currentBand > 2)
			y += ySpeed;

		fill(255);
		text("Band: "+currentBand,5, 20); 

		// Draw circle
		if(y < 0)
			y = 0;
		if(y > 480)
			y = 480;		

		//make sure it cant get negative 
		x+=Math.abs(xSpeed*(Math.log(dim)));

		text("speed: "+xSpeed*(Math.log(dim)), width/2, 20);

		//make sure it wont turn into a negative value
		rad = Math.abs((float)Math.log(dim/2)*8);    

		if(rad < 10)
			rad = 10;
		if ( rad > 45)
			rad = 44;

		// Collision - move back if collision is detected to make sure youre not moving over obstacles 
		// something is wrong here though..

		col_val = collide(rects);
		if(col_val != 0){

			if(energy > 0)
				energy -= 1;

			//trigger particle System if energy is gone
			else if(energy <=0 && pTrigger)
			{				
				psys = new ParticleSystem(random(5,25),new PVector(x,y), this); 
				pTrigger = false;
			}
			//continue oder irgendsowas hierhin

			fill(255,0,0);

			if(col_val == COL_FRONT)
				x-=Math.abs(xSpeed*(Math.log(dim)));

			if(col_val == COL_UP)
				//y -=0.5f;
				y +=ySpeed;

			else if(col_val == COL_DOWN)
				//y -= 0.5f;
				y -= ySpeed;
		}	

		//Draw the ball in different colors depending on its direction
		else if(currentBand == 7 || currentBand < 4)
		{
			stroke(40,180,40);
			fill(50,200,50);
		}
		else if (currentBand < 7 && currentBand > 4){
			stroke(80, 40 ,80);
			fill(100,50 ,100);
		}
		else
		{
			stroke(240, 150, 0);
			fill(255, 165, 0);
		}

		if(energy > 0)
			ellipse (x, y, rad, rad);

		if(psys != null)
			psys.run();

		fill(255);
		text("energy: "+energy, 180, 20);

		//back to the left side if the right is reached	
		//actually the next xml file containing the positions of the obstacles should be loaded	
		if (x > width) {
			x = 0;
			y += maxDim/4;
			if(y > height)
				y = 200;
		}
	}

	/**
	 * Check if the ball collides with any of the obstacles
	 * @param obj
	 * @return
	 */
	private int collide(Obstacle[] obj){

		float tmp = rad*0.7f;

		for(int i = 0; i < obj.length; i++){

			if((x+tmp > obj[i].x && x+tmp < obj[i].x+obj[i].w)|| (x-tmp > obj[i].x && x-tmp < obj[i].x+obj[i].w)
			/*||(x > obj[i].x && x < obj[i].x+obj[i].w)*/)
			{
				//println("collision x");

				//case 3
				if(y > obj[i].y && y < obj[i].y+obj[i].h){
					//println("collision front");					
					return COL_FRONT;
				}

				//case 1
				else if(y+tmp < obj[i].y+obj[i].h && y+tmp > obj[i].y){
					//println("collision bottom");
					return COL_DOWN;
				}
				//case 2
				else if(y-tmp > obj[i].y && y-tmp < obj[i].y+obj[i].h){
					//println("collision collision top");
					return COL_UP;
				}							
			}
		}
		return 0;
	}

}