package umeds.uhoo.com.u_meds.content;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Set;

import umeds.uhoo.com.u_meds.R;

public class ActivityViewProfile extends AppCompatActivity {
    Toolbar toolbar;
    TextView tab_patients_item, tab_documents_item;
    LinearLayout personal_details_tab, documents_tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        Declare();
        SetupTab();
    }
    public void Declare(){
        toolbar = findViewById(R.id.toolbar);
        SetupToolbar();
        tab_documents_item = findViewById(R.id.tab_documents_item);
        tab_patients_item = findViewById(R.id.tab_patients_item);
        personal_details_tab = findViewById(R.id.personal_details_tab);
        documents_tab = findViewById(R.id.documents_tab);
    }
    public void SetupToolbar(){
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void SetupTab(){
        //default
        tab_documents_item.setBackground(getDrawable(R.drawable.profile_tab_normal));
        tab_patients_item.setBackground(getDrawable(R.drawable.profile_tab_selected));
        personal_details_tab.setVisibility(View.VISIBLE);
        documents_tab.setVisibility(View.GONE);
        //onclick
        tab_documents_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tab_documents_item.setBackground(getDrawable(R.drawable.profile_tab_selected));
                tab_patients_item.setBackground(getDrawable(R.drawable.profile_tab_normal));
                personal_details_tab.setVisibility(View.GONE);
                documents_tab.setVisibility(View.VISIBLE);
            }
        });
        tab_patients_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tab_documents_item.setBackground(getDrawable(R.drawable.profile_tab_normal));
                tab_patients_item.setBackground(getDrawable(R.drawable.profile_tab_selected));
                personal_details_tab.setVisibility(View.VISIBLE);
                documents_tab.setVisibility(View.GONE);
            }
        });
    }
}
