package com.serbatic.facturas.service;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.serbatic.facturas.accessingData.User;
import com.serbatic.facturas.accessingData.UserRepository;

@Service
public class UserService implements UserServiceInterface {

  @Autowired
  private UserRepository userRepository;

  @Override
  public User addNewUser(String name, String firstSurname, String secondSurname, String email) {
    User n = new User();
    n.setName(name);
    n.setFirstSurname(firstSurname);
    n.setSecondSurname(secondSurname);
    n.setEmail(email);
    return userRepository.save(n);
  }

  @Override
  public User updateUserPartially(Long userId, User userDetails) throws ResourceNotFoundException {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));

    user.setName(userDetails.getName());
    user.setFirstSurname(userDetails.getFirstSurname());
    user.setSecondSurname(userDetails.getSecondSurname());
    user.setEmail(userDetails.getEmail());

    return userRepository.save(user);
  }

  @Override
  public User findUser(Long userId) throws ResourceNotFoundException {
    return userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("User not found on : " + userId));
  }

  @Override
  public void deleteEmployee(Long id) {
    userRepository.deleteById(id);
  }

  @Override
  public Iterable<User> getAllUsers() {
    return userRepository.findAll();
  }

}
