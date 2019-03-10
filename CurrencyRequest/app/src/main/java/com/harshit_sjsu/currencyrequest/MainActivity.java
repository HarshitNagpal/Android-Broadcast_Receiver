package com.harshit_sjsu.currencyrequest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Spinner spinner;
    TextView textView;
    BroadcastReceiver broadcastR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        broadcastR = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Bundle b = intent.getExtras();
                if (b != null) {
//                    Assign the input values to the TextView ID on the screen of MainActivity
                    textView = (TextView) findViewById(R.id.textView);
                    String display ="USD amount $" + editText.getText().toString() + " converted to "+  b.getString("Conversion") + " "+ spinner.getSelectedItem().toString();
                    textView.setText(display);
                    Log.i("output: ",display);
                }
            }
        };
        this.registerReceiver(broadcastR, new IntentFilter("Receiver"));
    }

    public void convertAmount(View v){
        editText = (EditText) findViewById(R.id.editText);
        spinner = (Spinner) findViewById(R.id.spinner);

        Intent intent = new Intent();
        intent.setAction("com.harshit_sjsu");
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);                        // To change the Android OS and make it compatible for all versions
        intent.putExtra("USD Currency", editText.getText().toString());
        intent.putExtra("Conversion Currency", spinner.getSelectedItem().toString());
        Log.i("Intent sent: ",String.valueOf(intent.getExtras()));
        sendBroadcast(intent);
    }

    public void onClose(View v){
        MainActivity.this.finish();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        this.unregisterReceiver(this.broadcastR);
    }
}
