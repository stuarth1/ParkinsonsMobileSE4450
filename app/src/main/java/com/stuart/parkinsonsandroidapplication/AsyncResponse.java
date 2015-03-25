package com.stuart.parkinsonsandroidapplication;

import com.parkinsonsmobile.backend.userDataEndpoint.model.UserData;

import java.util.List;

/**
 * Created by Stuart Hunter on 3/24/2015.
 */
public interface AsyncResponse {
    void processFinish(List<UserData> output);
}
