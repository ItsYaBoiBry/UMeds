package umeds.uhoo.com.u_meds;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import umeds.uhoo.com.u_meds.content.ActivityHome;
import umeds.uhoo.com.u_meds.functions.Session;
import umeds.uhoo.com.u_meds.login.ActivityLogin;

public class MainActivity extends AppCompatActivity {
    LinearLayout background;
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        background = findViewById(R.id.background);
        logo = findViewById(R.id.logo);
        setuppage();

        int SPLASH_TIME_OUT = 2000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Session session = new Session(MainActivity.this);
                int status = session.getLoginStatus();
                if(status==session.loggedin){
                    startActivity(new Intent(MainActivity.this, ActivityHome.class));
                    finish();
                }else if(status==session.loggedout){
                    startActivity(new Intent(MainActivity.this, ActivityLogin.class));
                    finish();
                }else {
                    startActivity(new Intent(MainActivity.this, ActivityLogin.class));
                    Toast.makeText(MainActivity.this, "An unexpected error has occurred!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }, SPLASH_TIME_OUT);
    }
    public void setuppage(){
        Session s = new Session(MainActivity.this);
        if(s.getsplash()){
            background.setBackgroundColor(getResources().getColor(R.color.blue));
            logo.setImageResource(R.drawable.logo_white);
        }else{
            background.setBackgroundColor(getResources().getColor(R.color.white));
            logo.setImageResource(R.drawable.logo_blue);
        }
    }
}
