package org.bbelovic.weather;

import org.testng.TestNG;

public class TestNGSuite 
{

	public static void main(String [] args) 
	{
		TestNG testng = new TestNG();
		testng.setTestClasses(new Class [] {YahooWeatherReaderTest.class,
				                            WUndergroundWeatherReaderTest.class,
				                            GoogleWeatherReaderTest.class,
				                            UtilitiesTest.class,
				                            WindDirectionTest.class,
				                            WeatherModelTest.class
		});
		testng.run();
	}
	
}
