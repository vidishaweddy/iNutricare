package com.example.abdul.test;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.widget.EditText;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import dmax.dialog.SpotsDialog;


public class MainActivity extends AppCompatActivity {

    private Handler hd;
    final String [] response = new String[1];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = (TextView) findViewById(R.id.tvRegister);
        tv.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);

            }
        });



        Button bt = (Button) findViewById(R.id.btLogin);
        assert bt != null;
        bt.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                final String username = ((EditText) findViewById(R.id.username2)).getText().toString();
                final String password = ((EditText) findViewById(R.id.password2)).getText().toString();
//-------------------- To be Removed
                Intent intent = new Intent(MainActivity.this, MainMenu.class);

                /// 2. put key/value data
                //intent.putExtra("cid", obj.get("cid").toString());
                //intent.putExtra("fName", obj.get("fName").toString());
                /*Bundle extras = new Bundle();
                EditText et = (EditText) findViewById(R.id.editText1);
                extras.putString("fName", et.getText().toString());
                et = (EditText) findViewById(R.id.editText2);
                extras.putString("lName", et.getText().toString());

                // 3. or you can add data to a bundle

                extras.putString("msg", "Hello Abdulaziz");

                // 4. add bundle to intent
                intent.putExtras(extras);

                // 5. start the activity*/
                startActivity(intent);
                //-------------------- Till here
                if(username.length() == 0 || password.length() == 0) {
                    Util.showAlert("Error", "Fill all the data", MainActivity.this);
                    return;
                }

                final WebServicesCallers service = new WebServicesCallers();


                final AlertDialog pd;
                pd = new SpotsDialog(MainActivity.this, R.style.Custom);
                pd.setCancelable(false);
                pd.show();

                final Thread t1 = new Thread(new Runnable() {
                    public void run() {
                        response[0] = service.Get(WebServicesCallers.baseURL+"/iNutriCareWebServices/rest/login/" + username + "/"+password );
                        hd.sendEmptyMessage(0);
                    }
                });
                t1.start();

                hd = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        pd.dismiss();
                        if(response[0].length() == 3)
                        {
                            Util.showAlert("Alert", "Unable to connect to the server", MainActivity.this);
                            //return;

                        }
                        else if(response[0].equals("-3"))
                        {
                            Util.showAlert("Error", "Invalide Credential !!", MainActivity.this);
                            //return;
                        }
                        else if(response[0].equals("-1"))
                        {
                            Util.showAlert("Error", "Unknown Error (" + response[0] + ")", MainActivity.this);
                            //return;
                        }
                        else {
                            JSONParser parser = new JSONParser();
                            Object parsedJson;

                            try {
                                //Util.showAlert("", response[0], MainActivity.this);
                                parsedJson = parser.parse(response[0]);
                                JSONObject obj = (JSONObject) parsedJson;
                                Intent intent = new Intent(MainActivity.this, MainMenu.class);

                                /// 2. put key/value data
                                intent.putExtra("cid", obj.get("cid").toString());
                                intent.putExtra("fName", obj.get("fName").toString());
                /*Bundle extras = new Bundle();
                EditText et = (EditText) findViewById(R.id.editText1);
                extras.putString("fName", et.getText().toString());
                et = (EditText) findViewById(R.id.editText2);
                extras.putString("lName", et.getText().toString());

                // 3. or you can add data to a bundle

                extras.putString("msg", "Hello Abdulaziz");

                // 4. add bundle to intent
                intent.putExtras(extras);

                // 5. start the activity*/
                               startActivity(intent);
                           } catch (ParseException e) {
                                ;
                           }
                        }
                    }
                };
            }
        });


    }


}
