package com.example.anirudhs.myproject;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anirudhs.myproject.core.BaseFragment;

/**
 * A very basic fragment that shows some app information at first opening
 */
public class WelcomeFragment extends BaseFragment implements SensorEventListener{
private SensorManager mSensorManager;
    TextView myText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



View rootView = inflater.inflate(R.layout.fragment_welcome, container, false);
        initializeRootViews(rootView);
        return rootView;

    }

    private void initializeRootViews(View rootView) {
        myText = (TextView)rootView.findViewById(R.id.myText);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

mSensorManager = (SensorManager) this.getActivity().getSystemService(Activity.SENSOR_SERVICE);


        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0], y = event.values[1],z=event.values[2];
        myText.setText("X = " + x + "\n" + "Y =" + y + "\n" + "Z = " + z);
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if(mSensorManager == null) {
            return;
        }

        if(menuVisible) {
            this.registerSensorListener();
        } else {
            this.unregisterSensorListener();
        }
    }
    @Override
    public void onStart() {
        super.onStart();

        if(this.getUserVisibleHint()) {
            this.registerSensorListener();
        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    @Override
    public void onStop() {
        super.onStop();
        this.unregisterSensorListener();
    }

    private void registerSensorListener() {
        mSensorManager.registerListener(this, mSensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0), SensorManager.SENSOR_DELAY_FASTEST);
    }

    private void unregisterSensorListener() {
        mSensorManager.unregisterListener(this);
    }
}
