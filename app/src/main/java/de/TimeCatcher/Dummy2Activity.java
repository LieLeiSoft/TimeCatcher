package de.TimeCatcher;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.String;
import java.lang.Object;


@SuppressWarnings("unused")
public class Dummy2Activity extends Activity {
	int intGesamtMinutenSumme = 0;

	// Storage Permissions
	private static final int REQUEST_EXTERNAL_STORAGE = 1;
	private static String[] PERMISSIONS_STORAGE = {
			Manifest.permission.READ_EXTERNAL_STORAGE,
			Manifest.permission.WRITE_EXTERNAL_STORAGE
	};


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

		/*
		FileWriter datei;
		FileWriter fw;

		fw = new FileWriter(filePath);
		try {
			fw.write("Test1");
		} finally {
			fw.close();
		}

		fw = new FileWriter("fw.txt");
		try {
			fw.write("Test2");
		} finally {
			fw.close();
		}


		datei = new FileWriter(filePath);

		String data = "Ship Name";
		datei.write(data);

		datei.close();
		*/
	}

	public static void Save(File file, String[] data)
	{
		FileOutputStream fos = null;
		try
		{
			fos = new FileOutputStream(file);
		}
//		catch (FileNotFoundException e) {e.printStackTrace();}
		catch (IOException e)
		{
			String errormsg = e.getMessage();
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

	/**
	 * Checks if the app has permission to write to device storage
	 *
	 * If the app does not has permission then the user will be prompted to grant permissions
	 *
	 * @param activity
	 */
	/*
	public static void verifyStoragePermissions(Activity activity) {
		// Check if we have write permission
		int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

		if (permission != PackageManager.PERMISSION_GRANTED) {
			// We don't have permission so prompt the user
			ActivityCompat.requestPermissions(
					activity,
					PERMISSIONS_STORAGE,
					REQUEST_EXTERNAL_STORAGE
			);
		}
	}
	*/
}