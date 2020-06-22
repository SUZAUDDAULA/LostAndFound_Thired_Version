package com.opus_bd.lostandfound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.opus_bd.lostandfound.Utils.Constants;
import com.opus_bd.lostandfound.Utils.LocaleHelper;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.opus_bd.lostandfound.sharedPrefManager.SharedPrefManager.KEY_State;
import static com.opus_bd.lostandfound.sharedPrefManager.SharedPrefManager.SHARED_PREF_NAME;

public class HomeActivity extends AppCompatActivity {
    boolean isChecked = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);


    }

    @Override
    protected void attachBaseContext(Context base) {
        SharedPreferences tprefs = base.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        boolean language = tprefs.getBoolean(KEY_State, true);
        if (language)
            super.attachBaseContext(LocaleHelper.setLocale(base, Constants.ENGLISH));
        else
            super.attachBaseContext(LocaleHelper.setLocale(base, Constants.BANGLA));
    }

    private void changeLanguage(String language) {
        LocaleHelper.setLocale(HomeActivity.this, language);
        recreate();
    }

    private boolean getSharedPrefValue() {
        SharedPreferences tprefs = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        return tprefs.getBoolean(KEY_State, true);
    }

    private void saveIntoSharedPref(boolean isLanguageEnglish) {
        SharedPreferences.Editor editor = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE).edit();
        editor.putBoolean(KEY_State, isLanguageEnglish);
        editor.apply();
    }

    @OnClick(R.id.button1)
    public void button1() {
        isChecked = getSharedPrefValue();
        isChecked = false;
        saveIntoSharedPref(false);

        changeLanguage(Constants.BANGLA);
         /*setLocal(Constants.BANGLA);
        loadLocal();*/
        //Intent intent = new Intent(HomeActivity.this, GPHomeActivity.class);

      //  startActivity(intent);
    }

    @OnClick(R.id.button2)
    public void button2() {
        isChecked = getSharedPrefValue();
        isChecked = true;
        saveIntoSharedPref(true);

        changeLanguage(Constants.ENGLISH);
        /*setLocal(Constants.ENGLISH);
        loadLocal();*/
        /*Intent intent = new Intent(HomeActivity.this, GPHomeActivity.class);
        startActivity(intent);*/
    }

   /* private void setLocal(String lang) {

        Locale locale=new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration= new Configuration();
        configuration.locale=locale;
        getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor=getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("Language",lang);
        editor.apply();

        Utilities.showLogcatMessage(" Local "+lang);
    }

    public void loadLocal(){
        SharedPreferences preferences=getSharedPreferences("Settings",MODE_PRIVATE);
        String language= preferences.getString("Language","");
        setLocal(language);
        Utilities.showLogcatMessage(" Local language"+language);
    }*/

}
