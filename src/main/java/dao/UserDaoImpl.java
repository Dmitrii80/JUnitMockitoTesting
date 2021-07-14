package dao;

import model.User;

import java.util.Arrays;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private List<User> users; // имитируем подключение к базе данных

    public UserDaoImpl() { // заполняем базу данных, пользователями
        this.users = Arrays.asList(
                new User("Olga", "olga@gmail.com"),
                new User("Maxim", "maxim@gmail.com"),
                new User("Artur", "artur@gmail.com"),
                new User("Swing", "swing@gmail.com")
        );
    }

    @Override
    public User getUserByUsername(String username) throws Exception {
        return users.stream().filter(u -> u.getUsername().equals(username))
                .findAny().orElse(null);
    }

    @Override
    public List<User> findAllUsers() {
        return users;
    }
}
