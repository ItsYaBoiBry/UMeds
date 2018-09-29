package umeds.uhoo.com.u_meds.connectivity;

import android.os.AsyncTask;

import umeds.uhoo.com.u_meds.models.APILink;

public class Login extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... strings) {
        APILink l = new APILink();
        Request r = new Request();
        String params = "";
        params += "email="+strings[0];
        params += "&password="+strings[1];
        return r.Post(l.login(),params);
    }
}
