package com.techm.ms.resource;

import com.techm.ms.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UserResourceImplTest {

    private UserResourceImpl userResource;

    @Before
    public void setup(){
        userResource = new UserResourceImpl();
    }

    @Test
    public void persistUserSuccessfully(){
        ResponseEntity<String> responseEntity = userResource.user(new User(100, "Ravi", 32, 9999));
        Assert.assertEquals("User was created successfully.", responseEntity.getBody());
        Assert.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void persistUserFailForExistingUserName(){
        userResource.user(new User(100, "Ravi", 32, 9999));
        ResponseEntity<String> responseEntity = userResource.user(new User(101, "Ravi", 32, 9999));
        Assert.assertEquals("Unable to create. An account with name already exists.", responseEntity.getBody());
        Assert.assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
    }

}