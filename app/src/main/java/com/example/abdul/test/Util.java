package com.example.abdul.test;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.content.DialogInterface;
import android.content.res.Resources;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Created by abdul on 4/22/2016.
 */
public class Util {

    public static void showAlert(String title, String msg, Activity act)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(act).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(msg);
        //alertDialog.
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public static void showAlert_GoPreviousActivity(String title, String msg, final Activity act)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(act).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(msg);
        //alertDialog.
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        act.finish();
                    }
                });
        alertDialog.show();
    }

    public static JSONObject getJSONObject(String jsonString)
    {
        JSONParser parser = new JSONParser();
        Object parsedJson;
        try {
            parsedJson = parser.parse(jsonString);
            JSONObject obj = (JSONObject) parsedJson;
            return obj;

        }
        catch(ParseException e)
        {
            ;
        }
        return new JSONObject();
    }

}
