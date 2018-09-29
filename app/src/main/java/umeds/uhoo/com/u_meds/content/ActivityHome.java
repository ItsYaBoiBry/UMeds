package umeds.uhoo.com.u_meds.content;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import umeds.uhoo.com.u_meds.R;
import umeds.uhoo.com.u_meds.functions.Session;

public class ActivityHome extends AppCompatActivity implements View.OnClickListener{
    Button logout;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        logout = findViewById(R.id.logout);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.logout:
                session = new Session(ActivityHome.this);
                session.Logout(ActivityHome.this);

        }
    }
}
