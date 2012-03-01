package org.bbelovic.weather;

import java.net.MalformedURLException;
import java.net.URL;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class GoogleWeatherReader extends AbstractWeatherReader
{
	
	private static final String WEATHER_URL = "http://www.google.com/ig/api?weather=";		
	private static final String[]  XPATHS = {"//humidity[@data]", "//temp_c[@data]", "//condition[@data]", "//wind_condition[@data]"};
	private static final String DATA_ATTRIBUTE_NAME = "data";
	private static final String FORECAST_XPATH = "//forecast_conditions";
		
	private Document doc;
		
	public GoogleWeatherReader(String location)
	{		
		super(location);
	}
	
	@Override
	public void process()
	{
		try {
			String weatherData = Utilities.readUrl(new URL(WEATHER_URL + location));			
			doc = Utilities.documentFromString(weatherData, true);			
			this.wm = new WeatherModel();
			String humidity = executeXPath(doc, XPATHS [ 0 ], DATA_ATTRIBUTE_NAME);
			String wind = 	executeXPath(doc, XPATHS [ 3 ], DATA_ATTRIBUTE_NAME).split(" ")[ 1 ];
			wm.setHumidity(   Utilities.getAllMatches(humidity, Utilities.TEMP_AND_SPEED_REGEX).get(0));
			wm.setTemperature(executeXPath(doc, XPATHS [ 1 ], DATA_ATTRIBUTE_NAME));
			wm.setCondition(  executeXPath(doc, XPATHS [ 2 ], DATA_ATTRIBUTE_NAME));
			wm.setWind(WindDirection.getDirection(wind));
			processForecast();
		} catch (MalformedURLException mue)
		{
			mue.printStackTrace();
		}
	}
	
	@Override
	public String toString()
	{
		return "GoogleWeatherReader for location: "+ this.location;
	}
	
	private void processForecast()
	{
		NodeList children = null;
		NodeList nl = Utilities.executeXPath(doc, FORECAST_XPATH);		
		for (int i = 0; i < nl.getLength(); i++)
		{
			children = nl.item(i).getChildNodes();
			String low  = children.item(1).getAttributes().getNamedItem(DATA_ATTRIBUTE_NAME).getNodeValue();
			String high = children.item(2).getAttributes().getNamedItem(DATA_ATTRIBUTE_NAME).getNodeValue();
			String condition = children.item(4).getAttributes().getNamedItem(DATA_ATTRIBUTE_NAME).getNodeValue();
			WeatherModel forecast = new WeatherModel();
			forecast.setCondition(condition);
			forecast.setTemperature(Utilities.fahrenheitToCelsius(low) + " - "+ Utilities.fahrenheitToCelsius(high));			
			wm.addForecastModel(forecast);
		}
	}
	
	private String executeXPath(Document doc, String xpath, String attr)
	{
		String data = null;
		NodeList nl = Utilities.executeXPath(doc, xpath);
		if (nl != null && nl.item(0) != null)
		{
			data = nl.item(0).getAttributes().getNamedItem(attr).getNodeValue();
		}
		return data;
	}
	
	public static void main(String[] args) throws Exception
	{
		GoogleWeatherReader google = new GoogleWeatherReader("Brno");
		google.process();
		System.out.println(google.getWeatherModel());
	}
	
}
