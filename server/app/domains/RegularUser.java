package domains;

import com.google.gson.JsonObject;
import domains.interfaces.User;
import domains.interfaces.Visitable;
import domains.interfaces.Visitor;
import models.Channel;
import models.UserProfile;
import play.mvc.Result;

import java.util.List;

import static play.mvc.Results.badRequest;
import static play.mvc.Results.status;

public class RegularUser implements User, Visitable {

    private static double monthlySubscriptionPrice = 10.0;
    @Override
    public JsonObject addUser(String username, String pwd,String fname, String lname) {

        JsonObject resultJson = new JsonObject();
        resultJson.addProperty("success", false);
        // creating user bean
        UserProfile user = new UserProfile();
        user.setUsername(username);
        user.setPassword(pwd);
        user.setFname(fname);
        user.setLname(lname);
        user.setAccessLevel("regular");
        System.out.println(user.toString());


        // Check if user exits
        List<UserProfile> dbUserMapped = UserProfile.find.query().where().ilike("username", user.getUsername()).findList();
        if (dbUserMapped.size() == 0) {
            user.save();

            Channel defaultChannel = Channel.getDefaultChannel();

            // Add the newly created user into the default channel.
            defaultChannel.addMembers(user)
                    .save();


            resultJson.addProperty("userId", String.valueOf(user.getId()));
            resultJson.addProperty("success", true);

        } else if (dbUserMapped.size() > 0) {
            resultJson.addProperty("msg", "username already exists");

        }


        return resultJson;
    }
    @Override
    public UserProfile getUser(int id) {
        return UserProfile.findById(id) ;
    }

    @Override
    public double accept(Visitor visitor, int id) {
        return visitor.visit(this, id);
    }
}
