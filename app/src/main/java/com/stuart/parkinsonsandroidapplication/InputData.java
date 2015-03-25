package com.stuart.parkinsonsandroidapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import android.view.ViewGroup;

import com.parkinsonsmobile.backend.quoteEndpoint.model.Quote;


public class InputData extends Fragment{

    private static int seekvalue = 10;
    private int temp1 = 0;
    private int temp2 = 0;
    private int temp3 = 0;
    EditText commentText;
    String []arrayActivity = {"Golf", "Squash", "Running"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_input_data, container, false);


        //This is for the FoodSeek bar grabs value and displays it on screen
        final SeekBar foodSeek = (SeekBar)rootView.findViewById(R.id.FoodSeekBar);
        final TextView foodText = (TextView) rootView.findViewById(R.id.FoodRating);
        foodSeek.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                temp1 = progress;
                String temp = progress + "/5";
                foodText.setText(temp);
                Log.i("Food",temp);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //the seekbar control and the changing of the variable for Activity Level
        final SeekBar activitySeek = (SeekBar) rootView.findViewById(R.id.ActivitySeekBar);
        final TextView activityText = (TextView) rootView.findViewById(R.id.ActivityRating);
        activitySeek.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                temp2 = progress;
                String temp = progress + "/5";
                activityText.setText(temp);
                Log.i("tag",temp);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //this is the seekbar that listens to the symtomSeverity seekbar and then re displays the value
        //beside it
        final SeekBar symptomSeek = (SeekBar) rootView.findViewById(R.id.FeelingSeekBar);
        final TextView symptomText = (TextView) rootView.findViewById(R.id.SymptomRating);
        symptomSeek.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                temp3 = progress;
                String temp = progress + "/5";
                symptomText.setText(temp);
                Log.i("Symptom" , temp);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        commentText = (EditText) rootView.findViewById(R.id.commentTxt);
        //Button that will take all values and submit it to server
        final Button submitButton = (Button) rootView.findViewById(R.id.SubmitInput);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = temp1 + " " + temp2 + " " + temp3;
                final int carry1 = temp1;
                final int carry2 = temp2;
                final int carry3 = temp3;
                Log.i("Values Submitted: ",temp);
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(Html.fromHtml("<font color='#FF7F27'>Activity Tag</font>"));
                builder.setItems(arrayActivity, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        submitButton.setText(arrayActivity[which]);
                        new InputAsyncTask(getActivity(),carry1,carry2,carry3,commentText.getText().toString()).execute();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        return rootView;

    }

}
