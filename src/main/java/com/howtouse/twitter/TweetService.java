package com.howtouse.twitter;

import twitter4j.*;

import java.util.List;

public class TweetService {

    //GET - Retrieve timeline (tweets at user)
    // *- If userId is null, then retrieve timeline from authenticated user
    public void getTweetsFromUser(String userId) {
        Twitter twitter = new TwitterFactory().getInstance();

        try {
            List<Status> statuses;
            if (userId != null) {
                statuses = twitter.getUserTimeline(userId);
            } else {
                userId = twitter.verifyCredentials().getScreenName();
                statuses = twitter.getUserTimeline(userId);
            }
            System.out.println("Showing @" + userId + "'s user timeline.");
            for (Status status : statuses) {
                System.out.println("@" + userId + " - " + status.getText());
            }
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
            System.exit(-1);
        }
    }

    // GET - single tweet by tweetId
    public void getTweet(String tweetId) {
        Twitter twitter = new TwitterFactory().getInstance();
        try {
            Status status = null;
            if (tweetId != null) {
                status = twitter.showStatus(Long.parseLong(tweetId));
            } else {
                System.out.println("Tweet id is empty.");
                System.exit(-1);
            }
            System.out.println("Showing @" + status.getUser().getScreenName() + "'s tweet.");
            System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
            System.exit(-1);
        }
    }

    // POST - Update status (post new tweet)
    public void postTweet(String text) {
        Twitter twitter = new TwitterFactory().getInstance();
        try {
            Status status = null;
            if (text != null) {
                status = twitter.updateStatus(text);
            } else {
                System.out.println("Message for status is empty.");
                System.exit(-1);
            }
            System.out.println("Showing recently posted @" + status.getUser().getScreenName() + "'s tweet.");
            System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
            System.exit(-1);
        }
    }

    // DELETE - single tweet by tweetId
    public void deleteTweet(String tweetId) {
        Twitter twitter = new TwitterFactory().getInstance();
        try {
            Status status = null;
            if (tweetId != null) {
                status = twitter.destroyStatus(Long.parseLong(tweetId));
            } else {
                System.out.println("Tweet id is empty.");
                System.exit(-1);
            }
            System.out.println("Showing @" + status.getUser().getScreenName() + "'s tweet.");
            System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
            System.exit(-1);
        }
    }
}
