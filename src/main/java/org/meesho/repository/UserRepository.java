package org.meesho.repository;

import java.util.HashMap;
import org.meesho.model.User;

public class UserRepository {
  public static HashMap<String, User> userData = new HashMap<>();

  public User getUser(String username) {
    return userData.getOrDefault(username, null);
  }

  public void addUser(String username, User user) {
    userData.put(username, user);
  }
}
