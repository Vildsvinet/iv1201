package se.kth.iv1201.example;

import java.util.HashMap;
import java.util.Map;

public class FakeDatabase {
    private Map<String, User> database;

    public FakeDatabase() {
        database = new HashMap<>();
    }

    public void createAccount(String firstName, String lastName, String email,
                              String personNumber, String username, String password) {
        User user = new User(firstName, lastName, email, personNumber, username, password);
        database.put(username, user);
    }

    public boolean login(String username, String password) {
        User user = database.get(username);
        return user != null && user.getPassword().equals(password);
    }

    private static class User {
        private String firstName;
        private String lastName;
        private String email;
        private String personNumber;
        private String username;
        private String password;

        public User(String firstName, String lastName, String email, String personNumber,
                    String username, String password) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.personNumber = personNumber;
            this.username = username;
            this.password = password;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getEmail() {
            return email;
        }

        public String getPersonNumber() {
            return personNumber;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }
}
