package ca.ucalgary.edu.ensf380;

/** 
 * Advertisement is a class that instantiates the advertisements 
 * present in the database using the given file path
 * 
 * @version 1.0
 * @since 2024-07-20
 */
public class Advertisement {
	private String filePath;
	private static final int DISPLAYDURATION = 10;
	
	 /**
     * Constructs an Advertisement with the specified filepath
     *
     * @param filePath the type of media for the advertisement
     */
	
	public Advertisement(String filePath) {
		this.filePath = filePath;
	}
	
    /**
     * Setting the filePath of the advertisement.
     *
     * @param filePath the new filePath
     */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	/**
     * Retrieving the file path of an advertisement.
     *
     * @return the file path
     */
	public String getFilePath() {
		return filePath;
	}
	
	 /**
     * Retrieving the display duration of an advertisement.
     *
     * @return the display duration in seconds
     */
	public int getDisplayDuration() {
		return DISPLAYDURATION;
	}
}
