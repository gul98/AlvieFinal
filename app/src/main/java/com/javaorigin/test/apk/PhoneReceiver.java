package com.javaorigin.test.apk;

/**
 * Created by Smillle on 4/25/2015.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;

@SuppressWarnings("rawtypes")
public class PhoneReceiver extends BroadcastReceiver {



    
    private static final String TAG = "blockCall";

    @Override
        public void onReceive(Context context, Intent intent) {
        DatabaseHandler db=new DatabaseHandler(context);
    String temp="";
    String incomingNumber;
            if (!intent.getAction().equals("android.intent.action.PHONE_STATE")) return;
            String extraState = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            if (extraState.equals(TelephonyManager.EXTRA_STATE_RINGING)) {

                     incomingNumber =intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
                try {
                    FileInputStream fin = context.getApplicationContext().openFileInput("Specificnumber.txt");
                    int c;
                    while( (c = fin.read()) != -1){
                        temp = temp + Character.toString((char)c);
                    }

                    fin.close();
                }
                catch(FileNotFoundException j) {
                Log.d("File not found", "Exception occured in PhoneReciever");
                }
                catch(IOException e){
                    Log.d("Ioexception","Exception Occured in PhoneReciever.java");
                }
                    if(db.isContact(incomingNumber) || temp==incomingNumber)
                         terminateCall(context);

            }
        }

        private void terminateCall(Context context) {
                        try {
                Log.v(TAG, "Get getTeleService...");

                Object telephonyService = getTelephonyServiceObject(context);

                Class telephonyInterface = Class.forName("com.android.internal.telephony.ITelephony");


                if (telephonyService != null && telephonyInterface != null) {
                    getAndInvokeMethod(telephonyInterface, telephonyService, "silenceRinger");
                    getAndInvokeMethod(telephonyInterface, telephonyService, "endCall");
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(TAG, "FATAL ERROR: could not connect to telephony subsystem");
                Log.e(TAG, "Exception object: " + e);
            }
        }

        private Object getTelephonyServiceObject(Context context) {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

            try {
                Class c = Class.forName(tm.getClass().getName());
                Method m = c.getDeclaredMethod("getITelephony");
                m.setAccessible(true);
                return m.invoke(tm);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        private void getAndInvokeMethod(Class c, Object target, String methodName) {

            try {
                Method m = c.getMethod(methodName);
                m.invoke(target);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
