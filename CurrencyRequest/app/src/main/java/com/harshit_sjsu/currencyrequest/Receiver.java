package com.harshit_sjsu.currencyrequest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class Receiver extends BroadcastReceiver {
    String calculated_Currency;

    @Override
    public void onReceive(Context context, Intent intent) {
        calculated_Currency = intent.getStringExtra("Conversion");
        String log = "Action: " + intent.getAction() + "\n" +
                "Converted Amount: " + calculated_Currency + "\n";
        Log.d( "Receiver",log);
        Toast.makeText(context, log, Toast.LENGTH_LONG).show();

//        Send the received data from MyBroadcastReceiver class to the MainActivity class
        Intent i = new Intent("Receiver");
        i.putExtra("Conversion", calculated_Currency);
        context.sendBroadcast(i);
    }
}