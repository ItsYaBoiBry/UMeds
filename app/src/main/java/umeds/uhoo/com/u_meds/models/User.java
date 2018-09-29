package umeds.uhoo.com.u_meds.models;

import java.io.Serializable;

public class User implements Serializable{
    public String key_id = "id"; private int id;
    public String key_firstname = "first_name"; private String firstname;
    public String key_lastname = "last_name"; private String lastname;
    public String key_name = "name"; private String name;
    public String key_dob = "dob"; private String dob;
    public String key_email = "email"; private String email;
    public String key_mobile = "mobile"; private String mobile;
    public String key_subscribe = "subscribe"; private boolean subscribe;
    public String key_firebase_token = "token"; private String firebasetoken;

    public User(){

    }

    public User(int id, String firstname, String lastname, String name, String dob, String email, String mobile, boolean subscribe, String firebasetoken){
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.mobile = mobile;
        this.subscribe = subscribe;
        this.firebasetoken = firebasetoken;
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public boolean isSubscribe() {
        return subscribe;
    }

    public String getFirebasetoken() {
        return firebasetoken;
    }
}
