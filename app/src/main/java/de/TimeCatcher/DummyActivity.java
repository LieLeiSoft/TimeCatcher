package de.TimeCatcher;

import de.TimeCatcher.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
@SuppressWarnings("unused")
public class DummyActivity extends Activity 
{
    @Override
    
	// ******************************************************************************************
	// ******************* onCreate - Layout wird erstellt **************************************
	// ******************************************************************************************
    
    public void onCreate(Bundle savedInstanceState)
    {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.dummy);
        EditText et = null;
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(et, InputMethodManager.SHOW_FORCED);


    	
    } // public void onCreate
    
	@Override
	
	// ******************************************************************************************
	// ******************* onResume - Layout wird angezeigt *************************************
	// ******************************************************************************************
    
	public void onResume()
	{
		super.onResume();
		

	} // public void onResume

    	
    
	// ******************************************************************************************
	// ******************* Button Berechne ******************************************************
	// ******************************************************************************************
    
	public void btnBerechne(View v)
	{
    	EditText et;
    	TextView tv;
    	String strText;
    	int resId;	
    	int intStd_A;
    	int intMin_A;
    	int intStd_E;
    	int intMin_E;
    	int intSumme_E;
    	int intSumme_A;
    	int intZeitraum;
    	int intStunde;
    	int intMinute;
    	int intGStunde = 0;
    	int intGMinute = 0;
    	int intGTag = 0;
    	int intGesamtzeitraum = 0;
    	
    	
    	// Auslesen der Eingabefelder
    	
	    for (int x=1; x<=4; x++)
	    {
	    	intStd_A = 0;
	    	intMin_A = 0;
	    	intStd_E = 0;
	    	intMin_E = 0;
	    	
	    	resId = getResources().getIdentifier("et_"+x+"_01", "id", getPackageName());
	    	et = (EditText) findViewById(resId);
	    	strText = et.getText().toString();
	    	if (strText.equals("") == false)
	    	{
	    		intStd_A = Integer.parseInt(strText);
	    	}
	    	
	    	resId = getResources().getIdentifier("et_"+x+"_02", "id", getPackageName());
	    	et = (EditText) findViewById(resId);
	    	strText = et.getText().toString();
	    	if (strText.equals("") == false)
	    	{
	    		intMin_A = Integer.parseInt(strText);
	    	}
	    	
	    	resId = getResources().getIdentifier("et_"+x+"_03", "id", getPackageName());
	    	et = (EditText) findViewById(resId);
	    	strText = et.getText().toString();
	    	if (strText.equals("") == false)
	    	{
	    		intStd_E = Integer.parseInt(strText);
	    	}
	    	
	    	resId = getResources().getIdentifier("et_"+x+"_04", "id", getPackageName());
	    	et = (EditText) findViewById(resId);
	    	strText = et.getText().toString();
	    	if (strText.equals("") == false)
	    	{
	    		intMin_E = Integer.parseInt(strText);
	    	}
	    	
	    	// Berechnung des Zeitraumes
    	
	    	intSumme_E = intStd_E * 60 + intMin_E;
	    	intSumme_A = intStd_A * 60 + intMin_A;
    	
	    	if(intSumme_A > intSumme_E)
	    	{
	    		intZeitraum = (1440 - intSumme_A) + intSumme_E;   
	    	}
	    	else
	    	{
	    		intZeitraum = intSumme_E - intSumme_A;   // Wenn Berechnung 0 bis 23:59
	    	}
    	
	    	// Gesamtzeitraum
	    	
	    	intGesamtzeitraum = intGesamtzeitraum + intZeitraum;
	    	
	    	intGTag = (int) Math.floor(intGesamtzeitraum / 1440); // zum Runden auf die nächst-niedrigere Ganzzahl
	    	
	    	intGStunde = (int) Math.floor((intGesamtzeitraum - 1440 * intGTag) / 60); // zum Runden auf die nächst-niedrigere Ganzzahl
	    	  
	    	intGMinute = Math.round((intGesamtzeitraum - 1440 * intGTag) - 60 * intGStunde);	
		
	    	if(intGMinute == 60)
	    	{
	    		intGStunde = intGStunde + 1;
	    		intGMinute = 0;
	    	}
    		
	    	// *******************************************
	    		    	
	    	intStunde = (int) Math.floor(intZeitraum / 60);	// zum Runden auf die nächst-niedrigere Ganzzahl	
  
	    	intMinute = Math.round(intZeitraum - 60 * intStunde);	
		
	    	if(intMinute == 60)
	    	{
	    		intStunde = intStunde + 1;
	    		intMinute = 0;
	    	}
		
		
	    	// Ausgabe des Ergebnisses
	    	
	    	resId = getResources().getIdentifier("tvZeitraumStd_"+x, "id", getPackageName());
	    	tv = (TextView) findViewById(resId);
	    	strText = Integer.toString(intStunde);
	    	tv.setText(strText);
	    	
	    	resId = getResources().getIdentifier("tvZeitraumMin_"+x, "id", getPackageName());
	    	tv = (TextView) findViewById(resId);
	    	strText = Integer.toString(intMinute);
	    	tv.setText(strText);
	    }
	   
	    tv = (TextView) findViewById(R.id.tvGZ_Tag);
	    strText = Integer.toString(intGTag);
	    tv.setText(strText);
	    
	    tv = (TextView) findViewById(R.id.tvGZ_Std);
	    strText = Integer.toString(intGStunde);
	    tv.setText(strText);
	    
	    tv = (TextView) findViewById(R.id.tvGZ_Min);
	    strText = Integer.toString(intGMinute);
	    tv.setText(strText);

    } // btnWeiter
}