package audioSpielchen;

import processing.core.*;
/**
 * A simple Particle class taken from: http://processing.org/learning/topics/multipleparticlesystems.html
 * @author Melanie Krauth
 *
 */

//
public class Particle {
	PVector loc;
	PVector vel;
	PVector acc;
	float r;
	float timer;
	
	PApplet app;

	// One constructor
	Particle(PVector a, PVector v, PVector l, float r_, PApplet parent) {
		app = parent;
		acc = a.get();
		vel = v.get();
		loc = l.get();
		r = r_;
		timer = 100.0f;
	}

	// Another constructor (the one we are using here)
	Particle(PVector l, PApplet parent) {
		app = parent;
		acc = new PVector(0,0.05f,0);
		vel = new PVector(app.random(-1,1),app.random(-2,0),0);
		loc = l.get();
		r = 6.0f;
		timer = 100.0f;
	}


	void run() {
		update();
		render();
	}

	// Method to update location
	void update() {
		vel.add(acc);
		loc.add(vel);
		timer -= 0.5;
	}

	// Method to display
	void render() {
		app.ellipseMode(app.CENTER);
		app.stroke(255,timer);
		app.fill(150,timer);
		app.ellipse(loc.x,loc.y,r,r);
	}

	// Is the particle still useful?
	boolean dead() {
		if (timer <= 0.0) {
			return true;
		} else {
			return false;
		}
	}
}
