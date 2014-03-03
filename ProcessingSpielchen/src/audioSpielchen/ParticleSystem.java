package audioSpielchen;

import java.util.Vector;
import processing.core.*;

/**
 * An ArrayList is used to manage the list of Particles 
 * Taken from: http://processing.org/learning/topics/multipleparticlesystems.html
 * @author Melanie Krauth
 *
 */


class ParticleSystem {
	 
	PApplet app;
	Vector<Particle> particles;    // An arraylist for all the particles <- I changed this to a vector as ArrayList does not exist in Java
	PVector origin;        			// An origin point for where particles are birthed

	ParticleSystem(float num, PVector v, PApplet parent) 
	{
		app = parent;
		particles = new Vector();              // Initialize the arraylist
		origin = v.get();                        // Store the origin point
		for (int i = 0; i < num; i++) {
			// We have a 50% chance of adding each kind of particle
			if (app.random(1) < 0.5) {
				particles.add(new CrazyParticle(origin, app)); 
			} else {
				particles.add(new Particle(origin, app)); 
			}
		}
	}

	void run() {
		// Cycle through the ArrayList backwards b/c we are deleting
		for (int i = particles.size()-1; i >= 0; i--) {
			Particle p = (Particle) particles.get(i);
			p.run();
			if (p.dead()) {
				particles.remove(i);
			}
		}
	}

	void addParticle() {
		particles.add(new Particle(origin, app));
	}

	void addParticle(Particle p) {
		particles.add(p);
	}

	// A method to test if the particle system still has particles
	boolean dead() {
		if (particles.isEmpty()) {
			return true;
		} 
		else {
			return false;
		}
	}

}