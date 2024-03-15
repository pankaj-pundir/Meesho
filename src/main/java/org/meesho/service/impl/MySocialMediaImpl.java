package org.meesho.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.meesho.exception.PostException;
import org.meesho.exception.UserException;
import org.meesho.model.User;
import org.meesho.model.UserPost;
import org.meesho.repository.FollowRepository;
import org.meesho.repository.PostRepository;
import org.meesho.repository.UserRepository;
import org.meesho.service.MySocialMedia;
import org.meesho.utils.Validation;

@Slf4j
public class MySocialMediaImpl implements MySocialMedia {

  private final UserRepository userRepository = new UserRepository();
  private final PostRepository postRepository = new PostRepository();

  private final FollowRepository followRepository = new FollowRepository();

  public final Validation validation = new Validation(userRepository, postRepository);

  @Override
  public User createUser(String username, String fullName) throws UserException {
    if (userRepository.getUser(username) != null) {
      throw new UserException("User already exists");
    }
    User user = User.builder().fullName(fullName).username(username).build();
    userRepository.addUser(username, user);

    log.info("user created {}", username);
    return user;
  }

  @Override
  public User getUser(String username) throws UserException {
    if (userRepository.getUser(username) == null) {
      throw new UserException("User does not exists");
    }

    return userRepository.getUser(username);
  }

  @Override
  public void createPost(String username, String postId, String postData, LocalDateTime timestamp)
      throws UserException {
    validation.validateUser(username);

    // create post
    UserPost post =
        UserPost.builder()
            .username(username)
            .postedOn(timestamp)
            .postData(postData)
            .postId(postId)
            .build();

    postRepository.addPost(postId, post);

    log.info("{} created post with postId {}", username, postId);
  }

  @Override
  public boolean deletePost(String username, String postId) throws UserException {
    validation.validateUser(username);

    if (postRepository.isUserOfPost(username, postId)) {

      postRepository.deletePost(username, postId);
      log.info("{} deleted post with postId {}", username, postId);
      return true;
    }
    log.info("post not found for user {} and post Id {}", username, postId);
    return false;
  }

  @Override
  public UserPost searchPost(String postId) throws PostException {
    validation.validatePost(postId);
    UserPost postData = postRepository.getPost(postId);
    return postData;
  }

  @Override
  public void follow(String username, String followingUserName) throws UserException {
    validation.validateUser(username);
    validation.validateUser(followingUserName);

    followRepository.addFollower(username, followingUserName);
    log.info("{} is following {}", username, followingUserName);
  }

  @Override
  public boolean unfollow(String username, String followingUserName) throws UserException {
    validation.validateUser(username);
    validation.validateUser(followingUserName);

    if (followRepository.isFollowing(username, followingUserName)) {
      log.info("{} trying to unfollow {}", username, followingUserName);
      followRepository.removeFollower(username, followingUserName);
      log.info("{} unfollowed {}", username, followingUserName);
      return true;
    }
    log.info("{} is not following {}", username, followingUserName);
    return false;
  }

  @Override
  public List<UserPost> showTimeLine(String username, int pastRecord) throws UserException {
    validation.validateUser(username);
    List<UserPost> postList = postRepository.showTimeLine(username, pastRecord);
    log.info("{}", postList);
    for (UserPost post : postList) {
      log.info("{}", post.getPostData());
    }
    return postList;
  }
}
