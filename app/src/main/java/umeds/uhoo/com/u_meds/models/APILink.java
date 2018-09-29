package umeds.uhoo.com.u_meds.models;

public class APILink {
    String hostname = "http://www.bryanlowsk.com/umeds/";
    String user_dir = "user/";
    public APILink(){}
    public String login(){
        return get_user_dir()+"login.php";
    }
    public String register(){
        return get_user_dir()+"register.php";
    }
    public String get_user_dir(){
        return hostname+user_dir;
    }


}
