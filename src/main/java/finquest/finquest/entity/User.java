package finquest.finquest.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor

@Data
public class User {
    private String id;
    private String username;
    private String password;


    public User(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
