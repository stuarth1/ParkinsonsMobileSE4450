package com.stuart.parkinsonsandroidapplication;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import android.view.ViewGroup;

import com.stuart.parkinsonsandroidapplication.EndpointsAsyncTask;

import com.parkinsonsmobile.backend.userDataEndpoint.model.UserData;

import java.util.ArrayList;
import java.util.List;

public class PreviousData extends android.support.v4.app.Fragment implements AsyncResponse {

    private ListView lv;
    List<ListViewItem> item;

    EndpointsAsyncTask results = new EndpointsAsyncTask();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_previous_data, container, false);


        lv = (ListView) rootView.findViewById(R.id.listView);
        item = new ArrayList<ListViewItem>();


        final Button getData = (Button) rootView.findViewById(R.id.btnGetData);
        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                results.execute();
                //((MainActivity) getActivity()
            }
        });
        results.delegate = this;
        return rootView;
    }
    @Override
    public void processFinish(List<UserData> output){
        //display your data.
        int i=0;
        for (UserData q : output) {
            if(q.getSymptom()==1){
                String sympt = "Symptom: " + q.getSymptom();
                Log.i("here", sympt);
                i++;
                final int s = q.getSymptom();
                final int f = q.getFood();
                final int a = q.getActivity();
                final int j = i;
                final String comm = q.getComment();
                item.add(new ListViewItem(){{
                    ThumbnailResource = j;
                    title = "Tag: " + j;
                    subtitle = "Comment: " + comm + "Details: "+f + " : " + s + " : " + a;
                }
                });
            }
        }
        CustomListViewAdapter adapter = new CustomListViewAdapter(getActivity(), item);
        lv.setAdapter(adapter);
    }
    class ListViewItem{
        public int ThumbnailResource;
        public String title;
        public String subtitle;

    }
}
