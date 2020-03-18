package services;

import models.Channel;
import models.Post;
import models.Tag;
import models.UserProfile;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static play.mvc.Results.badRequest;
import static play.mvc.Results.status;

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
                    newPost.save();
                    return status(201, String.valueOf(newPost.getPostId()));
                } catch (Exception e) {
                    e.printStackTrace();
                    return badRequest("\"{\"error\":\"couldn't save new post\"}\"").withHeader("auth", userId);
                }


            }
        }

    }

}
