package ca.ucalgary.edu.ensf380;

/**
 * An interface for classes that need to provide a display functionality.
 * Implementing classes should define how they are displayed.
 * 
 * @version 1.0
 * @since 2024-07-20
 */

interface Displayable {
	/**
     * Displays the content of the implementing class.
     * Classes that use the display function will define the function
     */
	public void display();
}
