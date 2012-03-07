package com.example;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import org.bbelovic.weather.GoogleWeatherReader;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MyActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);


        String location = "43202";
        StringBuilder s = new StringBuilder();
        GoogleWeatherReader google = new GoogleWeatherReader(location);
        s.append(google.toString());
        google.process();
        s.append(google.getWeatherModel());

        TextView tv = new TextView(this);
        tv.setText(s);
        setContentView(tv);
        //setContentView(R.layout.main);
    }
}
