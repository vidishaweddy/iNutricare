package com.example.abdul.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by Vidi on 4/25/2016.
 */
public class MainPatient extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu_patient);

        ImageButton bt1 = (ImageButton) findViewById(R.id.imageButton12);
        assert bt1 != null;
        if (bt1 != null)
            bt1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainPatient.this, addPatient.class);
                    startActivity(intent);
                }
            });
    }

}
