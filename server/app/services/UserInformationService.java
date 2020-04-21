package services;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import domains.Admin;
import domains.RegularUser;
import domains.TaxVisitor;
import models.Channel;
import models.Post;
import models.Tag;
import models.UserProfile;
import play.mvc.Result;

import java.util.List;

import static play.mvc.Results.*;

public class UserInformationService {

    public Result getUserBasicInfo(String id) {

        System.out.println("inside user basic info, id= " + id);

        // creating user bean
        UserProfile userToFind = new UserProfile();
        userToFind.setId(Integer.parseInt(id));

        // Check if user exits
        List<UserProfile> dbUserMapped = UserProfile.find.query().where().eq("id", id).findList();
        if (dbUserMapped.size() == 0) {
            return badRequest("\"{\\\"error\\\":\\\"user not found\\\"}\"");
        } else if (dbUserMapped.size() > 0) {

            //create result json
            JsonObject resultJson = new JsonObject();
            UserProfile user = dbUserMapped.get(0);


            resultJson.addProperty("userId", String.valueOf(user.getId()));
            resultJson.addProperty("username", user.getUsername());
            resultJson.addProperty("firstname", user.getFname());
            resultJson.addProperty("lastname", user.getLname());
            resultJson.addProperty("dateJoined", String.valueOf(user.getDateCreated()));

            return status(200, resultJson.toString()).withHeader("auth", String.valueOf(user.getId()));
        }

        return badRequest("{\"error\":\"Unable to get basic user information\"}");


    }

    public Result getUserInterests(String id) {

        // creating user bean
        UserProfile userToFind = new UserProfile();
        userToFind.setId(Integer.parseInt(id));

        JsonObject resultJson = new JsonObject();

        // Check if user exits
        List<UserProfile> dbUserMapped = UserProfile.find.query().where().ilike("userId", String.valueOf(userToFind.getId())).findList();
        if (dbUserMapped.size() == 0) {
            return badRequest("\"{\\\"error\\\":\\\"user not found\\\"}\"");
        } else if (dbUserMapped.size() > 0) {

            //create result json
            UserProfile user = dbUserMapped.get(0);

            JsonArray interestsJsonArray = new JsonArray();

            List<Tag> interests = user.getInterests();

            //create interest jsonobject from obtained interest
            for (Tag interest : interests) {
                JsonObject interestObject = new JsonObject();

                interestObject.addProperty("tagId", interest.getTagId());
                interestObject.addProperty("tagName", interest.getTagName());

                interestsJsonArray.add(interestObject);
            }


            resultJson.add("interests", interestsJsonArray);


        }

        return status(200, resultJson.toString()).withHeader("auth", id);


    }


    public Result getUserPosts(String userId) {

        try {

            //get user from userid
            List<UserProfile> users = UserProfile.find.query().where().eq("id", userId).findList();

            if (users.size() == 0) {
                return badRequest("{\"error\": \"user not found\"}");
            }

            //get posts from userprofile table
            List<Post> posts = users.get(0).getPosts();

            //create post json array
            JsonArray postsJsonArray = new JsonArray();

            for (Post post : posts) {

                JsonObject postJson = new JsonObject();
                postJson.addProperty("postId", post.getPostId());
                postJson.addProperty("postText", post.getText());
                postJson.addProperty("channelName", post.getChannel().getChannelName());
                postJson.addProperty("channelId", post.getChannel().getChannelId());
                postJson.addProperty("datePosted", String.valueOf(post.getDateCreated()));

                postsJsonArray.add(postJson);
            }

            JsonObject resultJson = new JsonObject();
            resultJson.add("posts", postsJsonArray);

            return status(200, resultJson.toString()).withHeader("auth", userId);


        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError("{\"error\":\"Couldn't fetch user posts \"}");
        }


    }

    //TODO: change it for multiple channels in future sprints
    public Result getUserSubscribedChannels(String userId){

    try{
        //default channel
        Channel channel = Channel.findById(1);

        //creating subscribed channel object
        JsonObject subscribedChannel = new JsonObject();
        subscribedChannel.addProperty("channelId", channel.getChannelId());
        subscribedChannel.addProperty("channelName", channel.getChannelName());

        //creating subscribed channels array
        JsonArray subscribedChannels = new JsonArray();
        subscribedChannels.add(subscribedChannel);

        //creating result json
        JsonObject resultJson= new JsonObject();
        resultJson.add("subscribedChannels", subscribedChannels);

        return status(200, resultJson.toString()).withHeader("auth",userId);


    }
    catch (Exception e){
        e.printStackTrace();
        return internalServerError("{\"error\":\"Couldn't fetch user subscribed channels \"}");
    }
    }

    public static double getTaxValue(int id, String userType){

        TaxVisitor taxCalculator = new TaxVisitor();
        double tax=0.0;
        if(userType.equals("admin")){
            Admin admin = new Admin();
            tax=taxCalculator.visit(admin,id);
        }
        else{
            RegularUser user = new RegularUser();
            tax=taxCalculator.visit(user,id);
        }
        return tax;
    }


}
