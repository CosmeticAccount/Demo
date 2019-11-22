package com.isolsgroup.demo;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class PrefernceSettings {
    private static SharedPreferences myPrefs;
    private static SharedPreferences.Editor prefsEditor;

    public static void openDataBase(Context context) {
        try {
            context=context.getApplicationContext();
            myPrefs = context.getSharedPreferences("Reload", context.MODE_PRIVATE);
            prefsEditor = myPrefs.edit();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static String getQuantity() {
        String i = myPrefs.getString("KEY_Quantity"," ");
        Log.e("dfg", i);
        return i;
    }
    public static void setQuantity(String m) {
        Log.e("dfgdfd", m);
        prefsEditor.putString("KEY_Quantity",m);
        prefsEditor.commit();
    }
    public static String getMax() {
        String i = myPrefs.getString("KEY_Max"," ");
        Log.e("dfg", i);
        return i;
    }
    public static void setMax(String m) {
        Log.e("dfgdfd", m);
        prefsEditor.putString("KEY_Max",m);
        prefsEditor.commit();
    }
}
