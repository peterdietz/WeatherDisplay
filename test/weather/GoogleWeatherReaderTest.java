package org.bbelovic.weather;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test(enabled = true)
public class GoogleWeatherReaderTest 
{
	private static final int GOOGLE_FORECAST_LENGTH = 4;
	private AbstractWeatherReader googleReader;
	
	@BeforeMethod
	public void beforeMethod()
	{
		this.googleReader = new GoogleWeatherReader("Brno");
	}
	
	@Test(enabled = true)
	public void testGoogleWeatherReaderString() throws Exception
	{
		Assert.assertEquals(googleReader.getLocation(), "Brno");
	}
	
	@Test(enabled = true)
	public void testGetAndSetLocation() throws Exception
	{
		googleReader = new GoogleWeatherReader("TestLocation");
		Assert.assertEquals(googleReader.getLocation(), "TestLocation");
		googleReader.setLocation("TestLocation_2");
		Assert.assertEquals(googleReader.getLocation(), "TestLocation_2");
	}
	
	@Test(enabled = true)
	public void testGetAndSetWeatherModel() throws Exception
	{
		Assert.assertNull(googleReader.getWeatherModel());
		WeatherModel wm = new WeatherModel();
		googleReader.setWeatherModel(wm);
		Assert.assertNotNull(googleReader.getWeatherModel());
		Assert.assertEquals(googleReader.getWeatherModel(), wm);
	}
	
	@Test(enabled = true)
	public void testProcess() throws Exception
	{
		Assert.assertNull(googleReader.getWeatherModel());
		googleReader.process();
		Assert.assertNotNull(googleReader.getWeatherModel());
		WeatherModel wm = googleReader.getWeatherModel();
		Assert.assertNotNull(wm.getCondition());
		Assert.assertNotNull(wm.getHumidity());
		Assert.assertNotNull(wm.getTemperature());
		Assert.assertNotNull(wm.getWind());
		Assert.assertNotNull(wm.getForecast());
		Assert.assertEquals(wm.getForecast().size(), GOOGLE_FORECAST_LENGTH);
	}
	
	@Test(enabled = true)
	public void testToString() throws Exception
	{
		Assert.assertEquals(googleReader.toString(), "GoogleWeatherReader for location: Brno");
	}
}
