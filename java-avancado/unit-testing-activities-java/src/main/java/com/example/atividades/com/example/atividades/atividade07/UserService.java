package com.example.atividades.atividade07;

public class UserService {
	private Database db;

    public UserService(Database db) {
        this.db = db;
    }

    public void saveUser(User user) {
        if (user.getName() == null || user.getName().isEmpty() || user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("User must have a name and email");
        }
        db.saveUser(user);
    }
}
