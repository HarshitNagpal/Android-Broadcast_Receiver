package com.harshit_sjsu.currencyconverter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;



public class CurrencyReceiver extends BroadcastReceiver {
    String amount_data;
    String convert;

    @Override
    public void onReceive(Context context, Intent intent) {
        amount_data = intent.getStringExtra("USD Currency");
        convert = intent.getStringExtra("Conversion Currency");

        System.out.println(amount_data);
        System.out.println(convert);

        String log = "Action: " + intent.getAction() + "\n" +
                "Dollar Amount: " + amount_data + "\n" +
                "Convert To: " + convert + "\n";

        Log.d( "CurrencyReceiver",log);

        Toast.makeText(context, log, Toast.LENGTH_LONG).show();

//        Send the received data from MyBroadcastReceiver class to the MainActivity class
        Intent i = new Intent("CurrencyReceiver");
        i.putExtra("USD Currency",amount_data);
        i.putExtra("Conversion Currency",convert);
        context.sendBroadcast(i);
    }
}

