package org.bbelovic.weather;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test(enabled = true)
public class WindDirectionTest 
{

	@Test(enabled = true)
	public void testGetDirection() throws Exception
	{
		WindDirection direction = WindDirection.getDirection(0);
		Assert.assertEquals(direction, WindDirection.NORTH);
		direction = WindDirection.getDirection(44);
		Assert.assertEquals(direction, WindDirection.NORTH);
		
		direction = WindDirection.getDirection(45);
		Assert.assertEquals(direction, WindDirection.NORTHEAST);
		direction = WindDirection.getDirection(60);
		Assert.assertEquals(direction, WindDirection.NORTHEAST);
		Assert.assertEquals(WindDirection.getDirection(46), WindDirection.NORTHEAST);
		Assert.assertEquals(WindDirection.getDirection(89), WindDirection.NORTHEAST);
		
		direction = WindDirection.getDirection(90);
		Assert.assertEquals(direction, WindDirection.EAST);
		Assert.assertEquals(WindDirection.getDirection(96), WindDirection.EAST);
		Assert.assertEquals(WindDirection.getDirection(130), WindDirection.EAST);
		Assert.assertEquals(WindDirection.getDirection(134), WindDirection.EAST);
		
		direction = WindDirection.getDirection(135);
		Assert.assertEquals(direction, WindDirection.SOUTHEAST);
		Assert.assertEquals(WindDirection.getDirection(136), WindDirection.SOUTHEAST);
		Assert.assertEquals(WindDirection.getDirection(150), WindDirection.SOUTHEAST);
		Assert.assertEquals(WindDirection.getDirection(179), WindDirection.SOUTHEAST);
		
		direction = WindDirection.getDirection(180);
		Assert.assertEquals(direction, WindDirection.SOUTH);
		Assert.assertEquals(WindDirection.getDirection(181), WindDirection.SOUTH);
		Assert.assertEquals(WindDirection.getDirection(200), WindDirection.SOUTH);
		Assert.assertEquals(WindDirection.getDirection(224), WindDirection.SOUTH);
		
		
		direction = WindDirection.getDirection(225);
		Assert.assertEquals(direction, WindDirection.SOUTHWEST);
		Assert.assertEquals(WindDirection.getDirection(226), WindDirection.SOUTHWEST);
		Assert.assertEquals(WindDirection.getDirection(250), WindDirection.SOUTHWEST);
		Assert.assertEquals(WindDirection.getDirection(269), WindDirection.SOUTHWEST);
		
		direction = WindDirection.getDirection(270);
		Assert.assertEquals(direction, WindDirection.WEST);
		Assert.assertEquals(WindDirection.getDirection(271), WindDirection.WEST);
		Assert.assertEquals(WindDirection.getDirection(290), WindDirection.WEST);
		Assert.assertEquals(WindDirection.getDirection(314), WindDirection.WEST);
		
		direction = WindDirection.getDirection(315);
		Assert.assertEquals(direction, WindDirection.NORTHWEST);
		Assert.assertEquals(WindDirection.getDirection(316), WindDirection.NORTHWEST);
		Assert.assertEquals(WindDirection.getDirection(325), WindDirection.NORTHWEST);
		Assert.assertEquals(WindDirection.getDirection(359), WindDirection.NORTHWEST);
		
		direction = WindDirection.getDirection(360);
		Assert.assertEquals(direction, WindDirection.NORTH);
		Assert.assertEquals(WindDirection.getDirection(361), WindDirection.NORTH);
		Assert.assertEquals(WindDirection.getDirection(400), WindDirection.NORTH);
		Assert.assertEquals(WindDirection.getDirection(404), WindDirection.NORTH);
		Assert.assertEquals(WindDirection.getDirection(449), WindDirection.NORTHEAST);
		Assert.assertEquals(WindDirection.getDirection(450), WindDirection.EAST);
		Assert.assertEquals(WindDirection.getDirection(495), WindDirection.SOUTHEAST);
		Assert.assertEquals(WindDirection.getDirection(498), WindDirection.SOUTHEAST);
		Assert.assertEquals(WindDirection.getDirection(539), WindDirection.SOUTHEAST);
		Assert.assertEquals(WindDirection.getDirection(540), WindDirection.SOUTH);
		Assert.assertEquals(WindDirection.getDirection(541), WindDirection.SOUTH);
		Assert.assertEquals(WindDirection.getDirection(585), WindDirection.SOUTHWEST);
		Assert.assertEquals(WindDirection.getDirection(629), WindDirection.SOUTHWEST);
		Assert.assertEquals(WindDirection.getDirection(630), WindDirection.WEST);
		Assert.assertEquals(WindDirection.getDirection(631), WindDirection.WEST);
		Assert.assertEquals(WindDirection.getDirection(720), WindDirection.NORTH);
		Assert.assertEquals(WindDirection.getDirection(721), WindDirection.NORTH);
	}
	
	@Test(enabled = true)
	public void testGetDegrees() throws Exception
	{
		Assert.assertEquals(WindDirection.NORTH.getDegrees(), 0);
		Assert.assertEquals(WindDirection.NORTHEAST.getDegrees(), 45);
		Assert.assertEquals(WindDirection.EAST.getDegrees(), 90);
		Assert.assertEquals(WindDirection.SOUTHEAST.getDegrees(), 135);
		Assert.assertEquals(WindDirection.SOUTH.getDegrees(), 180);
		Assert.assertEquals(WindDirection.SOUTHWEST.getDegrees(), 225);
		Assert.assertEquals(WindDirection.WEST.getDegrees(), 270);
		Assert.assertEquals(WindDirection.NORTHWEST.getDegrees(), 315);
	}
	
	@Test(enabled = true)
	public void testGetCode() throws Exception
	{
		Assert.assertEquals(WindDirection.NORTH.getCode(), "N");
		Assert.assertEquals(WindDirection.NORTHEAST.getCode(), "NE");
		Assert.assertEquals(WindDirection.EAST.getCode(), "E");
		Assert.assertEquals(WindDirection.SOUTHEAST.getCode(), "SE");
		Assert.assertEquals(WindDirection.SOUTH.getCode(), "S");
		Assert.assertEquals(WindDirection.SOUTHWEST.getCode(), "SW");
		Assert.assertEquals(WindDirection.WEST.getCode(), "W");
		Assert.assertEquals(WindDirection.NORTHWEST.getCode(), "NW");
	}
}
