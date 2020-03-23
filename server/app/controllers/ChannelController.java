package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import middlewares.Secured;
import models.Channel;
import models.Post;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

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

        json.set("posts", Post.toJson(posts));

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

        json.set("channels", Channel.toJson(channels));

        return ok(json);
    }
}
