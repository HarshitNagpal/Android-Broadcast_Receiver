package com.harshit_sjsu.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    TextView textView2;
    TextView textView3;
    double currency = 0.0;
    String currencyType = "";
    public static String amountType = " ";

    CurrencyReceiver currencyReceiver = new CurrencyReceiver();
    BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter filter = new IntentFilter("android.intent.action.MAIN");
        this.registerReceiver(currencyReceiver,filter);

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Bundle bundle = intent.getExtras();
                if (bundle != null) {
//                    To calculate the conversion amount for respective currecncy
                    currency = Double.parseDouble(bundle.getString("USD Currency"));
                    currencyType = bundle.getString("Conversion Currency");

//                    Assign the input values to the TextView IDs on the screen of MainActivity
                    textView2 = (TextView) findViewById(R.id.textView2);
                    textView2.setText("Amount : " + (bundle.getString("USD Currency")));
                    textView3= (TextView) findViewById(R.id.textView3);
                    textView3.setText("Convert to : " + currencyType);
                }
            }
        };
        this.registerReceiver(broadcastReceiver, new IntentFilter("CurrencyReceiver"));
    }

    public double convertCurrency(double  amount, String convertTo){

        double conversionCalculation = 0.0;

        switch (convertTo){
            case "Indian Rupees":
                amountType = "INR";
                break;
            case "Australian Dollar":
                amountType = "AUD";
                break;
            case "Canadian Dollar":
                amountType = "CAD";
                break;
            case "Pound":
                amountType = "GBP";
                break;
            case "New Zealand Dollar":
                amountType = "NZD";
                break;
            case "Euro":
                amountType = "EUR";
                break;

        }

        getExchangePrice exchange  = new getExchangePrice();
        exchange.execute();
        conversionCalculation = amount * getExchangePrice.calculated_value;
        Log.i("input",String.valueOf(amount));
        Log.i("currency_exchange_value",String.valueOf(getExchangePrice.calculated_value));
        Log.i("convertedAmount",String.valueOf(conversionCalculation ));
        return conversionCalculation;
    }

    public void conversion(View v){
        Log.i("Values: ", String.valueOf(currency) + currencyType);
        String conversionCalculation = String.valueOf(convertCurrency(currency, currencyType));
        Intent intent = new Intent();
//       To identify which broadcast sends and make a particular receiver by matching this sender keyword
        intent.setAction("com.harshit_sjsu.currencyconverter");
//        To change the Android OS and make it compatible for all versions
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        intent.putExtra("Conversion",conversionCalculation);
        Log.i("Intent sent back: ",String.valueOf(intent.getExtras()));
        sendBroadcast(intent);
    }

    public void closeApp(View v){
        MainActivity.this.finish();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        this.unregisterReceiver(this.currencyReceiver);
        this.unregisterReceiver(this.broadcastReceiver);
    }
}
