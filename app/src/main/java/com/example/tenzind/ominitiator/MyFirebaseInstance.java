package com.example.tenzind.ominitiator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by tenzind on 12/1/2017.
 */

public class MyFirebaseInstance extends FirebaseInstanceIdService {
    private static final String TAG="MyFirebaseInstance";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG,"'New Token :"+refreshedToken);
    }
}
