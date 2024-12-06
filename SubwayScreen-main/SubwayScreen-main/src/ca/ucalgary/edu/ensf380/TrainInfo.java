package ca.ucalgary.edu.ensf380;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


/**This class loads train and station information from the subway.csv file of the newest file in the out folder
 * it then draws them and displays. 
 * 
 * @version 1.0
 * @since 2024-07-20
 * */
public class TrainInfo extends JPanel {
	private Map<String, Point> stationLocations;
	private Map<String, String> stationNames;
	private Map<String, String[]> trainRoutes;
	private String currentTrainId;
	private String[] currentRoute;
	private static final String OUT_FOLDER_PATH = "subwayscreen-main/out";
	private Timer timer;

    /**Class constructor initializes the variables stationLocations, stationNames, and trainRoutes,
     *  and checks for new files every 0.5 seconds*/
    public TrainInfo() {
        stationLocations = new HashMap<>();
        stationNames = new HashMap<>();
        trainRoutes = new HashMap<>();
        currentTrainId = null;
        currentRoute = new String[0];
        setPreferredSize(new Dimension(1200, 800));

        try {
            loadStationLocations(new File("SubwayScreen-main/data/subway.csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        clearOutFolder();

        
        try {
            loadInitialTrainRoutes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                checkForNewFiles();
            }
        }, 0, 500); 

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updatePreferredSize();
            }
        });
    }

    /**Updates the preferred size of the panel to match the current size*/
    public void updatePreferredSize() {
        setPreferredSize(new Dimension(getWidth(), getHeight()));
        revalidate();
    }

    /**clears all files from the Out folder to prevent reading old files*/ 
    public void clearOutFolder() {
        File directory = new File(OUT_FOLDER_PATH);
        File[] files = directory.listFiles(File::isFile);
        if (files != null) {
            for (File file : files) {
                file.delete();
            }
        }
    }
    
    /**Loads the station Locations from within the subway.csv file
     * @param dataFile subway.csv file where all station information is
     * @throws IOException if an error during file I/O occurs
     * */
    public void loadStationLocations(File dataFile) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(dataFile))) {
            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] fields = line.split(",");
                if (fields.length >= 7) {
                    String stationCode = fields[3].trim();
                    int x = (int) Double.parseDouble(fields[5].trim());
                    int y = (int) Double.parseDouble(fields[6].trim());
                    String stationName = fields[4].trim();

                    stationLocations.put(stationCode, new Point(x, y));
                    stationNames.put(stationCode, stationName);
                }
            }
        }

        repaint(); 
    }

    /**loads the initial train Routes from the most recent file
     * @throws IOException if a file I/O error occurs
     * */
    public void loadInitialTrainRoutes() throws IOException {
        File newestFile = getLastModified(OUT_FOLDER_PATH);
        if (newestFile != null) {
            loadTrainRoutes(newestFile);
        }
    }
    
    /**setter for train routes and repaints panel with the new data
     * @param trainRoutes a hashmap of a String and String list that reprsentes line name and station names respectively 
     * */
    public void setTrainRoutes(Map<String, String[]> trainRoutes) {
        this.trainRoutes = trainRoutes;
        repaint(); 
    }

    /**loads train routes from a file
     * @param newestFile the filepath to load train route info from
     * @throws IOException if there is an error opening a file
     * */
    public void loadTrainRoutes(File newestFile) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(newestFile))) {
            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] fields = line.split(",");
                if (fields.length >= 5) {
                    String trainId = fields[1].trim();
                    String currentStationCode = fields[2].trim();
                    String destination = fields[4].trim();
                    trainRoutes.put(trainId, new String[]{currentStationCode, destination});
                }
            }
        }

        repaint(); // Ensure trains are displayed immediately
    }

    /**get the file that was last modified inside the OUt folder
     * @param directoryFilePath the directory of the file
     * @return chosenFile which is thhe last modified file
     * */
    public File getLastModified(String directoryFilePath) {
        File directory = new File(directoryFilePath);
        File[] files = directory.listFiles(File::isFile);
        long lastModifiedTime = Long.MIN_VALUE;
        File chosenFile = null;

        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.lastModified() > lastModifiedTime) {
                    chosenFile = file;
                    lastModifiedTime = file.lastModified();
                }
            }
        }

        return chosenFile;
    }

    /**Checks for new files every 0.5 seconds*/
    public void checkForNewFiles() {
        File newestFile = getLastModified(OUT_FOLDER_PATH);
        if (newestFile != null) {
            try {
                loadTrainRoutes(newestFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**Paints the Station and Train components
     * @param g Graphics object for painting*/
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawStations(g);
        drawTrains(g);
    }

    /**draw the stations using coordinates as black dots
     * @param g graphics object for painting*/
    public void drawStations(Graphics g) {
        int dotSize = 6;
        g.setColor(Color.BLACK);

        for (Map.Entry<String, Point> entry : stationLocations.entrySet()) {
            String stationCode = entry.getKey();
            Point location = entry.getValue();

            if (getWidth() > 0 && getHeight() > 0) {
                int x = (int) ((double) location.x / 1000 * getWidth());
                int y = (int) ((double) location.y / 1000 * getHeight());
                g.fillOval(x - dotSize / 2, y - dotSize / 2, dotSize, dotSize);
            }
        }
    }

    /**draw trains (currently moving trains) as red dots and shows the station names next to the marker
     * @param g graphics object for painting
     * */
    public void drawTrains(Graphics g) {
        for (Map.Entry<String, String[]> entry : trainRoutes.entrySet()) {
            String trainId = entry.getKey();
            String[] route = entry.getValue();
            String currentStationCode = route[0];
            Point currentLocation = stationLocations.get(currentStationCode);

            if (currentLocation != null) {
                int x = (int) ((double) currentLocation.x / 1000 * getWidth());
                int y = (int) ((double) currentLocation.y / 1000 * getHeight());

                g.setColor(Color.RED);
                g.fillOval(x - 10, y - 10, 20, 20);

                // Display the station name
                String stationName = stationNames.get(currentStationCode);
                if (stationName != null) {
                    g.drawString(stationName, x + 15, y);
                }
            }
        }
    }
}
