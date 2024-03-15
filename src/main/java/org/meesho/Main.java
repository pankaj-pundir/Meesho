package org.meesho;

import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.meesho.model.UserPost;
import org.meesho.service.MySocialMedia;
import org.meesho.service.impl.MySocialMediaImpl;

@Slf4j
public class Main {

  public static void main(String[] args) {

    MySocialMedia mySocialMedia = new MySocialMediaImpl();

    // add user
    try {
      mySocialMedia.createUser("user1", "Pankaj pundir");
    } catch (Exception e) {
      log.error(e.getMessage());
    }

    // add user
    try {
      mySocialMedia.createUser("user2", "Shivam");
    } catch (Exception e) {
      log.error(e.getMessage());
    }

    // get user
    try {
      log.info("user data : {}", mySocialMedia.getUser("user1"));
    } catch (Exception e) {
      log.error(e.getMessage());
    }

    // create post
    try {
      mySocialMedia.createPost("user1", "POST1", "This is my first post", LocalDateTime.now());
      mySocialMedia.createPost("user2", "POST2", "This is my second post", LocalDateTime.now());
      mySocialMedia.createPost("user1", "POST3", "This is my third post", LocalDateTime.now());

    } catch (Exception e) {
      log.error(e.getMessage());
    }

    // get post
    try {
      UserPost postData = mySocialMedia.searchPost("POST1");
      log.info("Post data \n\n {}\n ", postData);
    } catch (Exception e) {
      log.error(e.getMessage());
    }

    // user1 following user2
    try {
      mySocialMedia.follow("user1", "user2"); // user1 is following user2

      mySocialMedia.unfollow("user2", "user1"); // user2 is not following user1

      mySocialMedia.unfollow("user1", "user2");

    } catch (Exception e) {
      log.error(e.getMessage());
    }

    // deleting user post data - invalid user
    try {
      mySocialMedia.deletePost("user2", "POST1");
    } catch (Exception e) {
      log.error(e.getMessage());
    }

    // deleting user post data - valid user
    try {
      mySocialMedia.deletePost("user1", "POST1");
    } catch (Exception e) {
      log.error(e.getMessage());
    }


    try {
      mySocialMedia.showTimeLine("user1", 2);
    } catch (Exception e) {
      log.error(e.getMessage());
    }
  }
}
