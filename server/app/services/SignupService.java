package services;

import domains.Admin;
import domains.RegularUser;
import domains.interfaces.User;
import play.mvc.Result;

import static play.mvc.Results.badRequest;
import static play.mvc.Results.internalServerError;

public class SignupService {

    public static Result addUser(String username, String pwd, String userType, String fname, String lname) {

        User user;

        if (userType.equals(null)) {
            return badRequest("{\"error\":\"Missing required parameter user type\"}");
        }
        if(userType.equalsIgnoreCase("regular")){
            user=new RegularUser();
            return user.addUser(username, pwd, userType, fname, lname);
        }
        if(userType.equalsIgnoreCase("admin")){
            user=new Admin();
            return user.addUser(username, pwd, userType, fname, lname);
        }

        return internalServerError("{\"error\":\"Internal Server error while adding user\"}");
    }


}
