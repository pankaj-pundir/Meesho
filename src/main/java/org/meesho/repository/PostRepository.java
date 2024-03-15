package org.meesho.repository;

import java.util.*;

import org.meesho.model.UserPost;

public class PostRepository {
  static HashMap<String, UserPost> postData = new HashMap<>(); // postId,postData
  static HashMap<String, Set<String>> userPostData = new HashMap<>(); // user,List -> postID

  // post - db
  // sql user-> post id,

  // post - mongo database (NO SQL)
  // post id

  public UserPost getPost(String postId) {
    return postData.getOrDefault(postId, null);
  }

  public List<UserPost> showTimeLine(String userId, int pastRecord) {
    List<UserPost> data = new ArrayList<>();
    for (UserPost post : postData.values()) {
      data.add(post);
    }
    return data;
  }

  public List<UserPost> getPostWithUsername(String username) {
    List<UserPost> posts = new ArrayList<>();

    userPostData.getOrDefault(username, null).forEach(x -> posts.add(getPost(x)));
    return posts;
  }

  public void addPost(String postId, UserPost post) {

    userPostData.computeIfAbsent(post.getUsername(), k -> new HashSet<>()).add(postId);
//    if (userPostData.containsKey(post.getUsername())) {
//      userPostData.get(post.getUsername()).add(postId);
//    } else {
//      userPostData.put(post.getUsername(), Set.of(postId));
//    }

    postData.put(postId, post);
  }

  public boolean isUserOfPost(String username, String postId) {

    if (userPostData.containsKey(username)) {
      if (userPostData.get(username).contains(postId)) {
        return true;
      }
    }
    return false;
  }

  public void deletePost(String postId, String username) {

    if (userPostData.containsKey(username)) {
      userPostData.get(username).remove(postId);
    }

    postData.remove(postId);
  }
}
