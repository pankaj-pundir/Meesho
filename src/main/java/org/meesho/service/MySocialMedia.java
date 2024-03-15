package org.meesho.service;

import java.time.LocalDateTime;
import java.util.List;
import org.meesho.exception.PostException;
import org.meesho.exception.UserException;
import org.meesho.model.User;
import org.meesho.model.UserPost;

public interface MySocialMedia {
  public User createUser(String username, String fullName) throws UserException;

  public User getUser(String username) throws UserException;

  public void createPost(String username, String postId, String post, LocalDateTime timestamp)
      throws UserException;

  public boolean deletePost(String username, String postId) throws UserException;

  public UserPost searchPost(String postId) throws PostException;

  public void follow(String username, String toFollowUserId) throws UserException;

  public boolean unfollow(String username, String toUnFollowUserId) throws UserException;

  public List<UserPost> showTimeLine(String username, int pastRecord) throws UserException;
}
