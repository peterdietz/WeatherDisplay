package org.bbelovic.weather;

import java.net.MalformedURLException;
import java.net.URL;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class YahooWeatherReader extends AbstractWeatherReader
{
	private static final String WOEID_URL = "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20geo.places%20where%20text%3D%22";
	private static final String WEATHER_URL = "http://weather.yahooapis.com/forecastrss?w=";
	private static final String [] XPATHS = {"//yweather:atmosphere[@humidity]", 
		                                     "//yweather:condition[@temp]", 
		                                     "//yweather:condition[@text]", 
		                                     "//yweather:wind[@direction]" };
	
	private static final String FORECAST_XPATH = "//yweather:forecast";
		                                     
	private Document doc;
	private String   units;
	
	
	public YahooWeatherReader(String location, String units)
	{
		super(location);
		this.units = units.toLowerCase();
		this.wm = new WeatherModel();
	}
	
	public YahooWeatherReader(String location)
	{
		this (location, "c");
	}
	
	public String getUnits()
	{
		return this.units;
	}
	
	public void setUnits(String units)
	{		
		this.units = units.toLowerCase();
	}
	
	@Override
	public void process()
	{
		String woeid = getWoeid();
		String weatherData = getWeatherData(woeid);
		doc = Utilities.documentFromString(weatherData, true);		
		wm.setHumidity( executeXPath(doc, XPATHS[0], "humidity") );
		wm.setTemperature(executeXPath(doc, XPATHS[1], "temp"));
		wm.setCondition(executeXPath(doc, XPATHS[2], "text"));
		wm.setWind(executeXPath(doc, XPATHS [3], "direction"));
		processForecast();
	}
	
	@Override
	public String toString()
	{
		return "YahooWeatherReader for location: "+ this.location;
	}
	
	private String getWoeid()
	{
		String woeid = null;
		try {
			String content = Utilities.readUrl(new URL(WOEID_URL + location + "%22"));
			Document doc = Utilities.documentFromString(content, true);
			NodeList nl = Utilities.executeXPath(doc, "//yahooDefault:woeid");
			if (nl.item(0) != null)
			{
				woeid = nl.item(0).getTextContent();
			}
		} catch (MalformedURLException mue)
		{
			mue.printStackTrace();
		}
		return woeid;
	}
	
	private String getWeatherData(String woeid)
	{
		String data = null;
		try {
			data = Utilities.readUrl(new URL(WEATHER_URL + woeid + "&u="+ units));
		} catch (MalformedURLException mue)
		{
			mue.printStackTrace();
		}
		return data;
	}
	
	private void processForecast()
	{
		NodeList forecastList =  Utilities.executeXPath(doc, FORECAST_XPATH);
		for (int i = 0; i < forecastList.getLength(); i++)
		{
			WeatherModel forecastModel = new WeatherModel();
			String temp = forecastList.item(i).getAttributes().getNamedItem("low").getNodeValue() + " - " + 
						  forecastList.item(i).getAttributes().getNamedItem("high").getNodeValue(); 
			forecastModel.setTemperature(temp);
			forecastModel.setCondition(forecastList.item(i).getAttributes().getNamedItem("text").getNodeValue());
			wm.addForecastModel(forecastModel);
		}
	}
	
	private String executeXPath(Document doc, String xpath, String attr)
	{
		NodeList nl = Utilities.executeXPath(doc, xpath);
		String data = null;
		if (nl != null & nl.item(0) != null)
		{
			data = nl.item(0).getAttributes().getNamedItem(attr).getNodeValue();
		}
		return data;
	}
	
	public static void main(String[] args) 
	{
		YahooWeatherReader yahoo = new YahooWeatherReader("Brno", "C");
		yahoo.process();
		System.out.println(yahoo.getWeatherModel());
	}
}
