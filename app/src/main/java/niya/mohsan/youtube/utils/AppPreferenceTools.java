package niya.mohsan.youtube.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * for better management of preference in application
 * like authentication information
 */
public class AppPreferenceTools {

    private SharedPreferences mPreference;
    private Context mContext;
    public static final String STRING_PREF_UNAVAILABLE = "novideo";

    public AppPreferenceTools(Context context) {
        this.mContext = context;
        this.mPreference = this.mContext.getSharedPreferences("app_preference", Context.MODE_PRIVATE);
    }


    public void saveFavorite(String id) {
        mPreference.edit()
                .putString(id,id)
                .apply();
    }


    /**
     * get access token
     *
     * @return
     */
    public boolean getVideoFavorite(String id) {
        String value = mPreference.getString(id, STRING_PREF_UNAVAILABLE);
        Log.e("AppPreferencesToolas", value);
         if(value.equals("novideo")){
            return false;
        }else{
            return true;
        }

    }


    public void removeFavorite(String id) {
        mPreference.edit().remove(id).apply();

    }


    /**
     *
     */
    public void removeAllPrefs() {
        mPreference.edit().clear().apply();
    }





}
