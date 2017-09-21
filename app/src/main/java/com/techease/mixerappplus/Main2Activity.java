package com.techease.mixerappplus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Main2Activity extends AppCompatActivity {
    boolean isBackPressed=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Thread timer=new Thread(){
        public void run(){
            try{
                sleep(2000);
                Intent intent=new Intent(Main2Activity.this,MainActivity.class);
                startActivity(intent);
                finish();

            }catch (InterruptedException e)
            {
                e.printStackTrace();
            }


            }
        };
        timer.start();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        isBackPressed=true;
        finish();
    }
}
