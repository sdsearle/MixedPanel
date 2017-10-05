package com.example.admin.mixedpanel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.mixpanel.android.mpmetrics.MixpanelAPI;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private static final String YOUR_PROJECT_TOKEN = "";
    private MixpanelAPI mixpanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String projectToken = YOUR_PROJECT_TOKEN; // e.g.: "1ef7e30d2a58d27f4b90c42e31d6d7ad"
        mixpanel = MixpanelAPI.getInstance(this, projectToken);

        // start the timer for the event "Image Upload"
        mixpanel.eventElapsedTime("MainActivityTime");

    }


    public void logAction(View view) {
        String activity = "";

        switch (view.getId()) {

            case R.id.btnJump:
                activity = "jump";
                trackMixpanel(activity);
                break;

            case R.id.btnRun:
                activity = "run";
                trackMixpanel(activity);
                break;

            case R.id.btnFly:
                activity = "fly";
                trackMixpanel(activity);
                break;

            case R.id.btnSleep:
                activity = "sleep";
                trackMixpanel(activity);
                break;

            case R.id.btnSecondActivity:
                // stop the timer if the imageUpload() method returns true

                trackTimeMixpanel("MainActivityTime");
                break;

        }
    }

    private void trackMixpanel(String activity) {
        try {
            JSONObject props = new JSONObject();
            props.put("Activity", activity);
            props.put("Logged in", false);
            mixpanel.track("MainActivity -" + activity + " called", props);
        } catch (JSONException e) {
            Log.e("MYAPP", "Unable to add properties to JSONObject", e);
        }
    }

    private void trackTimeMixpanel(String activity) {
        mixpanel.track(activity);
    }

}
