package com.gototongcheng.cookies;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by admin on 16/6/14.
 */
public class MyIniClass {
    public static final String MyPREFERENCES = "MyPrefs" ;

    private SharedPreferences sharedpreferences = null;

    public MyIniClass(Context FContext, String IniFileName){
        super();

        if(IniFileName.isEmpty())
        {
            sharedpreferences = FContext.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        }
        else{
            sharedpreferences = FContext.getSharedPreferences(IniFileName, Context.MODE_PRIVATE);
        }
    }

    public String ReadString(String KeyStr, String DefualtStr){

        if (sharedpreferences != null)
        {
            return sharedpreferences.getString(KeyStr, DefualtStr);
        }
        else{
            return "";
        }
    }

    public int ReadInteger(String KeyStr, int DefualtValue){

        if (sharedpreferences != null)
        {
            return sharedpreferences.getInt(KeyStr, DefualtValue);
        }
        else{
            return 0;
        }
    }

    public Long ReadLong(String KeyStr, Long DefualtValue){

        if (sharedpreferences != null)
        {
            return sharedpreferences.getLong(KeyStr, DefualtValue);
        }
        else{
            return (long)0;
        }
    }

    public boolean Readbool(String KeyStr, boolean DefualtValue){

        if (sharedpreferences != null)
        {
            return sharedpreferences.getBoolean(KeyStr, DefualtValue);
        }
        else{
            return false;
        }
    }

    public void WriteString(String KeyStr, String DefualtStr){

        if (sharedpreferences != null)
        {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString(KeyStr, DefualtStr);
            editor.commit();
        }
    }

    public void WriteInteger(String KeyStr, int DefualtStr){

        if (sharedpreferences != null)
        {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putInt(KeyStr, DefualtStr);
            editor.commit();
        }
    }

    public void WriteBoolean(String KeyStr, boolean DefualtStr){

        if (sharedpreferences != null)
        {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putBoolean(KeyStr, DefualtStr);
            editor.commit();
        }
    }
}
