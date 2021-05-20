package com.sergeik.design;

import java.util.*;

/**
 * Design a simplified version of Twitter where users can post tweets, follow/unfollow another user, and is able 
 * to see the 10 most recent tweets in the user's news feed.
 *
 * Implement the Twitter class:
 *
 * Twitter() Initializes your twitter object.
 * void postTweet(int userId, int tweetId) Composes a new tweet with ID tweetId by the user userId. Each call to 
 * this function will be made with a unique tweetId.
 * List<Integer> getNewsFeed(int userId) Retrieves the 10 most recent tweet IDs in the user's news feed. Each item 
 * in the news feed must be posted by users who the user followed or by the user themself. Tweets must be ordered from most recent to least recent.
 * void follow(int followerId, int followeeId) The user with ID followerId started following the user with 
 * ID followeeId.
 * void unfollow(int followerId, int followeeId) The user with ID followerId started unfollowing the user
 * with ID followeeId.
 */
public class DesignTwitter {

    public static void main(String[] args) {
        Twitter twitter = new Twitter();
        twitter.postTweet(1, 5); // User 1 posts a new tweet (id = 5).
        assert Arrays.equals(
                new Integer[] {5},
                twitter.getNewsFeed(1).toArray()
        ); // User 1's news feed should return a list with 1 tweet id -> [5]. return [5]
        twitter.follow(1, 2);    // User 1 follows user 2.
        twitter.postTweet(2, 6); // User 2 posts a new tweet (id = 6).
        Arrays.equals(
                new Integer[] {6, 5},
                twitter.getNewsFeed(1).toArray()
        );  // User 1's news feed should return a list with 2 tweet ids -> [6, 5]. Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
        twitter.unfollow(1, 2);  // User 1 unfollows user 2.
        Arrays.equals(
                new Integer[] {5},
                twitter.getNewsFeed(1).toArray()
        );  // User 1's news feed should return a list with 1 tweet id -> [5], since user 1 is no longer following user 2.
    }

    static class Twitter {

        private Map<Integer, Set<Integer>> followers;
        private Map<Integer, List<Message>> feeds;
        private List<Message> messages;

        /** Initialize your data structure here. */
        public Twitter() {
            followers = new HashMap<>();
            feeds = new HashMap<>();
            messages = new LinkedList<>();
        }

        /** Compose a new tweet. */
        public void postTweet(int userId, int tweetId) {
            Message tweet = new Message(userId, tweetId);
            messages.add(0, tweet);
        }

        class Message {
            int userId;
            int tweetId;

            Message(int userId, int tweetId) {
                this.userId = userId;
                this.tweetId = tweetId;
            }
        }

        /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
        public List<Integer> getNewsFeed(int userId) {

            List<Integer> feed = new LinkedList<>();
            for (Message message: messages) {
                if (message.userId == userId
                        || followers.getOrDefault(userId, new HashSet<>()).contains(message.userId)
                ) {
                    feed.add(message.tweetId);
                    if (feed.size() == 10)
                        return feed;
                }
            }
            return feed;
        }

        /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
        public void follow(int followerId, int followeeId) {
            if (!followers.containsKey(followerId))
                followers.put(followerId, new HashSet<>());
            followers.get(followerId).add(followeeId);
        }

        /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
        public void unfollow(int followerId, int followeeId) {
            if (!followers.containsKey(followerId))
                return;
            followers.get(followerId).remove(followeeId);
        }
    }

}
