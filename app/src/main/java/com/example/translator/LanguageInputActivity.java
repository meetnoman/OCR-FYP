package com.example.translator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.translator.Helper.LanguageRemindHelper;
import com.google.android.gms.dynamic.IFragmentWrapper;

public class LanguageInputActivity extends AppCompatActivity {
    Button english,urdu,chinesse,detect;
    String intentString;
    boolean detectLang,lang1,lang2,lang3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_input);
        detect=(Button)findViewById(R.id.Detect);
        english=(Button)findViewById(R.id.English);
        urdu=(Button)findViewById(R.id.Urdu);
        chinesse=(Button)findViewById(R.id.Chinese);

        Intent intent=getIntent();
        intentString=intent.getStringExtra("requestActivity");


        openActivity();
        setCheck();

    }
    public void SelectInputLanguage(View view){
        boolean connection=false;
        if (view==detect){
            if (!isOnline(getApplicationContext())){
                Toast.makeText(getApplicationContext(),"Internet is not connected",Toast.LENGTH_SHORT).show();
            }else {
                connection=true;
                detectLang=true;

                lang1=false;
                lang2=false;
                lang3=false;
            }
        }
        if (view==english){
             if (!isOnline(getApplicationContext())){
                Toast.makeText(getApplicationContext(),"Internet is not connected",Toast.LENGTH_SHORT).show();
            }else {
                 lang1=true;
                connection = true;

                 detectLang=false;
                 lang2=false;
                 lang3=false;
             }

        }else if (view==urdu){
            if (!isOnline(getApplicationContext())){
                Toast.makeText(getApplicationContext(),"Internet is not connected",Toast.LENGTH_SHORT).show();
            }else {
                lang2=true;
                connection=true;

                detectLang=false;
                lang1=false;
                lang3=false;
            }
        }
        else if (view==chinesse){
            if (!isOnline(getApplicationContext())){
                Toast.makeText(getApplicationContext(),"Internet is not connected",Toast.LENGTH_SHORT).show();
            }else {
                connection=true;
                lang3=true;

                detectLang=false;
                lang1=false;
                lang2=false;
             }
        }
        setCheck();

        if (connection){
        goTobackActivity();
    }
    }



    public void goTobackActivity(){
        if (intentString.equalsIgnoreCase("A")){
            Intent intent=new Intent(this,TextAugmenter.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            this.startActivity(intent);

        }
        else if (intentString.equalsIgnoreCase("B")){
            Intent intent=new Intent(this,CameraViewActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            this.startActivity(intent);

        }
        else if (intentString.equalsIgnoreCase("C")){
            Intent intent=new Intent(this,LiveTextTranslation.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            this.startActivity(intent);
        }
        else {

        }
        finish();
    }


    public static boolean isOnline(Context context) {
        boolean result = false;
        if (context != null) {
            final ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cm != null) {
                final NetworkInfo networkInfo = cm.getActiveNetworkInfo();
                if (networkInfo != null) {
                    result = networkInfo.isConnected();
                }
            }
        }
        return result;
    }

    public  void  setCheck(){
        if (detectLang){
            LanguageRemindHelper.getInstance().setInputLanguage("Detect Language");
            detect.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_check, 0);

            english.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,0,0);
            chinesse.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,0,0);
            urdu.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,0,0);
        }
        else if (lang1){
            LanguageRemindHelper.getInstance().setInputLanguage("en");
            english.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_check, 0);

            chinesse.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0);
            urdu.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0);
            detect.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0);
        }
        else if (lang2){
            LanguageRemindHelper.getInstance().setInputLanguage("ur");
            urdu.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_check, 0);

            english.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0);
            chinesse.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0);
            detect.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0);

        }
        else if (lang3){
            LanguageRemindHelper.getInstance().setInputLanguage("zh");
            chinesse.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_check, 0);

            english.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0);
            urdu.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0);
            detect.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0);

        }

    }


    public void openActivity(){
        String language=LanguageRemindHelper.getInstance().getInputLanguage();
        if (language.equalsIgnoreCase("Detect Language")){
            detectLang=true;



        }
       else if (language.equalsIgnoreCase("en")){
            lang1=true;


        }
        else if (language.equalsIgnoreCase("ur")){
            lang2=true;


        }
       else if (language.equalsIgnoreCase("zh")){
            lang3=true;


        }

    }


}
