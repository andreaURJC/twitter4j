package com.howtouse.twitter;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import java.util.Objects;

public class RetweetService {
    //POST - do retweet
    public void retweet(String tweetId) {
        Twitter twitter = new TwitterFactory().getInstance();
        try {
            Status status = null;
            if (tweetId != null) {
                status = twitter.retweetStatus(Long.parseLong(tweetId));
            } else {
                System.out.println("Tweet id is empty.");
                System.exit(-1);
            }
            System.out.println("Showing retweeted @" + status.getUser().getScreenName() + "'s tweet.");
            System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
        } catch (TwitterException te) {
            if (Objects.equals(te.getStatusCode(), 403)) {
                System.out.println("You have already retweeted this tweet!");
                System.exit(-1);
            }
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
            System.exit(-1);
        }
    }

    //POST - do unRetweet
    public void unRetweet(String tweetId) {
        Twitter twitter = new TwitterFactory().getInstance();
        try {
            Status status = null;
            if (tweetId != null) {
                status = twitter.unRetweetStatus(Long.parseLong(tweetId));
            } else {
                System.out.println("Tweet id is empty.");
                System.exit(-1);
            }
            System.out.println("Showing retweeted @" + status.getUser().getScreenName() + "'s tweet.");
            System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
        } catch (TwitterException te) {
            if (Objects.equals(te.getStatusCode(), 403)) {
                System.out.println("You have already retweeted this tweet!");
                System.exit(-1);
            }
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
            System.exit(-1);
        }
    }
}
