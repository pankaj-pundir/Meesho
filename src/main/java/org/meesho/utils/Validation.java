package org.meesho.utils;

import org.meesho.exception.PostException;
import org.meesho.exception.UserException;
import org.meesho.repository.PostRepository;
import org.meesho.repository.UserRepository;

public class Validation {
  private final UserRepository userRepository;
  private final PostRepository postRepository;

  public Validation(UserRepository userRepository, PostRepository postRepository) {
    this.userRepository = userRepository;
    this.postRepository = postRepository;
  }

  public void validateUser(String username) throws UserException {
    if (userRepository.getUser(username) == null) {
      throw new UserException("User not found");
    }
  }

  public void validatePost(String postId) throws PostException {
    if (postRepository.getPost(postId) == null) {
      throw new PostException("User not found");
    }
  }
}
