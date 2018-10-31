package umeds.uhoo.com.u_meds.models;

public class APILink {
    private String hostname = "http://www.bryanlowsk.com/umeds/";

    public APILink(){}
    public String login(){
        return get_user_dir()+"login.php";
    }
    public String register(){
        return get_user_dir()+"register.php";
    }
    private String get_user_dir(){
        String user_dir = "user/";
        return hostname+ user_dir;
    }
    public String get_appt_dir(){
        String appt_dir = "appointments/";
        return hostname+ appt_dir;
    }


}
