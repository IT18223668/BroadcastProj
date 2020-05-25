package com.example.broadcastproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public final static String BROADCAST_ACTION = "android.intent.action.MAIN";

    TextView TxtV;
    Button Brdcst;
    Receiver localListner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TxtV=findViewById(R.id.textView);
        Brdcst=findViewById(R.id.StartBroadcast);
    }

    @Override
    protected void onStart() {
        super.onStart();

        localListner =new Receiver();
        IntentFilter intentFilter=new IntentFilter(BROADCAST_ACTION);
        this.registerReceiver(localListner,intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.unregisterReceiver(localListner);
    }
    public void onClick(View view){
        if(view.getId()==R.id.StartBroadcast){
            BackgroundServices.startAction(this.getApplicationContext());
        }

    }

    public class Receiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            String currentText=TxtV.getText().toString();
            String message=intent.getStringExtra("value");
            currentText  += "\nRecieved " + message;
            TxtV.setText(currentText);
        }
    }
}




