package org.bbelovic.weather;

import java.util.HashMap;
import java.util.Map;

public enum WindDirection 
{
	NORTH     (0, "N"),
	NORTHEAST (1, "NE"),
	EAST      (2, "E"),
	SOUTHEAST (3 ,"SE"),
	SOUTH	  (4 ,"S"),
	SOUTHWEST (5, "SW"),
	WEST      (6, "W"),
	NORTHWEST (7, "NW");
	
	private static final int STEP = 45;
	private static final int MASK = 8;
	
	private static final Map<String, WindDirection> directions = new HashMap<String, WindDirection>();
	
	static {
		initDirections();
	}
	
	private int degrees;
	private String code;
	
	WindDirection(int idx, String code)
	{
		this.degrees = idx * STEP;
		this.code = code;		
	}
	
	public int getDegrees()
	{
		return this.degrees;
	}
	
	public String getCode()
	{
		return this.code;
	}
	
	public static WindDirection getDirection(int degrees)
	{
		int idx = (degrees / STEP) % MASK;
		return values()[ idx ];
	}
	
	public static WindDirection getDirection(String code)
	{
		if (directions.containsKey(code))
		{
			return directions.get(code);
		} else {
			return null;
		}
	}
	
	private static void initDirections()
	{		
		directions.put("N", 	NORTH);
		directions.put("NE", 	NORTHEAST);
		directions.put("E", 	EAST);
		directions.put("SE", 	SOUTHEAST);
		directions.put("S", 	SOUTH);
		directions.put("SW", 	SOUTHWEST);
		directions.put("W", 	WEST);
		directions.put("NW", 	NORTHWEST);
	}
}
