package audioSpielchen;

import java.awt.Color;
/**
 * Frame that displays the Processing sketch
 * example taken from: 
 * http://www.sebastianoliva.com/en/en/2010/05/using-a-processing-sketch-as-a-java-component/trackback/index.html
 * @author Melanie Krauth
 *
 */

public class DisplayFrame extends javax.swing.JFrame {
		
	
	javax.swing.JPanel panel ;
	Dialog dialog;
	Game game; 
	public DisplayFrame()
	{
		this.setSize(630, 450);
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		panel = new javax.swing.JPanel();
		panel.setBounds(10, 10, 630, 450);
		panel.setBackground(Color.black);        
		dialog = new Dialog();
		panel.add(dialog);
		this.add(panel);
		dialog.init(); //this is the function used to start the execution of the sketch
		this.setVisible(true);	
		
		// Test if dialog is done
		while(!dialog.isDone()){
			try {
				Thread.sleep(500);			

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// Start game if dialog is done
		this.remove(panel);
		panel.remove(dialog);
		game = new Game(); 
		
		this.setSize(650,500);
		panel.setBounds(0,0,650,500);
		panel.add(game);
		this.add(game);
		game.init();
	}

}
