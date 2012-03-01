package org.bbelovic.weather;

import java.net.MalformedURLException;
import java.net.URL;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class WUndergroundWeatherReader extends AbstractWeatherReader
{
	private static final String LOCATION_URL = "http://api.wunderground.com/auto/wui/geo/GeoLookupXML/index.xml?query=";
	private static final String CURRENT_CONDITION_URL = "http://api.wunderground.com/auto/wui/geo/WXCurrentObXML/index.xml?query=";
	private static final String FORECAST_URL = "http://api.wunderground.com/auto/wui/geo/ForecastXML/index.xml?query=";
		
	private static final String AIRPORTID_XPATH = "//icao";
	private static final String [] XPATHS = new String [] {"//relative_humidity","//temp_c", "//weather", "//wind_degrees", "//fcttext"};
		
	public WUndergroundWeatherReader(String location)
	{
		super ( location );
		this.wm = new WeatherModel();
	}
	
	@Override
	public void process()
	{
		String aid = getAirportID();
		getCurrentConditions(aid);
		getForecastData();
	}
	
	@Override
	public String toString()
	{
		return "WUndergroundWeatherReader for location: "+ this.location;
	}
	
	private String getAirportID()
	{
		String aid = null;
		try {
			String xmlContent = Utilities.readUrl(new URL(LOCATION_URL + this.location));
			Document doc = Utilities.documentFromString(xmlContent, true);
			NodeList nl = Utilities.executeXPath(doc, AIRPORTID_XPATH);
			aid = processNodeList(nl);
		} catch (MalformedURLException mue)
		{
			mue.printStackTrace();
		}
		return aid;
	}
	
	private void getCurrentConditions(String airportID)
	{
		NodeList nl = null;
		try {
			String xmlContent = Utilities.readUrl(new URL(CURRENT_CONDITION_URL + airportID));
			Document doc = Utilities.documentFromString(xmlContent, true);
			nl = Utilities.executeXPath(doc, XPATHS [0]);
			wm.setHumidity(processNodeList(nl));
			nl = Utilities.executeXPath(doc, XPATHS [1]);
			wm.setTemperature(processNodeList(nl));
			nl = Utilities.executeXPath(doc, XPATHS [2]);
			wm.setCondition(processNodeList(nl));
			nl = Utilities.executeXPath(doc, XPATHS [3]);
			wm.setWind(processNodeList(nl));
		} catch (MalformedURLException mue)
		{
			mue.printStackTrace();
		}
	}
	
	private void getForecastData()
	{
		try {
			String xmlContent = Utilities.readUrl(new URL(FORECAST_URL + this.location));
			Document doc = Utilities.documentFromString(xmlContent, true);
			NodeList nl = Utilities.executeXPath(doc, XPATHS [4]);
			fillForecast(nl);
		} catch (MalformedURLException mue)
		{
			mue.printStackTrace();
		}
	}
	
	private void fillForecast(NodeList nl)
	{				
		String forecast    = null;
		String condition   = null;
		String temperature = null;
		for (int i = 1; i < nl.getLength(); i++) // first item is for today (not a forecast)
		{
			if (nl.item(i) != null)
			{				
				WeatherModel fctModel = new WeatherModel();
				forecast = nl.item(i).getTextContent();
				
				condition = Utilities.getAllMatches(forecast, "[a-zA-Z ]+").get(0);
				temperature = Utilities.getAllMatches(forecast, "\\d{1,2}").get(0);
				fctModel.setCondition(condition);
				fctModel.setTemperature(temperature);
				wm.addForecastModel(fctModel);
			}
		}		
	}
	
	private String processNodeList(NodeList nl)
	{
		
		if (nl != null && nl.item(0) != null)
		{
			return nl.item(0).getTextContent();
		} else {
			return "";
		}
	}
	
	public static void main(String[] args) {
		WUndergroundWeatherReader wUnderground = new WUndergroundWeatherReader("Brno");
		wUnderground.process();
		System.out.println(wUnderground.getWeatherModel());
	}
}
