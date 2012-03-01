package org.bbelovic.weather;

public abstract class AbstractWeatherReader implements IWeatherReader 
{
	protected WeatherModel wm;
	protected String location;
	
	public AbstractWeatherReader(String location)
	{
		this.location = location;
	}
	
	public abstract void process(); 

	public void setLocation(String location)
	{
		this.location = location;
	}
	
	public String getLocation()
	{
		return this.location;
	}
	
	public void setWeatherModel(WeatherModel wm)
	{
		this.wm = wm;
	}
	
	public WeatherModel getWeatherModel()
	{
		return this.wm;
	}
}
