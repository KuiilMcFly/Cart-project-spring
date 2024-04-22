package com.carmine.UserCart.Controllers;

import com.carmine.UserCart.Models.User;
import com.carmine.UserCart.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers(){
        try {
            List<User> userList = new ArrayList<>();           //Create the userList
            userRepository.findAll().forEach(userList::add);   //Find all the users and add it userList for each user found

            if (userList.isEmpty()){                            //If the userList is Empty will get the error "No Content"
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.OK);         //If is full will get the status "OK"
        } catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);  //For other Errors
        }
    }
    @GetMapping("/getUserById/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
       Optional<User> userData = userRepository.findById(id);
       if (userData.isEmpty()) {
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       }
       return new ResponseEntity<>(userData.get(), HttpStatus.OK);
    }

    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestBody User user){
        User userObj = userRepository.save(user);
        return new ResponseEntity<>(userObj, HttpStatus.OK);
    }
    @PostMapping("/updateUsersById/{id}")
    public ResponseEntity<User> updateUserById(@PathVariable Long id, @RequestBody User newUserData) {
        Optional<User> oldUserData = userRepository.findById(id);

        if (oldUserData.isPresent()){
           User updateUserData = oldUserData.get();
           updateUserData.setName(newUserData.getName());
           updateUserData.setSurname(newUserData.getSurname());
           updateUserData.setPhone(newUserData.getPhone());
           updateUserData.setCash(newUserData.getCash());

            User userObj = userRepository.save(updateUserData);
            return new ResponseEntity<>(userObj, HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/deleteUserById/{id}")
    public ResponseEntity<HttpStatus> deleteUserById(@PathVariable Long id) {
        userRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
