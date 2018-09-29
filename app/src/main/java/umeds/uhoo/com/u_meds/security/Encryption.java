package umeds.uhoo.com.u_meds.security;

import android.util.Base64;

import java.nio.charset.Charset;

public class Encryption {
    public Encryption() {

    }
    public String rot_13(String input) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c >= 'a' && c <= 'm') c += 13;
            else if (c >= 'A' && c <= 'M') c += 13;
            else if (c >= 'n' && c <= 'z') c -= 13;
            else if (c >= 'N' && c <= 'Z') c -= 13;
            sb.append(c);
        }
        return sb.toString();
    }
    public String B64Encode(String s){
        
        byte[] encodedBytes = Base64.encode(s.getBytes(), Base64.DEFAULT);
        return new String(encodedBytes, Charset.forName("UTF-8"));
    }
    public String B64Decode(String s){
        byte[] decodedBytes = Base64.decode(s.getBytes(), Base64.DEFAULT);
        return new String(decodedBytes, Charset.forName("UTF-8"));
    }

}
