package com.sujeet.recordingandreporting;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MyBroadcastReceiver extends BroadcastReceiver {

    private Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        try {

            this.context = context;
            String url = "http://mypracticepage.comxa.com";

            if (isOnline()) {
                final DBHandler dbHandler = new DBHandler(context, null, null, 0);
                Cursor cr = dbHandler.getCursor("Annex3A");
                cr.moveToFirst();
                final Map<String, String> map = new HashMap<>();
                for (cr.moveToFirst(); !cr.isAfterLast(); cr.moveToNext()) {
                    map.put(DBHandler.COLSA[0], "" + cr.getLong(cr.getColumnIndex(DBHandler.COLSA[0])));
                    map.put(DBHandler.COLSA[1], cr.getString(cr.getColumnIndex(DBHandler.COLSA[1])));
                    map.put(DBHandler.COLSA[2], cr.getString(cr.getColumnIndex(DBHandler.COLSA[2])));
                    map.put(DBHandler.COLSA[3], cr.getString(cr.getColumnIndex(DBHandler.COLSA[3])));
                    map.put(DBHandler.COLSA[4], cr.getString(cr.getColumnIndex(DBHandler.COLSA[4])));
                    map.put(DBHandler.COLSA[5], cr.getString(cr.getColumnIndex(DBHandler.COLSA[5])));
                    map.put(DBHandler.COLSA[6], cr.getString(cr.getColumnIndex(DBHandler.COLSA[6])));
                    map.put(DBHandler.COLSA[7], cr.getString(cr.getColumnIndex(DBHandler.COLSA[7])));
                    map.put(DBHandler.COLSA[8], cr.getString(cr.getColumnIndex(DBHandler.COLSA[8])));
                    map.put(DBHandler.COLSA[9], cr.getString(cr.getColumnIndex(DBHandler.COLSA[9])));
                    map.put(DBHandler.COLSA[10], cr.getString(cr.getColumnIndex(DBHandler.COLSA[10])));
                    map.put(DBHandler.COLSA[11], cr.getString(cr.getColumnIndex(DBHandler.COLSA[11])));
                    map.put(DBHandler.COLSA[12], cr.getString(cr.getColumnIndex(DBHandler.COLSA[12])));
                    map.put(DBHandler.COLSA[13], cr.getString(cr.getColumnIndex(DBHandler.COLSA[13])));
                    map.put(DBHandler.COLSA[14], cr.getString(cr.getColumnIndex(DBHandler.COLSA[14])));

                    StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Scanner sc = new Scanner(response);
                            dbHandler.mark("Annex3A", sc.next());
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            return map;
                        }
                    };
                    if (isOnline())
                        MySingleton.getInstance(context.getApplicationContext()).addToRequestQueue(request);
                }
            }
            if (isOnline()) {
                final DBHandler dbHandler = new DBHandler(context, null, null, 0);
                Cursor cr = dbHandler.getCursor("Annex3B");
                cr.moveToFirst();
                final Map<String, String> map = new HashMap<>();
                for (cr.moveToFirst(); !cr.isAfterLast(); cr.moveToNext()) {
                    map.put(DBHandler.COLSB[0], "" + cr.getLong(cr.getColumnIndex(DBHandler.COLSB[0])));
                    map.put(DBHandler.COLSB[1], cr.getString(cr.getColumnIndex(DBHandler.COLSB[1])));
                    map.put(DBHandler.COLSB[2], cr.getString(cr.getColumnIndex(DBHandler.COLSB[2])));
                    map.put(DBHandler.COLSB[3], cr.getString(cr.getColumnIndex(DBHandler.COLSB[3])));
                    map.put(DBHandler.COLSB[4], cr.getString(cr.getColumnIndex(DBHandler.COLSB[4])));
                    map.put(DBHandler.COLSB[5], cr.getString(cr.getColumnIndex(DBHandler.COLSB[5])));
                    map.put(DBHandler.COLSB[6], cr.getString(cr.getColumnIndex(DBHandler.COLSB[6])));
                    map.put(DBHandler.COLSB[7], cr.getString(cr.getColumnIndex(DBHandler.COLSB[7])));
                    map.put(DBHandler.COLSB[8], cr.getString(cr.getColumnIndex(DBHandler.COLSB[8])));
                    map.put(DBHandler.COLSB[9], cr.getString(cr.getColumnIndex(DBHandler.COLSB[9])));
                    map.put(DBHandler.COLSB[10], cr.getString(cr.getColumnIndex(DBHandler.COLSB[10])));
                    map.put(DBHandler.COLSB[11], cr.getString(cr.getColumnIndex(DBHandler.COLSB[11])));
                    map.put(DBHandler.COLSB[12], cr.getString(cr.getColumnIndex(DBHandler.COLSB[12])));
                    map.put(DBHandler.COLSB[13], cr.getString(cr.getColumnIndex(DBHandler.COLSB[13])));


                    StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Scanner sc = new Scanner(response);
                            dbHandler.mark("Annex3B", sc.next());
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            return map;
                        }
                    };
                    if (isOnline())
                        MySingleton.getInstance(context.getApplicationContext()).addToRequestQueue(request);
                }
            }
        } catch (Exception e) {
            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

}
