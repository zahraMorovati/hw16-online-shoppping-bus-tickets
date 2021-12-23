package model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String family;
    private double balance;
    private String email;
    @Column(unique = true,nullable = false)
    private String userName;
    private String password;


    public static final class UserBuilder {
        private User user;

        private UserBuilder() {
            user = new User();
        }

        public static UserBuilder anUser() {
            return new UserBuilder();
        }

        public UserBuilder setId(int id) {
            user.setId(id);
            return this;
        }

        public UserBuilder setName(String name) {
            user.setName(name);
            return this;
        }

        public UserBuilder setFamily(String family) {
            user.setFamily(family);
            return this;
        }

        public UserBuilder setBalance(double balance) {
            user.setBalance(balance);
            return this;
        }

        public UserBuilder setEmail(String email) {
            user.setEmail(email);
            return this;
        }

        public UserBuilder setUserName(String userName) {
            user.setUserName(userName);
            return this;
        }

        public UserBuilder setPassword(String password) {
            user.setPassword(password);
            return this;
        }

        public User build() {
            return user;
        }
    }
}
