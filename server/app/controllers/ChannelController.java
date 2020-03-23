package controllers;

import models.Channel;
import play.mvc.Controller;
import play.mvc.Result;

public class ChannelController extends Controller {

    public Result getChannel(int channelId) {
        Channel channel = Channel.findById(channelId);

        return ok(channel.toJson());
    }
}
