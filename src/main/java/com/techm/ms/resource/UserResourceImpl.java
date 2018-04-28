package com.techm.ms.resource;

import com.techm.ms.exception.CustomError;
import com.techm.ms.model.Storage;
import com.techm.ms.model.User;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserResourceImpl implements UserResource {

    @RequestMapping(path = "/user", method = RequestMethod.POST)
    public ResponseEntity<String> user(@RequestBody User user) {
        //Success
        if (createNewUser(user))
            return new ResponseEntity<>("User was created successfully.",
                    HttpStatus.CREATED);

        //Failure
        return new ResponseEntity<>("Unable to create. An account with name already exists.",
                HttpStatus.CONFLICT);

    }

    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public ResponseEntity getUser(@RequestParam long id) {
        //Success
        if (Storage.getUsers().containsKey(id))
            return new ResponseEntity<>(Storage.getUsers().get(id),
                    HttpStatus.FOUND);

        //Failure
        return new ResponseEntity<>(new CustomError("Account with id " + id + " not found.",
                "NOT_FOUND"),
                HttpStatus.NOT_FOUND);

    }

    private boolean createNewUser(User user) {
        if (Storage.getUsers(). //get users map from Storage class
                values(). // gets users as collection
                stream().map(User::getName). // convert Users stream to User.name stream
                noneMatch(s -> s.equals(user.getName()))) // if no match found for the input user name in the stream
            // then put the user data as new user and return true
        {
            Storage.getUsers().put(user.getId(), user);
            return true;
        }
        return false;
    }

//    @RequestMapping(path = "/user/{name}", method = RequestMethod.GET)
//    public ResponseEntity<String> userName(@PathVariable String name) {
//        //Success
//        if (createNewUser(name))
//            return new ResponseEntity<>("User creation successful.", HttpStatus.CREATED);
//
//        //Failure
//        return new ResponseEntity<>("User already exists.", HttpStatus.CONFLICT);
//
//    }
//
//    private boolean createNewUser(String name) {
//        return !Storage.getNames().contains(name) && Storage.getNames().add(name);
//
//    }
}
