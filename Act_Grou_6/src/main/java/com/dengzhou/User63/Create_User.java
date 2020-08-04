package com.dengzhou.User63;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.User;

public class Create_User {
    public void createUser(IdentityService identityService,String id,String first,String last,String email,String password){
        User user = identityService.newUser(id);
        user.setFirstName(first);
        user.setLastName(last);
        user.setEmail(email);
        user.setPassword(password);
        identityService.saveUser(user);
    }
}
