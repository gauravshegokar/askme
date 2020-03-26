package services;

import models.Channel;
import models.Post;
import models.Tag;
import models.UserProfile;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static play.mvc.Results.badRequest;
import static play.mvc.Results.status;

public class PostService {
    // [Flyweight Pattern]
    private static HashMap<Integer, Post> cachedPosts = new HashMap<>();

    public static Result addPost(String channelId, String userId, String text, boolean isProfane, String tags) {

        // Check if user exits
        List<UserProfile> dbUserMapped = UserProfile.find.query().where().ilike("id", userId).findList();

        if (dbUserMapped.size() == 0) {
            return status(401, "User not found");
        } else {


            //check if channel present
            List<Channel> dbChannelMapped = Channel.getFinder().query().where().ilike("channelId", channelId).findList();

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
                    // create Post bean
                    Post newPost = new Post();

                    // [Builder Pattern]
                    newPost.setAuthor(dbUserMapped.get(0))
                            .setChannel(dbChannelMapped.get(0))
                            .setProfane(isProfane)
                            .setText(text)
                            .setTags(postTags);

                    newPost.save();

                    cachedPosts.put(newPost.getPostId(), newPost);

                    return status(201, String.valueOf(newPost.getPostId()));
                } catch (Exception e) {
                    e.printStackTrace();
                    return badRequest("\"{\"error\":\"couldn't save new post\"}\"").withHeader("auth", userId);
                }
            }
        }
    }


    /**
     * Get post by id.
     * <p>
     * [Singleton, Flyweight, and Factory Patterns]
     *
     * @param postIdString
     * @return
     */
    public static Post getPost(String postIdString) {
        int postId = Integer.parseInt(postIdString);

        Post post = cachedPosts.get(postId);

        // [Flyweight Pattern]
        if (post == null) {
            // [Singleton Pattern]
            post = Post.getFinder().where().eq("postId", postId).findUnique();

            cachedPosts.put(postId, post);
        }

        return post;
    }

}
