package com.harshit_sjsu.currencyconverter;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class getExchangePrice extends AsyncTask<Void,Void,Void> {


    String response ="";
    public static double calculated_value;

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("https://api.exchangeratesapi.io/latest?base=USD");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String nextresponse = "";
            while(nextresponse != null){
                nextresponse = bufferedReader.readLine();
                response = response + nextresponse;
            }

            Log.d("ResponseReceived",response);
            JSONObject reader = new JSONObject(response);
            JSONObject sys  = reader.getJSONObject("rates");
            calculated_value = Double.parseDouble(sys.getString(MainActivity.amountType));
            Log.i("currencyexchangevalue",String.valueOf(calculated_value));


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        //MainActivity.data.setText(this.dataParsed);

    }

}
