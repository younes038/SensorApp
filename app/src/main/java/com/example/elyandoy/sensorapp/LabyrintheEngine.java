package com.example.elyandoy.sensorapp;

import java.util.ArrayList;
import java.util.List;

import com.example.elyandoy.sensorapp.Models.*;
import com.example.elyandoy.sensorapp.Models.Bloc.Type;
import android.app.Service;
import android.graphics.RectF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class LabyrintheEngine {
    // PROPERTIES
    private Boule mBoule = null;
    public Boule getBoule() {
        return mBoule;
    }

    public void setBoule(Boule pBoule) {
        this.mBoule = pBoule;
    }

    // The labyrinth
    private List<Bloc> mBlocks = null;

    private MainActivity mActivity = null;

    private SensorManager mManager = null;
    private Sensor mAccelerometre = null;

    SensorEventListener mSensorEventListener = new SensorEventListener() {

        @Override
        public void onSensorChanged(SensorEvent pEvent) {
            float x = pEvent.values[0];
            float y = pEvent.values[1];

            if(mBoule != null) {
                // updates ball coordinates
                RectF hitBox = mBoule.putXAndY(x, y);

                for(Bloc block : mBlocks) {
                    // creates a new rectangle to not modify the one of the bloc
                    RectF inter = new RectF(block.getRectangle());
                    if(inter.intersect(hitBox)) {
                        switch(block.getType()) {
                            case TROU:
                                mActivity.showDialog(MainActivity.DEFEAT_DIALOG);
                                break;

                            case DEPART:
                                break;

                            case ARRIVEE:
                                mActivity.showDialog(MainActivity.VICTORY_DIALOG);
                                break;
                        }
                        break;
                    }
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor pSensor, int pAccuracy) {

        }
    };

    public LabyrintheEngine(MainActivity pView) {
        mActivity = pView;
        mManager = (SensorManager) mActivity.getBaseContext().getSystemService(Service.SENSOR_SERVICE);
        mAccelerometre = mManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    /**
     * Put the ball to the beginning
     */
    public void reset() {
        mBoule.reset();
    }

    /**
     * Stops sensor
     */
    public void stop() {
        mManager.unregisterListener(mSensorEventListener, mAccelerometre);
    }

    /**
     * Launches sensor back
     */
    public void resume() {
        mManager.registerListener(mSensorEventListener, mAccelerometre, SensorManager.SENSOR_DELAY_GAME);
    }

    /**
     * Build the labyrinth
     * @return Array of Bloc that represents the labyrinth
     */
    public List<Bloc> buildLabyrinthe() {
        mBlocks = new ArrayList<Bloc>();
        mBlocks.add(new Bloc(Type.TROU, 0, 0));
        mBlocks.add(new Bloc(Type.TROU, 0, 1));
        mBlocks.add(new Bloc(Type.TROU, 0, 2));
        mBlocks.add(new Bloc(Type.TROU, 0, 3));
        mBlocks.add(new Bloc(Type.TROU, 0, 4));
        mBlocks.add(new Bloc(Type.TROU, 0, 5));
        mBlocks.add(new Bloc(Type.TROU, 0, 6));
        mBlocks.add(new Bloc(Type.TROU, 0, 7));
        mBlocks.add(new Bloc(Type.TROU, 0, 8));
        mBlocks.add(new Bloc(Type.TROU, 0, 9));
        mBlocks.add(new Bloc(Type.TROU, 0, 10));
        mBlocks.add(new Bloc(Type.TROU, 0, 11));
        mBlocks.add(new Bloc(Type.TROU, 0, 12));
        mBlocks.add(new Bloc(Type.TROU, 0, 13));

        mBlocks.add(new Bloc(Type.TROU, 1, 0));
        mBlocks.add(new Bloc(Type.TROU, 1, 13));

        mBlocks.add(new Bloc(Type.TROU, 2, 0));
        mBlocks.add(new Bloc(Type.TROU, 2, 13));

        mBlocks.add(new Bloc(Type.TROU, 3, 0));
        mBlocks.add(new Bloc(Type.TROU, 3, 13));

        mBlocks.add(new Bloc(Type.TROU, 4, 0));
        mBlocks.add(new Bloc(Type.TROU, 4, 1));
        mBlocks.add(new Bloc(Type.TROU, 4, 2));
        mBlocks.add(new Bloc(Type.TROU, 4, 3));
        mBlocks.add(new Bloc(Type.TROU, 4, 4));
        mBlocks.add(new Bloc(Type.TROU, 4, 5));
        mBlocks.add(new Bloc(Type.TROU, 4, 6));
        mBlocks.add(new Bloc(Type.TROU, 4, 7));
        mBlocks.add(new Bloc(Type.TROU, 4, 8));
        mBlocks.add(new Bloc(Type.TROU, 4, 9));
        mBlocks.add(new Bloc(Type.TROU, 4, 10));
        mBlocks.add(new Bloc(Type.TROU, 4, 13));

        mBlocks.add(new Bloc(Type.TROU, 5, 0));
        mBlocks.add(new Bloc(Type.TROU, 5, 13));

        mBlocks.add(new Bloc(Type.TROU, 6, 0));
        mBlocks.add(new Bloc(Type.TROU, 6, 13));

        mBlocks.add(new Bloc(Type.TROU, 7, 0));
        mBlocks.add(new Bloc(Type.TROU, 7, 1));
        mBlocks.add(new Bloc(Type.TROU, 7, 2));
        mBlocks.add(new Bloc(Type.TROU, 7, 5));
        mBlocks.add(new Bloc(Type.TROU, 7, 6));
        mBlocks.add(new Bloc(Type.TROU, 7, 9));
        mBlocks.add(new Bloc(Type.TROU, 7, 10));
        mBlocks.add(new Bloc(Type.TROU, 7, 11));
        mBlocks.add(new Bloc(Type.TROU, 7, 12));
        mBlocks.add(new Bloc(Type.TROU, 7, 13));

        mBlocks.add(new Bloc(Type.TROU, 8, 0));
        mBlocks.add(new Bloc(Type.TROU, 8, 5));
        mBlocks.add(new Bloc(Type.TROU, 8, 9));
        mBlocks.add(new Bloc(Type.TROU, 8, 13));

        mBlocks.add(new Bloc(Type.TROU, 9, 0));
        mBlocks.add(new Bloc(Type.TROU, 9, 5));
        mBlocks.add(new Bloc(Type.TROU, 9, 9));
        mBlocks.add(new Bloc(Type.TROU, 9, 13));

        mBlocks.add(new Bloc(Type.TROU, 10, 0));
        mBlocks.add(new Bloc(Type.TROU, 10, 5));
        mBlocks.add(new Bloc(Type.TROU, 10, 9));
        mBlocks.add(new Bloc(Type.TROU, 10, 13));

        mBlocks.add(new Bloc(Type.TROU, 11, 0));
        mBlocks.add(new Bloc(Type.TROU, 11, 5));
        mBlocks.add(new Bloc(Type.TROU, 11, 9));
        mBlocks.add(new Bloc(Type.TROU, 11, 13));

        mBlocks.add(new Bloc(Type.TROU, 12, 0));
        mBlocks.add(new Bloc(Type.TROU, 12, 1));
        mBlocks.add(new Bloc(Type.TROU, 12, 2));
        mBlocks.add(new Bloc(Type.TROU, 12, 3));
        mBlocks.add(new Bloc(Type.TROU, 12, 4));
        mBlocks.add(new Bloc(Type.TROU, 12, 5));
        mBlocks.add(new Bloc(Type.TROU, 12, 9));
        mBlocks.add(new Bloc(Type.TROU, 12, 8));
        mBlocks.add(new Bloc(Type.TROU, 12, 13));

        mBlocks.add(new Bloc(Type.TROU, 13, 0));
        mBlocks.add(new Bloc(Type.TROU, 13, 8));
        mBlocks.add(new Bloc(Type.TROU, 13, 13));

        mBlocks.add(new Bloc(Type.TROU, 14, 0));
        mBlocks.add(new Bloc(Type.TROU, 14, 8));
        mBlocks.add(new Bloc(Type.TROU, 14, 13));

        mBlocks.add(new Bloc(Type.TROU, 15, 0));
        mBlocks.add(new Bloc(Type.TROU, 15, 8));
        mBlocks.add(new Bloc(Type.TROU, 15, 13));

        mBlocks.add(new Bloc(Type.TROU, 16, 0));
        mBlocks.add(new Bloc(Type.TROU, 16, 4));
        mBlocks.add(new Bloc(Type.TROU, 16, 5));
        mBlocks.add(new Bloc(Type.TROU, 16, 6));
        mBlocks.add(new Bloc(Type.TROU, 16, 7));
        mBlocks.add(new Bloc(Type.TROU, 16, 8));
        mBlocks.add(new Bloc(Type.TROU, 16, 9));
        mBlocks.add(new Bloc(Type.TROU, 16, 13));

        mBlocks.add(new Bloc(Type.TROU, 17, 0));
        mBlocks.add(new Bloc(Type.TROU, 17, 13));

        mBlocks.add(new Bloc(Type.TROU, 18, 0));
        mBlocks.add(new Bloc(Type.TROU, 18, 13));

        mBlocks.add(new Bloc(Type.TROU, 19, 0));
        mBlocks.add(new Bloc(Type.TROU, 19, 1));
        mBlocks.add(new Bloc(Type.TROU, 19, 2));
        mBlocks.add(new Bloc(Type.TROU, 19, 3));
        mBlocks.add(new Bloc(Type.TROU, 19, 4));
        mBlocks.add(new Bloc(Type.TROU, 19, 5));
        mBlocks.add(new Bloc(Type.TROU, 19, 6));
        mBlocks.add(new Bloc(Type.TROU, 19, 7));
        mBlocks.add(new Bloc(Type.TROU, 19, 8));
        mBlocks.add(new Bloc(Type.TROU, 19, 9));
        mBlocks.add(new Bloc(Type.TROU, 19, 10));
        mBlocks.add(new Bloc(Type.TROU, 19, 11));
        mBlocks.add(new Bloc(Type.TROU, 19, 12));
        mBlocks.add(new Bloc(Type.TROU, 19, 13));

        Bloc b = new Bloc(Type.DEPART, 2, 2);
        mBoule.setInitialRectangle(new RectF(b.getRectangle()));
        mBlocks.add(b);

        mBlocks.add(new Bloc(Type.ARRIVEE, 8, 11));

        return mBlocks;
    }

}
