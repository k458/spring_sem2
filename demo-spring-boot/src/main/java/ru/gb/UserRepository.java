package ru.gb;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component // userRepository
public class UserRepository {

  private final List<User> users;

  public UserRepository() {
    this.users = new ArrayList<>();
    users.add(new User("Igor"));
    users.add(new User("User #2"));
    users.add(new User("User #3"));
    users.add(new User("User #4"));
    users.add(new User("User #5"));
  }

  public List<User> getAll() {
    return List.copyOf(users);
  }

  public boolean addByName(String name)
  {
    try {
      users.add(new User(name));
      return true;
    }
    catch (Exception e){
      e.printStackTrace();
    }
    return false;
  }

  public User getById(long id) {
    return users.stream()
      .filter(it -> Objects.equals(it.getId(), id))
      .findFirst()
      .orElse(null);
  }

  public boolean delById(long id) {
    User user = users.stream()
            .filter(it -> Objects.equals(it.getId(), id))
            .findFirst()
            .orElse(null);
    if (user != null){
      users.remove(user);
      return true;
    }
    return false;
  }

}
