package org.bbelovic.weather;

import java.util.Iterator;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;

public class WeatherNamespaceContext implements NamespaceContext 
{

	private Map<String, String> prefixes;
	
	public WeatherNamespaceContext(Map<String, String> prefixes)
	{
		this.prefixes = prefixes;
	}
	
	@Override
	public String getNamespaceURI(String prefix) 
	{
		if (prefix == null) throw new IllegalArgumentException("Prefix cannot be null!");
		if (prefixes.containsKey(prefix))
		{
			return prefixes.get(prefix);
		} else {
			return XMLConstants.XML_NS_URI;
		}
		
	}

	@Override
	public String getPrefix(String namespaceURI) 
	{
		if (prefixes.containsValue(namespaceURI))
		{
			Iterator<Map.Entry<String, String>> it = prefixes.entrySet().iterator();
			while (it.hasNext())
			{
				Map.Entry<String, String> entry = it.next();
				if (entry.getValue().equals(namespaceURI)) return entry.getKey();
			}
		}
		return null;
	}

	@Override
	public Iterator getPrefixes(String namespaceURI) 
	{
		// TODO Auto-generated method stub
		return null;
	}

}
