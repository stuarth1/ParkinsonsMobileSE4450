package com.stuart.parkinsonsandroidapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import com.parkinsonsmobile.backend.quoteEndpoint.QuoteEndpoint;
import com.parkinsonsmobile.backend.quoteEndpoint.model.Quote;
import com.parkinsonsmobile.backend.userDataEndpoint.UserDataEndpoint;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import com.parkinsonsmobile.backend.userDataEndpoint.UserDataEndpoint;
import com.parkinsonsmobile.backend.userDataEndpoint.model.UserData;
import com.stuart.parkinsonsandroidapplication.AsyncResponse;

class EndpointsAsyncTask extends AsyncTask<Void, Void, List<UserData>> {
    private static UserDataEndpoint myApiService = null;

    public AsyncResponse delegate=null;

    @Override
    protected List<UserData> doInBackground(Void... params) {
        if(myApiService == null) { // Only do this once
            UserDataEndpoint.Builder builder = new UserDataEndpoint.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://parkinsonsmobile.appspot.com/_ah/api/");
                    // end options for devappserver

            myApiService = builder.build();
        }

        try {
            return myApiService.listUserData().execute().getItems();
        } catch (IOException e) {
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    protected void onPostExecute(List<UserData> result) {

        delegate.processFinish(result);
    }

}