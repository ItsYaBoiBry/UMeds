package umeds.uhoo.com.u_meds.functions;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import umeds.uhoo.com.u_meds.login.ActivityLogin;
import umeds.uhoo.com.u_meds.models.User;

public class Session {
    private SharedPreferences sp;
    private String session_id_key = "login_status";
    public int loggedin = 200;
    public int loggedout = 400;
    public int firsttime = 300;
    public Session(Context context){
        String session_name = "login";
        sp = context.getSharedPreferences(session_name,Context.MODE_PRIVATE);
    }

    public void StoreUser(User user){
        SharedPreferences.Editor  editor = sp.edit();
        editor.putInt(session_id_key,loggedin);
        editor.putInt(user.key_id,user.getId());
        editor.putString(user.key_firstname,user.getFirstname());
        editor.putString(user.key_lastname,user.getLastname());
        editor.putString(user.key_name,user.getName());
        editor.putString(user.key_dob,user.getDob());
        editor.putString(user.key_email,user.getEmail());
        editor.putString(user.key_mobile,user.getMobile());
        editor.putBoolean(user.key_subscribe,user.isSubscribe());
        editor.putString(user.key_firebase_token, user.getFirebasetoken());
        editor.apply();
    }
    public void Logout(Activity activity){
        SharedPreferences.Editor  editor = sp.edit();
        editor.clear();
        editor.putInt(session_id_key,loggedout);
        editor.apply();
        activity.startActivity(new Intent(activity.getApplicationContext(), ActivityLogin.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
    }
    public int getLoginStatus(){
        if(sp.contains(session_id_key)){
            return sp.getInt(session_id_key,-1);
        }else{
            return loggedout;
        }

    }
    public boolean getsplash(){
        String splash_color_key = "splash_color";
        //false = white, true = blue
        if (sp.contains(splash_color_key)){
            if(sp.getBoolean(splash_color_key,false)){
                SharedPreferences.Editor e = sp.edit();
                e.putBoolean(splash_color_key,false);
                e.apply();
                return true;
            }else{
                SharedPreferences.Editor e = sp.edit();
                e.putBoolean(splash_color_key,true);
                e.apply();
                return false;
            }
        }else{
            SharedPreferences.Editor e = sp.edit();
            e.putBoolean(splash_color_key,false);
            e.apply();
            return false;
        }
    }
}

