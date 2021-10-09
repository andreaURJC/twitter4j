package com.howtouse.twitter;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import twitter4j.*;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class TwitterApplication {

    public static void main(String[] args) {
//		SpringApplication.run(TwitterApplication.class, args);
// Authenticate user:
        File file = new File("src/main/resources/twitter4j.properties");
        Properties properties = new Properties();
//		InputStream inputStream = null;
//		OutputStream outputStream = null;
//		try {
//			if (file.exists()) {
//				inputStream = new FileInputStream(file);
//				properties.load(inputStream);
//			}
//			if (args.length < 2) {
//				if (null == properties.getProperty("oauth.consumerKey")
//						&& null == properties.getProperty("oauth.consumerSecret")) {
//					// consumer key/secret are not set in twitter4j.properties
//					System.out.println(
//							"Usage: java twitter4j.examples.oauth.GetAccessToken [consumer key] [consumer secret]");
//					System.exit(-1);
//				}
//			} else {
//				properties.setProperty("oauth.consumerKey", args[0]);
//				properties.setProperty("oauth.consumerSecret", args[1]);
//				outputStream = new FileOutputStream("twitter4j.properties");
//				properties.store(outputStream, "twitter4j.properties");
//			}
//		} catch (IOException ioe) {
//			ioe.printStackTrace();
//			System.exit(-1);
//		} finally {
//			if (inputStream != null) {
//				try {
//					inputStream.close();
//				} catch (IOException ignore) {
//				}
//			}
//			if (outputStream != null) {
//				try {
//					outputStream.close();
//				} catch (IOException ignore) {
//				}
//			}
//		}
//		try {
//			Twitter twitter = new TwitterFactory().getInstance();
//			RequestToken requestToken = twitter.getOAuthRequestToken();
//			System.out.println("Got request token.");
//			System.out.println("Request token: " + requestToken.getToken());
//			System.out.println("Request token secret: " + requestToken.getTokenSecret());
//			AccessToken accessToken = null;
//
//			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//			while (null == accessToken) {
//				System.out.println("Open the following URL and grant access to your account:");
//				System.out.println(requestToken.getAuthorizationURL());
//				try {
//					Desktop.getDesktop().browse(new URI(requestToken.getAuthorizationURL()));
//				} catch (UnsupportedOperationException ignore) {
//				} catch (IOException ignore) {
//				} catch (URISyntaxException e) {
//					throw new AssertionError(e);
//				}
//				System.out.print("Enter the PIN(if available) and hit enter after you granted access.[PIN]:");
//				String pin = br.readLine();
//				try {
//					if (pin.length() > 0) {
//						accessToken = twitter.getOAuthAccessToken(requestToken, pin);
//					} else {
//						accessToken = twitter.getOAuthAccessToken(requestToken);
//					}
//				} catch (TwitterException te) {
//					if (401 == te.getStatusCode()) {
//						System.out.println("Unable to get the access token.");
//					} else {
//						te.printStackTrace();
//					}
//				}
//			}
//			System.out.println("Got access token.");
//			System.out.println("Access token: " + accessToken.getToken());
//			System.out.println("Access token secret: " + accessToken.getTokenSecret());
//
//			try {
//				properties.setProperty("oauth.accessToken", accessToken.getToken());
//				properties.setProperty("oauth.accessTokenSecret", accessToken.getTokenSecret());
//				properties.setProperty("oauth.screenName", accessToken.getScreenName());
//				outputStream = new FileOutputStream(file);
//				properties.store(outputStream, "twitter4j.properties");
//				outputStream.close();
//			} catch (IOException ioe) {
//				ioe.printStackTrace();
//			} finally {
//				if (outputStream != null) {
//					try {
//						outputStream.close();
//					} catch (IOException ignore) {
//					}
//				}
//			}
//			System.out.println("Successfully stored access token to " + file.getAbsolutePath() + ".");
//		} catch (TwitterException te) {
//			te.printStackTrace();
//			System.out.println("Failed to get accessToken: " + te.getMessage());
//		} catch (IOException ioe) {
//			ioe.printStackTrace();
//			System.out.println("Failed to read the system input.");
//		}

        Twitter twitter = new TwitterFactory().getInstance();
        String tweetId = "1446790069046714370";
        Status status = null;
        try {
            if (tweetId != null) {
                status = twitter.unRetweetStatus(Long.parseLong(tweetId));
            } else {
                System.out.println("Tweet id is empty.");
                System.exit(-1);
            }
            System.out.println("Showing unretweeted @" + status.getUser().getScreenName() + "'s tweet.");
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
