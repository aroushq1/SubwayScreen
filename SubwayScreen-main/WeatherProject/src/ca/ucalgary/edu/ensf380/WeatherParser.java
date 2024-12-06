package ca.ucalgary.edu.ensf380;

import java.io.IOException;
import java.util.regex.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;

/**
 * The WeatherParser class fetches content from https://wttr.in and parses it to get the daily temp, rain, wind 
 * */
public class WeatherParser {
	
	private String url;
	private String text;
	
	/**The constructor takes in a cityName and uses it to generate the Daily forecast for that city
	 * @param cityName the cityName entered as a command line argument
	 * */
	// Constructor to initialize URL with city name
    public WeatherParser(String cityName) {
        this.url = "https://wttr.in/" + cityName;
    }
	
    /**
     * Responsible for retrieving the URL
     * @return returns the URL as a string
     * */
	private String getUrl() {
		return this.url;
	}
	
	/**fetches the weather data from the URL and converts it all to text
	 * @return the document converted to text as a String
	 * */
	//load the weather data
	public void fetchWeatherData() throws IOException {
        // Load the document from the URL
        Document doc = Jsoup.connect(getUrl()).get();
        
        //convert the entire document to text and put it into a String to easily find any matches
        String text = doc.text();
        
        //set the private text variable to the document converted to text
        this.text = text;
    }

	
	/**Uses a regex pattern to find the daily Expected rain
	 * @return the amount of rain as a String
	 * */
	public String dailyRain() {
		//regex pattern to find the expected rain in all possible formats
        Pattern rainPattern = Pattern.compile("\\d{1,2}[.]\\d{1,2} mm");
        Matcher rainMatcher = rainPattern.matcher(this.text);
        String dailyRain = "";
        
        //return the first match result since that is the daily one
        if (rainMatcher.find()) {
        	dailyRain =  rainMatcher.group();
        }
        
       return dailyRain;
	}
	
	/**Uses a regex pattern to find the daily Expected wind in km/hr
	 * @return the amount of wind Gust as a String
	 * */
	public String dailyWind() {
		//regex pattern to find the wind/hr in all possible formats (2 km/hr, 12-13 hm/hr)
        Pattern windPattern = Pattern.compile("\\d{1,2} km/h|\\d{1,2}[-]\\d{1,2} km/h");
        Matcher windMatcher = windPattern.matcher(this.text);
        String dailyWind = "";
        
        //return the first match result since that is the daily one
        if (windMatcher.find()) {
        	dailyWind = windMatcher.group();
        }
        
        return dailyWind;
	}
	
	/**Uses a regex pattern to find the daily temperature
	 * @return the temperature as a String
	 * */
	public String dailyTemp() {
		// regex pattern to find temperatures in all possible formats (+31 °C, 31 °C, +31(23) °C, 31(23) °C)
        Pattern tempPattern = Pattern.compile("\\+?\\d{1,2}\\(\\d{1,2}\\) °C|\\+?\\d{1,2} °C");
        Matcher tempMatcher = tempPattern.matcher(this.text);
        String tempDaily = "";
        
        
        //return the first match result since that is the daily one
        if(tempMatcher.find()) {
        	tempDaily = tempMatcher.group();
        }
        
        return tempDaily;
	}
		

}