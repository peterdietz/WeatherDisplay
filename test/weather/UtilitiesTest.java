package org.bbelovic.weather;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

import javax.xml.namespace.NamespaceContext;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class UtilitiesTest 
{
	private static final String EXPECTED_WOEID = "786869";
	private static final String YAHOO_WEATHER_DATA = "/home/bbelovic/workspace/Weather/weatheryahoo.xml";
	private static final String YAHOO_WEATHER_RSS = "/home/bbelovic/workspace/Weather/weatheryahoorss.xml";
	
	@Test(enabled = true)
	public void testExecuteXPath() throws Exception
	{
		InputSource source = Utilities.readFile(YAHOO_WEATHER_DATA);
		Document doc = Utilities.documentFromString(source, true);
		Assert.assertNotNull(doc);
		String xpath = "//yahooDefault:woeid";
		NodeList nl =  Utilities.executeXPath(doc, xpath);
		Assert.assertEquals(nl.getLength(), 1);
		String woeid = nl.item(0).getTextContent(); 
		Assert.assertEquals(woeid, EXPECTED_WOEID);
						
		String data = xpathData("//yweather:location[@city]", "city");
		Assert.assertEquals(data, "Brno");
		data = xpathData("//yweather:condition[@text]", "text");
		Assert.assertEquals(data, "Clear");
		data = xpathData("//yweather:condition[@temp]", "temp");
		Assert.assertEquals(data, "2");		
		data = xpathData("//yweather:atmosphere[@humidity]", "humidity");
		Assert.assertEquals(data, "93");
	}
	
	@Test(enabled = true)
	public void testConvertWindInformationInteger() throws Exception
	{
		WindDirection direction = Utilities.convertWindInformation(130);
		Assert.assertEquals(direction, WindDirection.EAST);
	}
	
	@Test(enabled = true)
	public void testConverWindInformationString() throws Exception
	{
		WindDirection direction = Utilities.convertWindInformation("130");
		Assert.assertEquals(direction, WindDirection.EAST);
	}
	
	@Test(enabled = true, expectedExceptions = NumberFormatException.class)
	public void testConvertWindInformationStringNegative() throws Exception
	{
		//expecting java.lang.NumberFormatException here
		Utilities.convertWindInformation("13X0");
	}
		
	@Test(enabled = true)
	public void testDocumentFromStringInputSource() throws Exception
	{
		Document doc = Utilities.documentFromString(new InputSource(new FileInputStream(YAHOO_WEATHER_DATA)), true);
		Assert.assertNotNull(doc);
		
	}
	
	@Test(enabled = true)
	public void testDocumentFromStringString() throws Exception
	{
		Document doc = Utilities.documentFromString(readResource( (new FileInputStream(YAHOO_WEATHER_DATA)) ).toString(), true);
		Assert.assertNotNull(doc);
	}
	
	@Test(enabled = true)
	public void testReadFile() throws Exception
	{
		InputSource is = Utilities.readFile(YAHOO_WEATHER_DATA);
		Assert.assertNotNull(is);
		FileInputStream fis = new FileInputStream(YAHOO_WEATHER_DATA);
		Writer w1 = readResource(fis);
		Writer w2 = readResource(is.getByteStream());
		Assert.assertEquals(w1.toString(), w2.toString());
	}
	
	@Test(enabled = true)
	public void testGetAllMatches() throws Exception
	{
		String input = "Chance of Rain. High 5&amp;deg;C (41&amp;deg;F). Winds 18 kph NNE";
		String regex = "\\d{1,2}";
		List<String> results = Utilities.getAllMatches(input, regex);
		Assert.assertEquals(results.size(), 3);
		Assert.assertEquals(results.get(0), "5");
		Assert.assertEquals(results.get(1), "41");
		Assert.assertEquals(results.get(2), "18");
	}
	
	@Test(enabled = true)
	public void testGetNamespaceContext() throws Exception
	{
		NamespaceContext ctx = Utilities.getNamespaceContext();
		Assert.assertEquals(ctx.getClass(), WeatherNamespaceContext.class);
		
		Assert.assertEquals(ctx.getNamespaceURI("yahoo"), "http://www.yahooapis.com/v1/base.rng");
		Assert.assertEquals(ctx.getNamespaceURI("yweather"), "http://xml.weather.yahoo.com/ns/rss/1.0");
		Assert.assertEquals(ctx.getNamespaceURI("geo"), "http://www.w3.org/2003/01/geo/wgs84_pos#");
		Assert.assertEquals(ctx.getNamespaceURI("yahooDefault"), "http://where.yahooapis.com/v1/schema.rng");
	}
	
	// helper methods follow
	
	private String xpathData(String xpath, String attr) throws Exception
	{
		
		InputSource source = Utilities.readFile(YAHOO_WEATHER_RSS);
		Document doc = Utilities.documentFromString(source, true);
		NodeList nl = Utilities.executeXPath(doc, xpath);
		Assert.assertEquals(nl.getLength(), 1);
		return nl.item(0).getAttributes().getNamedItem(attr).getNodeValue();
	}		
	
	private Writer readResource(InputStream is) throws Exception
	{
		StringWriter sw = new StringWriter();
		String line = null;
		BufferedReader br = null;
		try {
			 br = new BufferedReader(new InputStreamReader(is));
			while ( (line = br.readLine()) != null )
			{
				sw.append(line);
			}
		} finally {
			if (br != null)
			{
				br.close();
			}
		}
		return sw;
	}
	
}
