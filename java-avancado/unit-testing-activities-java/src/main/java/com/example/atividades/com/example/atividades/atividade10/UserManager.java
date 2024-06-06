package com.example.atividades.atividade10;

public class UserManager {
    private UserService userService;

    public UserManager(UserService userService) {
        this.userService = userService;
    }

    public User fetchUserInfo(int userId) {
        User user = userService.getUserInfo(userId);
        if (user == null) { 
        	throw new RuntimeException("User not found");
        }
        return user;
    }
}
