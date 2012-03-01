package org.bbelovic.weather;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WeatherModel implements Serializable 
{
	private static final long serialVersionUID = 3836336917331587164L;

	private String humidity;
	private String temperature;
	private String condition;
	private String wind;
	
	private List<WeatherModel> forecast;
	
	public WeatherModel()
	{
		this.forecast = new ArrayList<WeatherModel>();
	}
	
	public WeatherModel(String humidity, String temperature, String condition)
	{
		this.humidity = humidity;
		this.temperature = temperature;
		this.condition = condition;
		this.forecast = new ArrayList<WeatherModel>();
	}
	
	public String getHumidity()
	{
		return this.humidity;
	}
	
	public void setHumidity(String humidity)
	{
		this.humidity = humidity;
	}

	public String getTemperature() 
	{
		return temperature;
	}

	public void setTemperature(String temperature) 
	{
		this.temperature = temperature;
	}

	public String getCondition() 
	{
		return condition;
	}

	public void setCondition(String condition) 
	{
		this.condition = condition;
	}
	
	public String getWind()
	{
		return this.wind;
	}
			
	public void setWind(String wind)
	{
		this.wind = Utilities.convertWindInformation(wind).getCode();
	}
	
	public void setWind(WindDirection windDirection)
	{
		this.wind = windDirection.getCode();
	}
	
	public List<WeatherModel> getForecast()
	{
		return this.forecast;
	}
	
	public void setForecast(List<WeatherModel> forecast)
	{
		if (forecast != null)
		{
			this.forecast = forecast;
		}
	}
	
	public void addForecastModel(WeatherModel forecastModel)
	{
		if (forecastModel != null)
		{
			forecast.add(forecastModel);
		}
	}
	
	@Override
	public String toString()
	{
		return prepareToTring();
	}
	
	private String prepareToTring()
	{
		StringBuffer sb = new StringBuffer();
		if (this.humidity != null && !this.humidity.equals(""))
		{
			sb.append("humidity="+ this.humidity + ", ");
		}
		if (this.temperature != null && !this.temperature.equals(""))
		{
			sb.append("temperature="+ this.temperature + ", ");
		}
		if (this.condition != null && !this.condition.equals(""))
		{
			sb.append("condition="+ this.condition + ", ");
		}
		if (this.wind != null && !this.wind.equals(""))
		{
			sb.append("wind="+ this.wind + ", ");
		}
		if (forecast.size() != 0)
		{
			sb.append("\nForecast for "+ forecast.size() + " days: \n");
			for (WeatherModel model : forecast)
			{
				sb.append(model + "\n");
			}
		}
		return sb.toString();
	}
}
