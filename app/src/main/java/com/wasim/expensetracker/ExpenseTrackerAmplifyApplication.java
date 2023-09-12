package com.wasim.expensetracker;


import android.app.Application;
import android.util.Log;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;

public class ExpenseTrackerAmplifyApplication extends Application {
    public static final String TAG = "LOOK AT EXPENSE TRACKER APPLICATION.JAVA";


    @Override
    public void onCreate() {
        super.onCreate();

        try {
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.addPlugin(new AWSApiPlugin());
//            Amplify.addPlugin(new AWSPredictionsPlugin());
//            Amplify.addPlugin(new AWSPinpointAnalyticsPlugin());
//            Amplify.addPlugin(new AWSS3StoragePlugin());
            Amplify.configure(getApplicationContext());
        } catch(AmplifyException amplifyException) {
            Log.e(TAG, "Error initializing AMPLIFY: " + amplifyException.getMessage(), amplifyException);
        }
    }

}
