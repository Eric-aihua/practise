package com.eric.storm.graph;

import twitter4j.FilterQuery;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * 使用Twitter Client从Twitter获取数据，并对数据进行封装发往Kafka
 * Created by Eric on 2017/8/28.
 */
public class TwitterStreamConsumer {
    public static void main(String [] args){
        ConfigurationBuilder CB = new ConfigurationBuilder();
        CB.setDebugEnabled(true)
                .setOAuthConsumerKey("GQyCKJBmiufakgJ7P5T1eAsxV")
                .setOAuthConsumerSecret(
                        "Hmwv71tVYpHOSOrNT7w0WGdb71JG5Wgxcfo3Gn2qDlhmbtWs2w")
                .setOAuthAccessToken(
                        "54256387-TFUcMqAJdEMDWjyMOmsXMhyi4B95cxakSfF3aQ6tv")
                .setOAuthAccessTokenSecret(
                        "d45FxNmL5NiVsO5EWzZhPECmqEycAwSMO5Jk8jebGBqbR");
        StatusListener statusListener= new TwitterStatusListener();
        TwitterStream stream=new TwitterStreamFactory().getInstance();
        stream.addListener(statusListener);
        FilterQuery query=new FilterQuery().track(args);
        stream.filter(query);
    }
}
