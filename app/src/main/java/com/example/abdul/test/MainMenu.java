package com.example.abdul.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.view.View.OnClickListener;

/**
 * Created by Vidi on 4/25/2016.
 */
public class MainMenu extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);

        Util.showAlert("Welcome", getIntent().getStringExtra("fName"), MainMenu.this);

        ImageButton bt1 = (ImageButton) findViewById(R.id.imageButton);
        assert bt1 != null;
        if (bt1 != null)
            bt1.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainMenu.this, maintenance.class);
                    startActivity(intent);
                }
            });

        ImageButton bt2 = (ImageButton) findViewById(R.id.imageButton2);
        assert bt2 != null;
        if (bt2 != null)
            bt2.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainMenu.this, MainPatient.class);
                    startActivity(intent);
                }
            });

        ImageButton bt3 = (ImageButton) findViewById(R.id.imageButton3);
        assert bt3 != null;
        if (bt3 != null)
            bt3.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainMenu.this, MainMeals.class);
                    startActivity(intent);
                }
            });

        ImageButton bt4 = (ImageButton) findViewById(R.id.imageButton4);
        assert bt4 != null;
        if (bt4 != null)
            bt4.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainMenu.this, maintenance.class);
                    startActivity(intent);
                }
            });

        ImageButton bt5 = (ImageButton) findViewById(R.id.imageButton5);
        assert bt5 != null;
        if (bt5 != null)
            bt5.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainMenu.this, MainConsumption.class);
                    startActivity(intent);
                }
            });

        ImageButton bt6 = (ImageButton) findViewById(R.id.imageButton6);
        assert bt6 != null;
        if (bt6 != null)
            bt6.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainMenu.this, MainFoods.class);
                    startActivity(intent);
                }
            });
    }
}
