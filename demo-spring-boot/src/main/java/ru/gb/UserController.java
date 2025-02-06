package ru.gb;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
@RestController
@RequestMapping("/users")
public class UserController {

  // REST
  // GET /users/{id} => 404 (Not Found)
  // GET /users?nameLike='Igor%' => 204 (No Content)

  // DELETE /users/{id} - удаляет
  // POST   /users/      {"id": "1", "name": "newName"} - создает
  // PUT    /users/{id}  {"id": "1", "name": "newName"} - изменение

  // HTTP HyperText Transfer Protocol
  // GET POST PUT PATCH DELETE ... HEAD OPTIONS

  // http://ip-address/users/all -> List<User>
  // http://ip-address/users/1 -> User(1, Igor)
  // http://ip-address/users?name=Igor -> User(1, Igor)

  private final UserRepository repository;

  public UserController(UserRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/all")
  public String getUsers() {
    System.out.println(repository.getAll().toString());
    return repository.getAll().toString();
  }

  @GetMapping(value = "/add/{name}")
  public String addUserByName(@PathVariable String name) {
    boolean added = repository.addByName(name);
    if (added){
      return "Added: "+name;
    }
    else{
      return "FAIL";
    }
  }

  @GetMapping(value = "/get/{id}")
  public String getUserById(@PathVariable long id) {
    User user = repository.getById(id);
    if (user != null){
      return user.toString();
    }
    else{
      return "FAIL";
    }
  }

  @GetMapping(value = "/change/{id} {name}")
  public String changeUserById(@PathVariable long id, @PathVariable String name) {
    User user = repository.getById(id);
    if (user != null){
      user.setName(name);
      return "Renamed to: "+name;
    }
    else{
      return "FAIL";
    }
  }

  @GetMapping(value = "/del/{id}")
  public String delUserById(@PathVariable long id) {
    boolean deleted = repository.delById(id);
    if (deleted){
      return "Deleted: "+id;
    }
    else{
      return "FAIL";
    }
  }
}
