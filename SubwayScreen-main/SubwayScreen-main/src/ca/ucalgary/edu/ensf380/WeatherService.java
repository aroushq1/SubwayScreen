package ca.ucalgary.edu.ensf380;

import java.io.IOException;



/**
 * Provides the data collected by the WeatherParser class to the GUI
 * 
 * @version 1.0
 * @since 2024-07-20
 * */
public class WeatherService {
    private String temp;
    private String windGust;
    private String rain;
    private WeatherParser weatherParser;

    /**Instantiates a weatherService object using a weatherParser object
     * @param weatherParser takes in a weatherParser Object
     * */
    // Constructor
    public WeatherService(WeatherParser weatherParser) {
        this.weatherParser = weatherParser;
        updateWeatherData();
    }
    
    /**Initializes all the variable in WeatherService with the correct data retrieved in weatherParser class
     * */
    // Fetch and update weather data
    public void updateWeatherData() {
        try {
            weatherParser.fetchWeatherData();
            this.temp = weatherParser.dailyTemp();
            this.windGust = weatherParser.dailyWind();
            this.rain = weatherParser.dailyRain();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**Gets the daily Temperature
     * @return the Temperature as a String
     * */
    public String getDailyTemperature() {
        return temp;
    }
    
    /**Gets the daily wind gust
     * @return the windgust as a String*/
    public String getDailyWind() {
        return windGust;
    }
    
    /**Gets the daily Rain in mm
     * @return the Rain as a String
     * */
    public String getDailyRain() {
        return rain;
    }
    
}
