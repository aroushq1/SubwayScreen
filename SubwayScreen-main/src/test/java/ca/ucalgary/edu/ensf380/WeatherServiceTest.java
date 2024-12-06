package ca.ucalgary.edu.ensf380;

import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException; 
/**Class consisiting of unit tests for weatherService object*/

public class WeatherServiceTest {

    private WeatherParser weatherParserMock;
    private WeatherService weatherService;
    
    
    /**This method sets up the WeatherParser mock object and the weatherService object that calls the mocked WeatherParser*/
    @Before
    public void setup() {
        weatherParserMock = mock(WeatherParser.class);
        weatherService = new WeatherService(weatherParserMock);
    }
    
    /**This method checks that the method getDailyTemperature in WeatherService retrieves the same value as the 
     * dailyTemp method in the mocked WeatherParser class*/
    @Test
    public void getDailyTemperatureTest() {
        String expTemp = "+10 °C";
        

        when(weatherParserMock.dailyTemp()).thenReturn(expTemp);
        
        weatherService.updateWeatherData();

        String actualTemp = weatherService.getDailyTemperature();

        assertEquals("The expected temperature doesn't match the actual temperature", expTemp, actualTemp);
    }

    /**This method checks that the method getDailyWind in WeatherService retrieves the same value as the 
     * dailyWind method in the mocked WeatherParser class*/
    @Test
    public void getDailyWindTest() {
        String expWind = "15 km/h";

        when(weatherParserMock.dailyWind()).thenReturn(expWind);
        
        weatherService.updateWeatherData();

        String actualWind = weatherService.getDailyWind();

        assertEquals("The expected wind gust doesn't match the actual wind gust", expWind, actualWind);
    }
    
    /**This method checks that the method getDailyRain in WeatherService retrieves the same value as the 
     * dailyRain method in the mocked WeatherParser class*/
    @Test
    public void getDailyRainTest() {
        String expRain = "0.0 mm";

        when(weatherParserMock.dailyRain()).thenReturn(expRain);
        
        weatherService.updateWeatherData();

        String actualRain = weatherService.getDailyRain();

        assertEquals("The expected rain amount doesn't match the actual rain amount", expRain, actualRain);
    }
    
    /**This method checks that when we call the updateWeatherData method it correctly updates the weather from
     * the WeatherParser class, so we check that the temperature, wind, and rain is correctly updated
     * @throws IOException when there is an issue with input/output
     **/
    @Test
    public void updateWeatherDataTest() throws IOException {
        String expTemp = "+10 °C";
        String expWind = "15 km/h";
        String expRain = "0.0 mm";

        doNothing().when(weatherParserMock).fetchWeatherData(); 
        when(weatherParserMock.dailyTemp()).thenReturn(expTemp);
        when(weatherParserMock.dailyWind()).thenReturn(expWind);
        when(weatherParserMock.dailyRain()).thenReturn(expRain);

        weatherService.updateWeatherData();

        assertEquals("The expected temperature doesn't match the actual temperature", expTemp, weatherService.getDailyTemperature());
        assertEquals("The expected wind gust doesn't match the actual wind gust", expWind, weatherService.getDailyWind());
        assertEquals("The expected rain amount doesn't match the actual rain amount", expRain, weatherService.getDailyRain());
    }
}