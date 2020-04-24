package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import middlewares.Secured;
import models.Channel;
import models.Jsonable;
import models.Post;
import models.UserProfile;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import services.PostService;

import java.util.Date;
import java.util.List;

public class ChannelController extends Controller {

    /**
     * Get channel detail by id
     *
     * @param channelId
     * @return
     */
    @Security.Authenticated(Secured.class)
    public Result getChannel(int channelId) {
        Channel channel = Channel.findById(channelId);

        return ok(channel.toJson());
    }

    /**
     * Get all posts in a channel
     *
     * @param channelId
     * @return
     */
    @Security.Authenticated(Secured.class)
    public Result getPosts(int channelId) {
        Channel channel = Channel.findById(channelId);
        List<Post> posts = channel.getPosts();
        ObjectNode json = Json.newObject();

        json.set("posts", Jsonable.toJson(posts));

        return ok(json);
    }

    /**
     * Get all channels
     *
     * @return
     */
    @Security.Authenticated(Secured.class)
    public Result getAllChannels() {
        List<Channel> channels = Channel.getAllChannels();
        ObjectNode json = Json.newObject();

        json.set("channels", Jsonable.toJson(channels));

        return ok(json);
    }

    /**
     * Create a new channel.
     *
     * @return
     */
    @Security.Authenticated(Secured.class)
    public Result createChannel() {
        JsonNode body = request().body().asJson();
        String channelName = body.get("channelName").asText();
        String channelDescription = body.get("channelDescription").asText();
        // Get current user
        String userId = request().username();
        UserProfile user = UserProfile.findById(userId);
        // Create a new channel
        Channel channel = new Channel();

        channel.setChannelName(channelName)
                .setChannelDescription(channelDescription)
                .setChannelOwner(user)
                .setDateCreated(new Date())
                .save();

        return ok(channel.toJson());
    }

    /**
     * Create a new post in the channel.
     *
     * @param channelId
     * @return
     */
    @Security.Authenticated(Secured.class)
    public Result addPost(String channelId) {
        boolean isAuthentic = request().headers().get("auth")[0].equals(null) ? false : true;

        if (!isAuthentic) {
            return badRequest("\"{\"error\":\"authorization failed\"}\"");
        }

        boolean isProfane = request().body().asJson().get("profane").asText().
                equalsIgnoreCase("true") ? true : false;

        return new PostService().addPost(channelId,
                request().headers().get("auth")[0],
                request().body().asJson().get("postText").asText(),
                isProfane,
                request().body().asJson().get("tags").asText());
    }

    /**
     * Get channel subscribers.
     *
     * @param channelId
     * @return
     */
    @Security.Authenticated(Secured.class)
    public Result getSubscribers(String channelId) {
        Channel channel = Channel
                .findById(channelId);
        List<UserProfile> members = channel.getMembers();
        ObjectNode jsonNode = Json.newObject();

        jsonNode.set("subscribers", Jsonable.toJson(members));

        return ok(jsonNode);
    }

    /**
     * Delete a channel.
     *
     * @param channelId
     * @return
     */
    @Security.Authenticated(Secured.class)
    public Result deleteChannel(String channelId) {
        UserProfile user = UserProfile.findById(request().username());
        Channel channel = Channel.findById(channelId);

        // only channel owner or system admin can delete the channel
        if ("admin".equals(user.getAccessLevel())
                || channel.getChannelOwner().getId() == user.getId()) {
            channel.delete();
        }

        return ok(Json.newObject());
    }

    /**
     * Add the current user as a member of the channel.
     *
     * @param channelId
     * @return
     */
    @Security.Authenticated(Secured.class)
    public Result subscribe(String channelId) {
        String userId = request().username();
        UserProfile user = UserProfile.findById(userId);
        Channel channel = Channel.findById(channelId);

        if(!channel.containsMember(user)) {
            channel.addMembers(user)
                    .save();
        }

        return ok(Json.newObject());
    }
}
