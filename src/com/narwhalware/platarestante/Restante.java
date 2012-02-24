package com.narwhalware.platarestante;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.NumberFormat;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Restante extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restante);

        int monto = Integer.parseInt(getMonto());

        NumberFormat nf = NumberFormat.getCurrencyInstance();

        ((TextView)findViewById(R.id.monto)).setText(nf.format(monto));

    }

    public String getMonto(){

        HttpURLConnection con = null;

        try{
            URL url = new URL("http://narwhalware.com/banred/FLowkKJAQwC.php");

            con = (HttpURLConnection) url.openConnection();
            con.setReadTimeout(10000);
            con.setConnectTimeout(15000);
            con.setRequestMethod("GET");

            con.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            String payload = reader.readLine();

            reader.close();
            return payload;


        } catch (UnsupportedEncodingException e) {

        } catch (IOException e) {

        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
        return "";
    }

}