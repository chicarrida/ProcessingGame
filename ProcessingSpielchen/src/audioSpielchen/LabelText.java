//package audioSpielchen;
///**
// * Text to be displayed in the label 
// * @author Melanie Krauth
// *
// */
//
//public interface LabelText {
//	
//	public final static String INFO_TEXT = "Hello there,\n this is a teeny tiny prototype of a game where the figure is controlled using sound. First we need to determine the used frequency bands for the up and down movement of your figure. Choose wich sound should be set first. ";
//	
//	public final static String INFO_TEXT_UP = "Make the sound you want to use to move up. A high frequency is good, for example the letter i. Press stop when you're done.";
//	
//	public final static String INFO_TEXT_DOWN = "Make the sound you want to use to move down. A low frequency is good, for example the letter a. Press stop when you're done.";
//		
//	public final static String NO_MIC = "Error: A microphone is needed but its seems to be missing. :-("; 	
//	
//	public final static String UP_EQUALS_DOWN = "The sounds for up and down seem to be the same. Please try again.";
//
//	public final static String  NO_BAND = "Sorry, something went wrong. Please try again.";
//	
//	public final static String UP_SET = "The Value to control the upward motion of your figure is now set to "; 
//	
//	public final static String SET_INFO = "\nYou can overwrite the value or choose the next control sound";
//	
//	public final static String DOWN_SET = "The Value to control the downward motion of your figure is now set to ";
//	
//	public final static String BOTH_SET = "Both Values are now set. Let the game begin.";
//	
//}

package audioSpielchen;
/**
 * Text to be displayed in the label 
 * @author Melanie Krauth
 *
 */

public interface LabelText {
	
	public final static String INFO_TEXT = "Hello there,\n this is a teeny tiny prototype of a game where the figure is controlled using sound. First we need to determine the used frequency bands for the up and down movement of your figure. Choose wich sound should be set first. ";
	
	public final static String INFO_TEXT_UP = "Make the sound you want to use to move up. A high frequency is good, for example the letter i. Press stop when you're done.";
	
	public final static String INFO_TEXT_DOWN = "Make the sound you want to use to move down. A low frequency is good, for example the letter a. Press stop when you're done.";
		
	public final static String NO_MIC = "Error: A microphone is needed but its seems to be missing. :-("; 	
	
	public final static String UP_EQUALS_DOWN = "The sounds for up and down seem to be the same. Please try again.";

	public final static String  NO_BAND = "Sorry, something went wrong. Please try again.";
	
	public final static String UP_SET = "The Value to control the upward motion of your figure is now set to "; 
	
	public final static String SET_INFO = "\nYou can overwrite the value or choose the next control sound";
	
	public final static String DOWN_SET = "The Value to control the downward motion of your figure is now set to ";
	
	public final static String BOTH_SET = "Both Values are now set. Let the game begin.";
	
}

