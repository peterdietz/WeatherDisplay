package org.bbelovic.weather;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class Utilities 
{

	/**
	 * This regular expression gets sequences of one or two digits from input string. Therefore is convenient for
	 * retrieving speed of wind or temperature data.
	 */
	public static final String TEMP_AND_SPEED_REGEX = "\\d{1,2}";
	
	private Utilities()
	{
		// no instances of this class are allowed
	}
	
	public static String readUrl(URL url)
	{	
		StringBuffer sb = new StringBuffer();
		try {			
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");			
			BufferedReader br =  new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = null;
			while ( (line = br.readLine() ) != null)
			{
				sb.append(line);
			}
		} catch (MalformedURLException mue)
		{
			mue.printStackTrace();
		} catch (IOException ioe)
		{
			ioe.printStackTrace();
		} finally {
			
		}
		return sb.toString();
	}
	
	public static InputSource readFile(String pathToFile)
	{
		InputSource is = null;
		try {
			is = new InputSource(new FileInputStream(pathToFile));
		} catch (FileNotFoundException fnfe)
		{
			System.err.println(pathToFile + " was not found!");
		}
		return is;
	}
	
	public static NamespaceContext getNamespaceContext()
	{
		Map<String, String> prefixes = new HashMap<String, String>();
		prefixes.put("yahoo", "http://www.yahooapis.com/v1/base.rng");
		prefixes.put("yweather","http://xml.weather.yahoo.com/ns/rss/1.0");
		prefixes.put("geo", "http://www.w3.org/2003/01/geo/wgs84_pos#");
		prefixes.put("yahooDefault", "http://where.yahooapis.com/v1/schema.rng");		
		return new WeatherNamespaceContext(prefixes);
		
	}
	
	public static Document documentFromString(String xmlContent, boolean namespaceAware)
	{
		Document doc = null;
		try {
			doc = documentFromString(new InputSource(new StringReader(xmlContent)), namespaceAware);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return doc;
	}
	
	public static Document documentFromString(InputSource is, boolean namespaceAware)
	{
		Document doc = null;
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(namespaceAware);
			DocumentBuilder builder = dbf.newDocumentBuilder();
			doc = builder.parse(is);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return doc;
	}
	
	public static NodeList executeXPath(Document doc, String xpathString)
	{
		Object result  = null;
		try {
			XPath xpath = XPathFactory.newInstance().newXPath();
			xpath.setNamespaceContext(getNamespaceContext());
			XPathExpression expr = xpath.compile(xpathString);
			result = expr.evaluate(doc, XPathConstants.NODESET);
		} catch (XPathExpressionException xpe)
		{
			xpe.printStackTrace();
		}
		return (NodeList) result;
	}
	
	public static WindDirection convertWindInformation(int degrees)
	{
		return WindDirection.getDirection(degrees);
	}
	
	public static WindDirection convertWindInformation(String degrees)
	{
		return convertWindInformation(Integer.parseInt(degrees));
	}
	
	public static List<String> getAllMatches(String input, String regex)
	{
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		List<String> matches = new ArrayList<String>();
		while (matcher.find())
		{
			matches.add(matcher.group());
			
		}
		return matches;
	}
	
	public static int celsiusToFahrenheit(int tempInCelsius)
	{
		return ((tempInCelsius * 9/5) + 32);
	}
	
	public static int celsiusToFahrenheit(String tempInCelsius)
	{		
		return celsiusToFahrenheit(Integer.parseInt( tempInCelsius )); 
	}
	
	public static int fahrenheitToCelsius(int tempInFahrenheit)
	{
		return ((tempInFahrenheit - 32) * 5/9);
	}
	
	public static int fahrenheitToCelsius(String tempInFahrenheit)
	{
		return fahrenheitToCelsius(Integer.parseInt(tempInFahrenheit));
	}
}
