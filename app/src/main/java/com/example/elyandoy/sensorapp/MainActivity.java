package com.example.elyandoy.sensorapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.elyandoy.sensorapp.Models.*;

import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;


public class MainActivity extends AppCompatActivity {
    // PROPERTIES
    // Victory alert dialog identifier
    public static final int VICTORY_DIALOG = 0;
    // Defeat alert dialog identifier
    public static final int DEFEAT_DIALOG = 1;

    // Game's graphics engine
    private LabyrintheView mView = null;
    // Game's physics engine
    private LabyrintheEngine mEngine = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mView = new LabyrintheView(this);
        setContentView(mView);

        mEngine = new LabyrintheEngine(this);

        Boule b = new Boule();
        mView.setBoule(b);
        mEngine.setBoule(b);

        List<Bloc> mList = mEngine.buildLabyrinthe();
        mView.setBlocks(mList);
    }

    /**
     * When app is resumed, we launch back physics engine
     */
    @Override
    protected void onResume() {
        super.onResume();
        mEngine.resume();
    }

    /**
     * When app is paused (leaving), we stop physics engine
     */
    @Override
    protected void onPause() {
        super.onPause();
        mEngine.stop();
    }

    /**
     * Create and display a dialog alert
     *
     * @param id
     * @return
     */
    @Override
    public Dialog onCreateDialog(int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        switch (id) {
            case VICTORY_DIALOG:
                builder.setCancelable(false)
                        .setMessage("Bravo, vous avez gagné !")
                        .setTitle("Champion ! Le roi des Zörglubienotchs est mort grâce à vous !")
                        .setNeutralButton("Recommencer", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // L'utilisateur peut recommencer s'il le veut
                                mEngine.reset();
                                mEngine.resume();
                            }
                        });
                break;

            case DEFEAT_DIALOG:
                builder.setCancelable(false)
                        .setMessage("La Terre a été détruite à cause de vos erreurs.")
                        .setTitle("Bah bravo !")
                        .setNeutralButton("Recommencer", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mEngine.reset();
                                mEngine.resume();
                            }
                        });
        }
        return builder.create();
    }

    /**
     * Stops physics engine each time a dialog alert is launched
     *
     * @param id
     * @param box
     */
    @Override
    public void onPrepareDialog(int id, Dialog box) {
        mEngine.stop();
    }
}
