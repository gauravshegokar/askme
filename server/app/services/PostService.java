package services;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import models.Channel;
import models.Post;
import models.Tag;
import models.UserProfile;
import play.mvc.Result;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static play.mvc.Results.*;

public class PostService {

    public Result addPost(String channelId, String userId, String text, boolean isProfane, String tags) {

        // Check if user exits
        List<UserProfile> dbUserMapped = UserProfile.find.query().where().ilike("id", userId).findList();

        if (dbUserMapped.size() == 0) {
            return status(401, "User not found");
        } else {


            //check if channel present
            List<Channel> dbChannelMapped = Channel.find.query().where().ilike("channelId", channelId).findList();

            if (dbChannelMapped.size() == 0) {
                return badRequest("\"{\"error\":\"channel not found\"}\"").withHeader("auth", userId);
            } else {

                List<Tag> postTags = new ArrayList<>();
                List<String> tagNames = Arrays.asList(tags.split(","));

                for (String tagName : tagNames) {


                    List<Tag> existingTag = Tag.find.query().where().eq("tagName", tagName).findList();

                    if (existingTag.size() == 0) {


                        Tag newTag = new Tag();
                        newTag.setTagName(tagName);
                        newTag.save();
                        postTags.add(newTag);
                    } else {


                        postTags.add(existingTag.get(0));


                    }
                }

                try {
                    //create Post bean
                    Post newPost = new Post();

                    newPost.setAuthor(dbUserMapped.get(0));
                    newPost.setChannel(dbChannelMapped.get(0));
                    newPost.setProfane(isProfane);
                    newPost.setText(text);
                    newPost.setTags(postTags);
                    newPost.setDate_created(Timestamp.from(Instant.now()));
                    newPost.save();

                    return status(201, String.valueOf(newPost.getPostId()));
                } catch (Exception e) {
                    e.printStackTrace();
                    return badRequest("\"{\"error\":\"couldn't save new post\"}\"").withHeader("auth", userId);
                }


            }
        }

    }


    public Result getPost(String postId, String userId) {

        List<Post> post = Post.find.query().where().eq("postId", postId).findList();
        if (post.size() == 0) {
            return badRequest("\"{\"error\":\"Post doesn't exist\"}\"").withHeader("auth", userId);
        }

        try {

            //creating tag array
            JsonArray tagArray = new JsonArray();
            List<Tag> tags = post.get(0).getTags();

            for (Tag tag : tags) {
                JsonObject tagJsonObject = new JsonObject();
                tagJsonObject.addProperty("tagId", tag.getTagId());
                tagJsonObject.addProperty("tagName", tag.getTagName());
                tagArray.add(tagJsonObject);
            }

            //creating result json
            JsonObject resultJson = new JsonObject();

            resultJson.addProperty("postId", post.get(0).getPostId());
            resultJson.addProperty("postText", post.get(0).getText());
            resultJson.addProperty("profane", post.get(0).isProfane());
            resultJson.addProperty("authorId", post.get(0).getAuthor().getId());
            resultJson.addProperty("authorName", post.get(0).getAuthor().getUsername());
            resultJson.addProperty("datePosted", String.valueOf(post.get(0).getDate_created()));
            resultJson.addProperty("channelId",post.get(0).getChannel().getChannelId());
            resultJson.addProperty("channelName",post.get(0).getChannel().getChannelName());
            resultJson.add("tags", tagArray);

            return status(200, resultJson.toString()).withHeader("auth",userId);

        }
        catch (Exception e){
            e.printStackTrace();
            return internalServerError("{\"error\": \"Unable to fetch post\"}");
        }



    }

}
