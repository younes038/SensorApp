package com.example.elyandoy.sensorapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.elyandoy.sensorapp.Models.*;

import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;


public class MainActivity extends AppCompatActivity {
    // Identifiant de la boîte de dialogue de victoire
    public static final int VICTORY_DIALOG = 0;
    // Identifiant de la boîte de dialogue de défaite
    public static final int DEFEAT_DIALOG = 1;

    // Le moteur graphique du jeu
    private LabyrintheView mView = null;
    // Le moteur physique du jeu
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

    @Override
    protected void onResume() {
        super.onResume();
        mEngine.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mEngine.stop();
    }

    @Override
    public Dialog onCreateDialog (int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        switch(id) {
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

    @Override
    public void onPrepareDialog (int id, Dialog box) {
        // A chaque fois qu'une boîte de dialogue est lancée, on arrête le moteur physique
        mEngine.stop();
    }
}
