package umeds.uhoo.com.u_meds.connectivity;

import android.os.AsyncTask;

import umeds.uhoo.com.u_meds.models.APILink;

public class Register extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... strings) {
        APILink l = new APILink();
        Request r = new Request();
        String params = "";
        params+="firstname="+strings[0];
        params+="&lastname="+strings[1];
        params+="&dob="+strings[2];
        params+="&password="+strings[3];
        params+="&email="+strings[4];
        params+="&mobile="+strings[5];
        params+="&subscribe="+strings[6];
        return r.Post(l.register(),params);
    }
}

