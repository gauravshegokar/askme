package domains.interfaces;

import com.google.gson.JsonObject;
import models.UserProfile;
import play.mvc.Result;

public interface User {
    public JsonObject addUser(String username, String pwd, String fname, String lname);
    public UserProfile getUser(int id);
}
