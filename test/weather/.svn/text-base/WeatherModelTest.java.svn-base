package org.bbelovic.weather;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test(enabled = true)
public class WeatherModelTest 
{

	@Test(enabled = true)
	public void testGetAndSetTemperature() throws Exception
	{
		WeatherModel wm = new WeatherModel();
		Assert.assertNull(wm.getTemperature());
		wm.setTemperature("17°C");
		Assert.assertEquals(wm.getTemperature(), "17°C");
	}
	
}
