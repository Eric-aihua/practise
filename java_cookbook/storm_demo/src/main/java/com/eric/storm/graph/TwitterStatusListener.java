package com.eric.storm.graph;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.*;

import java.util.Arrays;

/**
 * Created by Eric on 2017/8/28.
 */
public class TwitterStatusListener implements StatusListener {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Override
    public void onStatus(Status status) {
        JSONObject twitterJSONObj=new JSONObject();
        HashtagEntity[] hashTagEntity=status.getHashtagEntities();
        UserMentionEntity[] userMentionEntities=status.getUserMentionEntities();
        URLEntity[] urlEntities=status.getURLEntities();

        JSONArray hasTagJsonArray=new JSONArray();
        JSONArray userMentionArray=new JSONArray();
        JSONArray urlJsonArray=new JSONArray();
        Arrays.asList(hashTagEntity).stream().map( (x)-> x.getText()) .forEach(x -> hasTagJsonArray.put(x));
        Arrays.asList(userMentionEntities).stream().map( (x)-> x.getScreenName()) .forEach(x -> userMentionArray.put(x));
        Arrays.asList(urlEntities).stream().map( (x)-> x.getExpandedURL()) .forEach(x -> urlJsonArray.put(x));

        try {
            twitterJSONObj.put("user",status.getUser().getScreenName());
            twitterJSONObj.put("name",status.getUser().getName());
            twitterJSONObj.put("location",status.getUser().getLocation());
            twitterJSONObj.put("text",status.getText());
            twitterJSONObj.put("hastag",hasTagJsonArray);
            twitterJSONObj.put("usermention",userMentionArray);
            twitterJSONObj.put("urls",urlJsonArray);

            if(status.isRetweet()){
                JSONObject reTwriterobj=new JSONObject();
                reTwriterobj.put("user",status.getUser().getScreenName());
                reTwriterobj.put("name",status.getUser().getName());
                reTwriterobj.put("location",status.getUser().getLocation());
                twitterJSONObj.put("retwitter", reTwriterobj);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        logger.info("Process Result:"+twitterJSONObj.toString());
    }

    @Override
    public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {

    }

    @Override
    public void onTrackLimitationNotice(int i) {

    }

    @Override
    public void onScrubGeo(long l, long l1) {

    }

    @Override
    public void onStallWarning(StallWarning stallWarning) {

    }

    @Override
    public void onException(Exception e) {
        e.printStackTrace();
    }
}
