package org.meesho.repository;

import java.util.*;

public class FollowRepository {
  public static HashMap<String, List<String>> followerData = new HashMap<>();
  public static HashMap<String, Set<String>> followingData = new HashMap<>();

  public Set<String> getUserFollowers(String username) {
    return new HashSet<>(followerData.getOrDefault(username, null));
  }

  public void addFollower(String username, String followingUserName) {
    // username is following followingUserName


    followerData.computeIfAbsent(username, k -> new ArrayList<>()).add(followingUserName);
    followingData.computeIfAbsent(followingUserName, k -> new HashSet<>()).add(username);

//
//    if (followerData.containsKey(username)) {
//      followerData.get(username).add(followingUserName);
//    } else {
//      followerData.put(username, List.of(followingUserName));
//    }
//
//    if (followingData.containsKey(followingUserName)) {
//      followingData.get(username).add(username);
//    } else {
//      followingData.put(followingUserName, Set.of(username));
//    }
  }

  public boolean isFollowing(String username, String followingUserName) {
    if (followerData.containsKey(username)) {
      if (followerData.get(username).contains(followingUserName)) {
        return true;
      }
    }
    return false;
  }

  public void removeFollower(String username, String followingUserName) {
    // username is following followingUserName

    if (followerData.containsKey(username)) {
      followerData.get(username).remove(followingUserName);
    }

    if (followingData.containsKey(followingUserName)) {
      followingData.get(username).remove(username);
    }
  }
}
