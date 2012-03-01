package org.bbelovic.weather;

import java.util.TimerTask;

public class WeatherTimerTask extends TimerTask 
{

	private GoogleWeatherReader weather;
	
	public WeatherTimerTask()
	{
		this.weather = new GoogleWeatherReader("Brno");
	}
	
	
	@Override
	public void run() 
	{
		weather.process();
		WeatherModel wm = weather.getWeatherModel();
		System.out.println("Temperature: "+ wm.getTemperature() + 
						   ", " + wm.getHumidity() + ", " +
						   
		                   "Condition: "+ wm.getCondition() );		
	}
}
