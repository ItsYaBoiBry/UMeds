package umeds.uhoo.com.u_meds.connectivity;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Request {
    public Request(){}
    private String GetRequest(String url) throws Exception {

        String[] getLink = url.split("/");
        if (getLink[0].equals("http:")) {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            return response.toString();
        } else if (getLink[0].equals("https:")) {
            URL obj = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");


            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            return response.toString();
        } else {
            System.out.print("Please enter a http:// link");
        }
        return null;
    }
    private String PostRequest(String url, String parameters) throws Exception {
        Log.i("status","started sendPost");
        String[] getLink = url.split("/");
        if (getLink[0].equals("http:")) {

            URL obj = new URL(url);
            HttpURLConnection conHttp = (HttpURLConnection) obj.openConnection();

            //add reuqest header
            conHttp.setRequestMethod("POST");
            conHttp.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            String urlParameters = parameters;

            // Send post request
            conHttp.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(conHttp.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            int responseCode = conHttp.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + urlParameters);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conHttp.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            return response.toString();

        } else if (getLink[0].equals("https:")) {
            Log.i("status","sendPost(if=https)");

            URL obj = new URL(url);
            HttpURLConnection conHttp = (HttpURLConnection) obj.openConnection();

            //add reuqest header
            conHttp.setRequestMethod("POST");
            conHttp.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            String urlParameters = parameters;

            // Send post request
            conHttp.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(conHttp.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            int responseCode = conHttp.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + urlParameters);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conHttp.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            Log.i("httpReqResponse",response.toString());
            //print result
            return response.toString();

        } else {
            System.out.print("Please enter a http:// link");
        }

        return null;

    }
    public String Get(String url){
        try {
            return GetRequest(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    public String Post(String url,String params){
        try {
            return PostRequest(url,params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
