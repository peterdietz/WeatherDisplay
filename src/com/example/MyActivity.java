package com.example;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
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
        StringBuilder builder = new StringBuilder();
        try {
            URL weatherURL = new URL("http://www.google.com/ig/api?weather=43202");
            URLConnection connection = weatherURL.openConnection();

            XPath xpath = XPathFactory.newInstance().newXPath();
            InputSource inputSource = new InputSource(connection.getInputStream());


            //NodeList nodes = (NodeList) xpath.evaluate("//current_conditions", inputSource, XPathConstants.NODESET);

            //for(int i=0;i<nodes.getLength();i++)
            //{
            //    builder.append(xpath.evaluate("@data", nodes.item(i)));
            //}


            //String currentInfo = xpath.evaluate("/forecast_information/city/@data", nodes);


            //builder.append("here");
            //builder.append("Weather forecast for " + currentInfo);
            //        + xpath.evaluate("//forecast_information/city/@data", inputSource) + "\n");

            //connection = weatherURL.openConnection();
            //inputSource = new InputSource(connection.getInputStream());
            //xpath=XPathFactory.newInstance().newXPath();
            //builder.append("Last Updated: "
            //        + xpath.evaluate("//forecast_information/current_date_time/@data", inputSource) + "\n");
            
            //builder.append("Current Condition: "
            //        + xpath.evaluate("//current_conditions/condition/@data", inputSource) + "\n");

            builder.append("Temperature "
                    + xpath.evaluate("//current_conditions/temp_f/@data", inputSource) + "\n");
            builder.append("Humidity "
                    + xpath.evaluate("//current_conditions/humidity/@data", inputSource) + "\n");
            builder.append("Wind "
                    + xpath.evaluate("//current_conditions/wind_condition/@data", inputSource) + "\n");
            builder.append("Humidity "
                    + xpath.evaluate("//current_conditions/humidity/@data", inputSource) + "\n");




            /*ImageView imageView = new ImageView(this);
            Uri imageURI = Uri.parse("http://www.google.com/" + xpath.evaluate("//current_conditions/icon/@data", inputSource));
            imageView.setImageURI(imageURI);
            setContentView(imageView);*/


        } catch (MalformedURLException e) {
            builder.append("Malformed url: "+ e.getMessage());
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            builder.append("IO exception"+ e.getMessage());
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (XPathExpressionException e) {
            builder.append("XPATH"+ e.getMessage());
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        TextView tv = new TextView(this);
        tv.setText("before"+builder+"after");
        setContentView(tv);
        
        //
        // tv.setText("Hello, Android");
        
        //setContentView(R.layout.main);
    }
}
