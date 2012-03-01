package org.bbelovic.weather;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test(enabled = true)
public class YahooWeatherReaderTest 
{
	private static final int YAHOO_FORECAST_LENGTH = 2;
	private YahooWeatherReader yahoo;
	
	@BeforeMethod
	public void beforeMethod() throws Exception
	{
		yahoo = new YahooWeatherReader("Brno", "C");
	}
	
	@Test(enabled = true, timeOut = 120000)
	public void testYahooWeatherReaderTwoParams() throws Exception
	{
		Assert.assertNotNull(yahoo);
		Assert.assertNotNull(yahoo.getWeatherModel());
		Assert.assertEquals(yahoo.getLocation(), "Brno");
		Assert.assertEquals(yahoo.getUnits(), "c");
	}
	
	@Test(enabled = true, timeOut = 120000)
	public void testYahooOneParam() throws Exception
	{
		YahooWeatherReader reader = new YahooWeatherReader("Brno");
		Assert.assertEquals(reader.getUnits(), "c");
		Assert.assertEquals(reader.getLocation(), "Brno");
	}
	
	@Test(enabled = true, timeOut = 120000)
	public void testGetAndSetLocation() throws Exception
	{
		Assert.assertEquals(yahoo.getLocation(), "Brno");
		yahoo.setLocation("Prague");
		Assert.assertEquals(yahoo.getLocation(), "Prague");
	}
	
	@Test(enabled = true)
	public void testGetAndSetWeatherModel() throws Exception
	{
		yahoo = new YahooWeatherReader("New York");
		Assert.assertNotNull(yahoo.getWeatherModel());
		WeatherModel wm = new WeatherModel();
		yahoo.setWeatherModel(wm);
		Assert.assertNotNull(yahoo.getWeatherModel());
		Assert.assertEquals(yahoo.getWeatherModel(), wm);
	}
	
	@Test(enabled = true)
	public void testProcess() throws Exception
	{
		yahoo = new YahooWeatherReader("Brno");
		WeatherModel wm = yahoo.getWeatherModel();
		Assert.assertNull(wm.getCondition());
		Assert.assertNull(wm.getTemperature());
		Assert.assertNull(wm.getHumidity());
		Assert.assertNull(wm.getWind());
		yahoo.process();		
		Assert.assertNotNull(wm.getForecast());
		Assert.assertEquals(wm.getForecast().size(), YAHOO_FORECAST_LENGTH);
		Assert.assertNotNull(wm.getCondition());
		Assert.assertNotNull(wm.getTemperature());
		Assert.assertNotNull(wm.getHumidity());
		Assert.assertNotNull(wm.getWind());
	}
	
	@Test(enabled = true, timeOut = 120000)
	public void testGetAndSetUnits() throws Exception
	{
		Assert.assertEquals(yahoo.getUnits(), "c");
		yahoo.setUnits("F");
		Assert.assertEquals(yahoo.getUnits(), "f");
	}
	
	@Test(enabled = true)
	public void testToString() throws Exception
	{
		yahoo = new YahooWeatherReader("Brno");
		Assert.assertEquals(yahoo.toString(), "YahooWeatherReader for location: Brno");
	}
}
