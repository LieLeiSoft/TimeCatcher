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
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
@SuppressWarnings("unused")
public class Dummy2Activity extends Activity 
{
	int intGesamtMinutenSumme = 0;
	
	@Override
    
	// ******************************************************************************************
	// ******************* onCreate - Layout wird erstellt **************************************
	// ******************************************************************************************
    
    public void onCreate(Bundle savedInstanceState)
    {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.dummy2);
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
		int intUhrzeitStunde = 0;
		int intUhrzeitMinute = 0;
		int intZeitraumStunde = 0;
		int intZeitraumMinute = 0;
		int intGesamtTage = 0;
		int intGesamtStunden = 0;
		int intGesamtMinuten = 0;
		String strText;
		
		
		// **************************************************
		// ********** Auslesen der Eingabefelder ************
		// **************************************************
		
		et = (EditText) findViewById(R.id.et_1_01);
		strText = et.getText().toString();
	   	if (strText.equals("") == false)
	   	{
	   		intUhrzeitStunde = Integer.parseInt(strText) ;
	   	}
				
		et = (EditText) findViewById(R.id.et_1_02);
		strText = et.getText().toString();
	   	if (strText.equals("") == false)
	   	{
	   		intUhrzeitMinute = Integer.parseInt(strText) ;
	   	}
		
		et = (EditText) findViewById(R.id.et_1_03);
		strText = et.getText().toString();
	   	if (strText.equals("") == false)
	   	{
	   		intZeitraumStunde = Integer.parseInt(strText) ;
	   	}
		
		et = (EditText) findViewById(R.id.et_1_04);
		strText = et.getText().toString();
	   	if (strText.equals("") == false)
	   	{
	   		intZeitraumMinute = Integer.parseInt(strText) ;
	   	}
		
		// **************************************************
		// ********** Berechnung der Enduhrzeit  ************
		// **************************************************

		intGesamtMinuten = (intUhrzeitStunde * 60 + intUhrzeitMinute) + (intZeitraumStunde * 60 + intZeitraumMinute);
		
		intGesamtMinutenSumme = intGesamtMinutenSumme + intGesamtMinuten;
		
		intGesamtMinuten = intGesamtMinutenSumme;
				
		if (intGesamtMinuten >= 1440)
		{
			intGesamtTage = (int) Math.floor(intGesamtMinuten / 1440);
			intGesamtMinuten = intGesamtMinuten - (intGesamtTage * 1440);
		}
		
		if (intGesamtMinuten >= 60)
		{
			intGesamtStunden = (int) Math.floor(intGesamtMinuten / 60);
			intGesamtMinuten = intGesamtMinuten - (intGesamtStunden * 60);
		}
		
		// **************************************************
		// ************* Ausgabe der Enduhrzeit  ************
		// **************************************************
				
		
		tv = (TextView) findViewById(R.id.tvEnduhrzeitTag);
		tv.setText("+ " + Integer.toString(intGesamtTage));

		tv = (TextView) findViewById(R.id.tvEnduhrzeitStd);
		tv.setText(Integer.toString(intGesamtStunden));
		
		tv = (TextView) findViewById(R.id.tvEnduhrzeitMin);
		tv.setText(":" + Integer.toString(intGesamtMinuten));
		
		
		et = (EditText) findViewById(R.id.et_1_01);
		et.setText("");
    	et.setHintTextColor(getResources().getColor(R.color.cSchwarz));
    	et.setHint(Integer.toString(intGesamtStunden));
		
		et = (EditText) findViewById(R.id.et_1_02);
		et.setText("");
    	et.setHintTextColor(getResources().getColor(R.color.cSchwarz));
    	et.setHint(Integer.toString(intGesamtMinuten));
		
		
		
		
		/*
		EditText et;
    	TextView tv;
    	TableLayout tl;
    	String strText;
    	int x = 1;
    	int resId;	
    	int intEingabe;			// Zählt die eingegebenen Eingabefelder 3 bis 4
    	int intStd_A;
    	int intMin_A;
    	int intStd_ZR;
    	int intMin_ZR;
    	int intSumme_ZR;
    	int intSumme_A;
    	int intSumme_E = 0;
    	int intSummeMinuten = 0;
    	int intSummeStunden = 0;
    	int intSummeTag = 0;
    	int intZeitraum = 0;
    	int intStunde;
    	int intMinute;
    	int intGStunde = 0;
    	int intGMinute = 0;
    	int intGTag = 0;
    	int intGesamtzeitraum = 0;
    	
    	
    	// Auslesen der Eingabefelder
    	
	    intStd_A = 0;
	    intMin_A = 0;
	    intStd_ZR = 0;
	    intMin_ZR = 0;
	    intEingabe = 0;
	    	
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
	    	intStd_ZR = Integer.parseInt(strText);
	   		intEingabe ++;
	   	}
	    	
	   	resId = getResources().getIdentifier("et_"+x+"_04", "id", getPackageName());
	   	et = (EditText) findViewById(resId);
	   	strText = et.getText().toString();
	   	if (strText.equals("") == false)
	   	{
	    	intMin_ZR = Integer.parseInt(strText);
	    	intEingabe ++;
	    }
	    	
	    if (intEingabe == 2) // Die Berechnung und Ausgabefelder werden nur mit Werten befüllt, wenn die Eingabefelder mit Werten befüllt wurden
	    {
	    	
	    		// Berechnung des Endzeitpunktes
    	
	    		intSumme_ZR = intStd_ZR * 60 + intMin_ZR;
	    		intSumme_A = intStd_A * 60 + intMin_A;
	    		intSumme_E = intSumme_ZR + intSumme_A;
	    		intGesamtSumme_E = intGesamtSumme_E + intSumme_E;
    	
	    		// ********************************************
	    		// ********* Berechnung Anzahl Tage ***********
	    		// ********************************************
	    	
	    		if(intGesamtSumme_E > 1439)
	    		{
	    			intSummeTag = (int) Math.floor(intGesamtSumme_E / 1440);  // zum Runden auf die nächst-niedrigere Ganzzahl
	    			intGesamtSumme_E = intGesamtSumme_E - intSummeTag * 1440;
	    		}
	    	
	    		// ********************************************
	    		// ********* Berechnung Anzahl Stunden ********
	    		// ********************************************
	    	
	    		if(intGesamtSumme_E > 59)
	    		{
	    			intSummeStunden = (int) Math.floor(intGesamtSumme_E / 60);  // zum Runden auf die nächst-niedrigere Ganzzahl
	    			intGesamtSumme_E = Math.round(intGesamtSumme_E - intSummeStunden * 60);
	    		}


		
	    		// **********************************************
	    		// ******** Ausgabe des Ergebnisses *************
	    		// **********************************************
	    
	    		resId = getResources().getIdentifier("tvEndzeitpunktTag_"+x, "id", getPackageName());
		    	tv = (TextView) findViewById(resId);
		    	strText = Integer.toString(intSummeTag);
		    	if(intSummeTag > 0)
		    	{
		    		strText = "+" + strText;
		    	}
		    	tv.setText(strText);
		    	
		    	resId = getResources().getIdentifier("tvEndzeitpunktStd_"+x, "id", getPackageName());
		    	tv = (TextView) findViewById(resId);
		    	strText = Integer.toString(intSummeStunden);
		    	tv.setText(strText + ":");
		    	
		    	resId = getResources().getIdentifier("tvEndzeitpunktMin_"+x, "id", getPackageName());
		    	tv = (TextView) findViewById(resId);
		    	strText = Integer.toString(intGesamtSumme_E);
		    	tv.setText(strText);
		    	
		    	// Ausgabe der Urzeit als Hinttext in die nächsten Eingabefelder
		    	if(x<4)
		    	{
		    		int y = x + 1; // Es muss mit einer zweiten Variable gearbeitet werden x + 1 wird im Package Name nicht akzeptiert.
		    		
		    		// Layout wird sichtbar gemacht
		    		resId = getResources().getIdentifier("tlEndzeitpunkt"+y, "id", getPackageName());
		    		TableLayout tl1 = (TableLayout) findViewById(resId);
		    		tl1.setVisibility(View.VISIBLE);
		   		
		    		resId = getResources().getIdentifier("et_"+y+"_01", "id", getPackageName());
			    	et = (EditText) findViewById(resId);
			    	strText = Integer.toString(intSummeStunden);
			    	et.setHintTextColor(getResources().getColor(R.color.cWeiss));
			    	et.setHint(strText);
			    	
			    	resId = getResources().getIdentifier("et_"+y+"_02", "id", getPackageName());
			    	et = (EditText) findViewById(resId);
			    	strText = Integer.toString(intGesamtSumme_E);
			    	et.setHintTextColor(getResources().getColor(R.color.cWeiss));
			    	et.setHint(strText);
			    	
			    	resId = getResources().getIdentifier("et_"+y+"_03", "id", getPackageName());
			    	et = (EditText) findViewById(resId);
					et.requestFocus();
		    	}
	    	}
	    }*/
    } // btnWeiter
}