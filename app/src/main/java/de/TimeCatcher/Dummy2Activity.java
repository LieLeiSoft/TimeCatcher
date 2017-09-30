package de.TimeCatcher;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v4.content.ContextCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class Dummy2Activity extends Activity {
	int intGesamtMinutenSumme = 0;

	@Override

	// ******************************************************************************************
	// ******************* onCreate - Layout wird erstellt **************************************
	// ******************************************************************************************

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dummy2);
		EditText et = null;
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(et, InputMethodManager.SHOW_FORCED);

		if (shouldAskPermissions())
		{
			askPermissions();
		}
	} // public void onCreate

	@Override

	// ******************************************************************************************
	// ******************* onResume - Layout wird angezeigt *************************************
	// ******************************************************************************************

	public void onResume() {
		super.onResume();


	} // public void onResume


	// ******************************************************************************************
	// ******************* Button Berechne ******************************************************
	// ******************************************************************************************

	public void btnBerechne(View v) {
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
		if (strText.equals("") == false) {
			intUhrzeitStunde = Integer.parseInt(strText);
		}

		et = (EditText) findViewById(R.id.et_1_02);
		strText = et.getText().toString();
		if (strText.equals("") == false) {
			intUhrzeitMinute = Integer.parseInt(strText);
		}

		et = (EditText) findViewById(R.id.et_1_03);
		strText = et.getText().toString();
		if (strText.equals("") == false) {
			intZeitraumStunde = Integer.parseInt(strText);
		}

		et = (EditText) findViewById(R.id.et_1_04);
		strText = et.getText().toString();
		if (strText.equals("") == false) {
			intZeitraumMinute = Integer.parseInt(strText);
		}

		// **************************************************
		// ********** Berechnung der Enduhrzeit  ************
		// **************************************************

		intGesamtMinuten = (intUhrzeitStunde * 60 + intUhrzeitMinute) + (intZeitraumStunde * 60 + intZeitraumMinute);

		intGesamtMinutenSumme = intGesamtMinutenSumme + intGesamtMinuten;

		intGesamtMinuten = intGesamtMinutenSumme;

		if (intGesamtMinuten >= 1440) {
			intGesamtTage = (int) Math.floor(intGesamtMinuten / 1440);
			intGesamtMinuten = intGesamtMinuten - (intGesamtTage * 1440);
		}

		if (intGesamtMinuten >= 60) {
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


	} // btnWeiter

	// ******************************************************************************************
	// ******************* Button Berechne ******************************************************
	// ******************************************************************************************

	public void btnCSV(View v) throws IOException {
		String baseDir = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
		String fileName = "AnalysisData.csv";
		String filePath = baseDir + File.separator + fileName;

		File datei = new File (filePath);

		String [] saveText = new String[] {"Hallo"};

		Save (datei, saveText);
	}

	public static void Save(File file, String[] data)
	{
		FileOutputStream fos = null;
		try
		{
			Log.i("Dummy2Activity", "Save: Datei öffnen" );
			fos = new FileOutputStream(file);
			Log.i("Dummy2Activity", "Save: Datei geöffnet" );
		}
		catch (IOException e)
		{
			Log.i("Dummy2Activity", "Save: " + e.getMessage());
			e.printStackTrace();
		}
		try
		{
			try
			{
				for (int i = 0; i<data.length; i++)
				{
					fos.write(data[i].getBytes());
					if (i < data.length-1)
					{
						fos.write("\n".getBytes());
					}
				}
			}
			catch (IOException e) {e.printStackTrace();}
		}
		finally
		{
			try
			{
				fos.close();
			}
			catch (IOException e) {e.printStackTrace();}
		}
	}

	protected boolean shouldAskPermissions() {
		if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1) {
			return false;
		}
		int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
		return  (permission != PackageManager.PERMISSION_GRANTED);
	} // shouldAskPermissions

	@TargetApi(23)
	protected void askPermissions()
	{
		String[] permissions = {
				"android.permission.READ_EXTERNAL_STORAGE",
				"android.permission.WRITE_EXTERNAL_STORAGE"
		};
		// Check if we have write permission
		int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

		if (permission != PackageManager.PERMISSION_GRANTED) {
			// We don't have permission so prompt the user
			ActivityCompat.requestPermissions(
					this, permissions, 1);
		}
	} // askPermissions

}