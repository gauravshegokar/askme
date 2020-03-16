package domains.interfaces;

import play.mvc.Result;

public interface User {

    public Result addUser(String username, String pwd, String userType, String fname, String lname);
    public Result getUser();

}
