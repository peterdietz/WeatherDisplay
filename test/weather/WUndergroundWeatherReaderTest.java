package org.bbelovic.weather;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(enabled = true)
public class WUndergroundWeatherReaderTest 
{
	private static final int WUNDERGROUND_FORECAST_LENGTH = 5;
	private AbstractWeatherReader wunderground;
	
	@BeforeClass
	public void setupClass()
	{
		wunderground = new WUndergroundWeatherReader("Brno");
	}
	
	@Test(enabled = true)
	public void testWUndergroundString() throws Exception
	{		
		Assert.assertEquals(wunderground.getLocation(), "Brno");
		Assert.assertNotNull(wunderground.getWeatherModel());
	}
	
	@Test(enabled = true)
	public void testGetAndSetLocation() throws Exception
	{		
		Assert.assertEquals(wunderground.getLocation(), "Brno");
		wunderground.setLocation("Prague");
		Assert.assertEquals(wunderground.getLocation(), "Prague");
	}
	
	@Test(enabled = true)
	public void testGetAndSetWeatherModel() throws Exception 
	{
		Assert.assertNotNull(wunderground.getWeatherModel());
		WeatherModel wm = new WeatherModel();
		wunderground.setWeatherModel(wm);
		Assert.assertEquals(wunderground.getWeatherModel(), wm);
	}
	
	@Test(enabled = true)
	public void testProcess() throws Exception
	{
		wunderground = new WUndergroundWeatherReader("Brno");
		WeatherModel wm = wunderground.getWeatherModel();
		Assert.assertNull(wm.getCondition());
		Assert.assertNull(wm.getHumidity());
		Assert.assertNull(wm.getTemperature());
		Assert.assertEquals(wunderground.getWeatherModel().getForecast().size(), 0);
		wunderground.process();		
		Assert.assertEquals(wunderground.getWeatherModel().getForecast().size(), WUNDERGROUND_FORECAST_LENGTH);		
		Assert.assertNotNull(wm.getCondition());
		Assert.assertNotNull(wm.getHumidity());
		Assert.assertNotNull(wm.getTemperature());
	}
	
	@Test(enabled = true)
	public void testToString() throws Exception
	{
		wunderground = new WUndergroundWeatherReader("Brno");
		Assert.assertEquals(wunderground.toString(), "WUndergroundWeatherReader for location: Brno");
	}
	
}
