package com.example;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

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
        builder.append("Before query");
        try {
            URL weatherURL = new URL("http://www.google.com/ig/api?weather=43202");
            URLConnection connection = weatherURL.openConnection();

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;

            while((inputLine = br.readLine()) != null ) {
                builder.append(inputLine);
            }
            br.close();



        } catch (MalformedURLException e) {
            builder.append("Malformed url: "+ e.getMessage());
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            builder.append("IO exception"+ e.getMessage());
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        builder.append("After query");

        TextView tv = new TextView(this);
        tv.setText(builder);
        //
        // tv.setText("Hello, Android");
        setContentView(tv);
        //setContentView(R.layout.main);
    }
}
