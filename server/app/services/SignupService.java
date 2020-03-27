package services;

import com.google.gson.JsonObject;
import domains.Admin;
import domains.RegularUser;
import domains.interfaces.User;
import play.mvc.Result;
import services.factory.UserFactory;

import javax.jws.soap.SOAPBinding;

import static play.mvc.Results.badRequest;
import static play.mvc.Results.internalServerError;

public class SignupService {

    public static JsonObject addUser(String username, String pwd, String userType, String fname, String lname) {

        //get user object from factory
        UserFactory userFactory = new UserFactory();

        User user = userFactory.getUser(userType);

        //return created user id or error message
        return user.addUser(username, pwd, fname, lname);

    }

}
