package umeds.uhoo.com.u_meds.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import umeds.uhoo.com.u_meds.R;
import umeds.uhoo.com.u_meds.connectivity.Login;
import umeds.uhoo.com.u_meds.connectivity.Register;
import umeds.uhoo.com.u_meds.content.ActivityHome;
import umeds.uhoo.com.u_meds.functions.Session;
import umeds.uhoo.com.u_meds.models.User;
import umeds.uhoo.com.u_meds.security.Encryption;

public class ActivityLogin extends AppCompatActivity implements View.OnClickListener {
    LinearLayout llRegister, llLogin;
    EditText etFirstName, etLastName, etDob, etPassword, etEmail, etMobile, etLoginEmail, etLoginPassword;
    CheckBox cbTnc, cbSubscribe;
    Button btnRegister, btnLogin;
    TextView tvTabsLogin, tvTabsRegister;
    LinearLayout llProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Declare();
        getLayout(1);
        SetupTabs();
        showProgress(false);
    }

    public void Declare() {
        llProgress = findViewById(R.id.llProgress);
        llLogin = findViewById(R.id.llLogin);
        llRegister = findViewById(R.id.llRegister);
        DeclareRegister();
        DeclareLogin();
    }

    public void DeclareRegister() {
        etFirstName = findViewById(R.id.et_firstname);
        etLastName = findViewById(R.id.et_lastname);
        etDob = findViewById(R.id.et_dob);
        etPassword = findViewById(R.id.et_password);
        etEmail = findViewById(R.id.et_email);
        etMobile = findViewById(R.id.et_mobile);
//        cbSubscribe = findViewById(R.id.);
//        cbTnc = findViewById(R.id.);
        btnRegister = findViewById(R.id.btnRegister);
        tvTabsRegister = findViewById(R.id.tv_tabs_register);
    }

    public void DeclareLogin() {
        etLoginEmail = findViewById(R.id.et_login_email);
        etLoginPassword = findViewById(R.id.et_login_password);
        btnLogin = findViewById(R.id.btnLogin);
        tvTabsLogin = findViewById(R.id.tv_tabs_login);
    }

    public void getLayout(int l) {
        //1 for login, 2 for register
        if (l == 1) {
            tvTabsLogin.setTextColor(getResources().getColor(R.color.blue));
            tvTabsRegister.setTextColor(getResources().getColor(R.color.white));
            llRegister.setVisibility(View.GONE);
            llLogin.setVisibility(View.VISIBLE);
        } else if (l == 2) {
            tvTabsLogin.setTextColor(getResources().getColor(R.color.white));
            tvTabsRegister.setTextColor(getResources().getColor(R.color.blue));
            llRegister.setVisibility(View.VISIBLE);
            llLogin.setVisibility(View.GONE);
        } else {
            tvTabsLogin.setTextColor(getResources().getColor(R.color.white));
            tvTabsRegister.setTextColor(getResources().getColor(R.color.white));
            llRegister.setVisibility(View.GONE);
            llLogin.setVisibility(View.GONE);
        }
    }

    public void SetupTabs() {
        tvTabsLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLayout(1);
            }
        });
        tvTabsRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLayout(2);
            }
        });
    }

    public void Login() {
//        Encryption encryption = new Encryption();
        Login login = new Login();
        try {
            String loginresults = login.execute(etLoginEmail.getText().toString(), etLoginPassword.getText().toString()).get();
            Log.e("Registration RESULT:", loginresults);
            JSONObject results = new JSONObject(loginresults);
            if (results.has("Success")) {
                JSONObject details = results.getJSONObject("Success");
                GetUser(details);
            } else {
                JSONObject details = results.getJSONObject("Error");
                Error(2, details);

            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void Register() {
//        Encryption encryption = new Encryption();
        Register register = new Register();
        try {
            String registerresults =
                            register.execute(
                                    etFirstName.getText().toString().trim()
                                    , etLastName.getText().toString().trim()
                                    , etDob.getText().toString()
                                    , etPassword.getText().toString()
                                    , etEmail.getText().toString()
                                    , etMobile.getText().toString()
                                    , String.valueOf(true)).get();
            Log.e("Registration RESULT:", registerresults);
            JSONObject results = new JSONObject(registerresults);
            if (results.has("Success")) {
                JSONObject details = results.getJSONObject("Success");
                GetUser(details);
            } else {
                JSONObject details = results.getJSONObject("Error");
                Error(2, details);
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void GetUser(JSONObject details) {
        User userkey = new User();
        Session session = new Session(ActivityLogin.this);
        try {
            JSONObject user = details.getJSONObject("user");
            session.StoreUser(new User(
                    user.getInt(userkey.key_id)
                    , user.getString(userkey.key_firstname)
                    , user.getString(userkey.key_lastname)
                    , user.getString(userkey.key_name)
                    , user.getString(userkey.key_dob)
                    , user.getString(userkey.key_email)
                    , user.getString(userkey.key_mobile)
                    , user.getBoolean(userkey.key_subscribe)
                    , user.getString(userkey.key_firebase_token)));
            Log.e("ActivityLogin", "user stored");
            startActivity(new Intent(ActivityLogin.this, ActivityHome.class));
            finish();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public boolean validateLogin() {
        return isEmpty(etLoginEmail) && isEmpty(etLoginPassword);
    }

    public boolean validateRegistration() {
        return isEmpty(etFirstName)
                && isEmpty(etLastName)
                && isEmpty(etDob)
                && isEmpty(etEmail)
                && isEmpty(etMobile)
                && isEmpty(etPassword);
    }

    public boolean isEmpty(TextView textView) {
        return !textView.getText().toString().equals("");
    }

    public void Error(int from, JSONObject details) {
        //1=login,2=register
        if (from == 1) {
            try {
                int status = details.getInt("status");
                if (status == 401) {
                    Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                } else if (status == 404) {
                    Toast.makeText(this, "You have not registered yet!", Toast.LENGTH_SHORT).show();
                } else if (status == 400) {
                    Toast.makeText(this, "An unexpected error has occurred!", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (from == 2) {
            try {
                int status = details.getInt("status");
                if (status == 400) {
                    Toast.makeText(this, "An unexpected error has occurred!", Toast.LENGTH_SHORT).show();
                } else if (status == 403) {
                    Toast.makeText(this, "An unexpected error has occurred!", Toast.LENGTH_SHORT).show();
                } else if (status == 412) {
                    Toast.makeText(this, "You have already registered with this email!", Toast.LENGTH_SHORT).show();
                } else if (status == 417) {
                    Toast.makeText(this, "An unexpected error has occurred!", Toast.LENGTH_SHORT).show();
                } else if (status == 201) {
                    Toast.makeText(this, "Thank you for registering! Please Login!", Toast.LENGTH_SHORT).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "An unexpected error has occurred!", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                showProgress(true);
                hideSoftKeyBoard();
                if (validateLogin()) {
                    Login();
                } else {
                    Toast.makeText(ActivityLogin.this, "Missing details", Toast.LENGTH_SHORT).show();
                    showProgress(false);
                }
                break;
            case R.id.btnRegister:
                showProgress(true);
                hideSoftKeyBoard();
                if (validateRegistration()) {
                    Register();
                } else {
                    Toast.makeText(ActivityLogin.this, "Missing details", Toast.LENGTH_SHORT).show();
                    showProgress(false);
                }
                break;

        }
    }

    private void hideSoftKeyBoard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        if (imm.isAcceptingText()) { // verify if the soft keyboard is open
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    private void showProgress(Boolean load) {
        if (load) {
            llProgress.setVisibility(View.VISIBLE);
            //register
            etFirstName.setEnabled(false);
            etLastName.setEnabled(false);
            etDob.setEnabled(false);
            etPassword.setEnabled(false);
            etEmail.setEnabled(false);
            etMobile.setEnabled(false);
//        cbSubscribe.setEnabled(false);
//        cbTnc.setEnabled(false);
            btnRegister.setEnabled(false);
            tvTabsRegister.setEnabled(false);
            //login
            etLoginEmail.setEnabled(false);
            etLoginPassword.setEnabled(false);
            btnLogin.setEnabled(false);
            tvTabsLogin.setEnabled(false);
        } else {
            llProgress.setVisibility(View.GONE);
            //register
            etFirstName.setEnabled(true);
            etLastName.setEnabled(true);
            etDob.setEnabled(true);
            etPassword.setEnabled(true);
            etEmail.setEnabled(true);
            etMobile.setEnabled(true);
//        cbSubscribe.setEnabled(true);
//        cbTnc.setEnabled(true);
            btnRegister.setEnabled(true);
            tvTabsRegister.setEnabled(true);
            //login
            etLoginEmail.setEnabled(true);
            etLoginPassword.setEnabled(true);
            btnLogin.setEnabled(true);
            tvTabsLogin.setEnabled(true);

        }
    }
}
