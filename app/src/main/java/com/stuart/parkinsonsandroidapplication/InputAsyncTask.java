package com.stuart.parkinsonsandroidapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import com.parkinsonsmobile.backend.userDataEndpoint.UserDataEndpoint;
import com.parkinsonsmobile.backend.userDataEndpoint.model.UserData;

import com.stuart.parkinsonsandroidapplication.MainActivity;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Created by Stuart on 2015-03-02.
 */
public class InputAsyncTask  extends AsyncTask<String, Void, UserData> {
    private static UserDataEndpoint myApiService = null;
    private Context context3;
    private int temp1;
    private int temp2;
    private int temp3;
    private String temp4;


    InputAsyncTask(Context temp, int userData1, int userData2, int userData3, String userData4) {
        context3 = temp;
        temp1 = userData1;
        temp2 = userData2;
        temp3 = userData3;
        temp4 = userData4;
    }

    @Override
    protected UserData doInBackground(String... params) {
        UserData response = null;
        UserData insertData = new UserData();


        if(myApiService == null) { // Only do this once
            UserDataEndpoint.Builder builder = new UserDataEndpoint.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://parkinsonsmobile.appspot.com/_ah/api/");
            // end options for devappserver
            myApiService = builder.build();
        }

        insertData.setFood(temp1);
        insertData.setActivity(temp2);
        insertData.setSymptom(temp3);
        insertData.setComment(temp4);

        String inputVar = temp1 + " " + temp2 + " " + temp3 + " " + temp4;

        Log.i("Variables inputted",inputVar);
        try {
            return myApiService.insertUserData(insertData).execute();
        } catch (IOException e) {
            return response;
        }
    }

    protected void onPostExecute(UserData postData) {
        Toast.makeText(context3, postData.getFood() + " : " + postData.getActivity() + " : " + postData.getSymptom() + " : " + postData.getComment(), Toast.LENGTH_LONG).show();

    }
}