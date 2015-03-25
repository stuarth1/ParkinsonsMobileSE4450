package com.stuart.parkinsonsandroidapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class ReminderPage extends Fragment {

    TextView text;
    Button start;
    Button reset;
    int hour = 3;
    int minute = 60;
    int second = 60;
    String timeCount = "";
    ////////////THIS IS FOR THE TIMER//////////////
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        public void run() {
            afficher();
        }
    };
    ////////////////////////////////////////////////
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_reminder_page, container, false);

        //////This is also for the timer////////
        //Where to set the timer hopefully it works
        text = (TextView)rootView.findViewById(R.id.counter);

        start = (Button)rootView.findViewById((R.id.startButton));
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                runnable.run();
        }
        });

        reset = (Button)rootView.findViewById(R.id.restartTimer);
        ///////Here we need to set CountDown to what the time is in settings
        reset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                hour = 3;
                minute = 60;
                second = 60;
        }
        });

        return rootView;

    }
    public void afficher()
    {
        if(second!=0) {
            second--;
        }
        else if(second==0 && minute != 0 && hour !=0){
            minute--;
            second = 60;
        }
        else if(second==0 && minute == 0 && hour !=0){
            hour--;
            second = 60;
            minute = 60;
        }
        String sec = String.valueOf(second);
        String min = String.valueOf(minute);
        String hou = String.valueOf(hour);
        timeCount = hou+" : " + min + " : " + sec;
        text.setText(timeCount);
        handler.postDelayed(runnable, 1000);
    }

}

