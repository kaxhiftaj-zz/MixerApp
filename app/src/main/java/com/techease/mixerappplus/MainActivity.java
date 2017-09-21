package com.techease.mixerappplus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText editTextUrl;
    Spinner spinner;
    public String tempUrl;
    public String item, prefUrl;
    Button buttongo;
    public static boolean flag = false;
    String save;
    String value,navitem;
    SharedPreferences sharedPreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        //saving a text in edittext
        editTextUrl=(EditText)findViewById(R.id.etUrl);
        Intent intent = getIntent();
        save=getIntent().getStringExtra("tempUrl");
        navitem=getIntent().getStringExtra("item");
        editTextUrl.setText(save);

        spinner=(Spinner)findViewById(R.id.sp);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                item = spinner.getSelectedItem().toString();
                Log.d("zma item create", item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

            buttongo = (Button) findViewById(R.id.btnGo);
            buttongo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedPreferences.Editor   editor = getSharedPreferences(MyPREFERENCES,MODE_PRIVATE).edit();
                    Intent intent = new Intent(getApplicationContext(), MixerApp.class);
                    tempUrl = editTextUrl.getText().toString();
                  //  Log.d("zma item", item + "\n" + tempUrl);
                    editor.putString("tempUrl",tempUrl);
                    editor.putString("item",item);
                    editor.commit();
                    intent.putExtra("item", item);
                    intent.putExtra("tempUrl", tempUrl);
                    startActivity(intent);
                    finish();
                }
            });

        SharedPreferences pref = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        prefUrl = pref.getString("tempUrl", "");
        value   = pref.getString("item","");
        Log.d("zma pref ao value", value+prefUrl);

        if (!prefUrl.equals("") && !flag){
            Intent inti = new Intent(getApplicationContext(), MixerApp.class);
            sharedPreferences=getSharedPreferences(MyPREFERENCES,MODE_PRIVATE);
            editTextUrl.setText(sharedPreferences.getString("tempUrl",""));
            inti.putExtra("item", String.valueOf(sharedPreferences.getString("item",item)));
            inti.putExtra("tempUrl", String.valueOf(sharedPreferences.getString("tempUrl",tempUrl)));
            Log.d("zma item", item + "\n" + tempUrl);
            startActivity(inti);
            finish();
        }
    }
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
         item=spinner.getSelectedItem().toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}
