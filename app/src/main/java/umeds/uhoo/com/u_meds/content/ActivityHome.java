package umeds.uhoo.com.u_meds.content;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import umeds.uhoo.com.u_meds.R;
import umeds.uhoo.com.u_meds.functions.Session;
import umeds.uhoo.com.u_meds.mainFragments.FragmentCalendar;
import umeds.uhoo.com.u_meds.mainFragments.FragmentHistory;
import umeds.uhoo.com.u_meds.mainFragments.FragmentHome;
import umeds.uhoo.com.u_meds.mainFragments.FragmentSettings;

public class ActivityHome extends AppCompatActivity implements View.OnClickListener {
    Button logout;
    Session session;
    FragmentTransaction ft;
    FrameLayout content;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    TextView toolbarTitle;
    ImageView closeNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        declare();
        configToolbar();
        if (savedInstanceState == null) {
            replacefragment(new FragmentHome(),"Home");
        }
    }

    private void declare() {
        content = findViewById(R.id.content);
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        toolbarTitle = findViewById(R.id.toolbar_tv_title);
        logout = findViewById(R.id.logout);
        closeNav = findViewById(R.id.close_nav);
    }

    private void configToolbar(){
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    CloseDrawer();
                } else {
                    OpenDrawer();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logout:
                session = new Session(ActivityHome.this);
                session.Logout(ActivityHome.this);
                break;
            case R.id.btn_view_profile:
                startActivity(new Intent(ActivityHome.this, ActivityViewProfile.class));
                break;
            case R.id.close_nav:
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    CloseDrawer();
                } else {
                    OpenDrawer();
                }
                break;
            case R.id.navbtn_home:
                replacefragment(new FragmentHome(),"Home");
                CloseDrawer();
                break;
            case R.id.navbtn_calendar:
                replacefragment(new FragmentCalendar(),"Calendar");
                CloseDrawer();
                break;
            case R.id.navbtn_history:
                replacefragment(new FragmentHistory(),"History");
                CloseDrawer();
                break;
            case R.id.navbtn_settings:
                replacefragment(new FragmentSettings(),"Settings");
                CloseDrawer();
                break;

        }
    }

    private void replacefragment(Fragment fragment,String title) {
        toolbarTitle.setText(title);
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content, fragment);
        ft.commit();
    }

    private void CloseDrawer() {
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    private void OpenDrawer() {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            CloseDrawer();
        }else{
            finish();
        }
    }
}
