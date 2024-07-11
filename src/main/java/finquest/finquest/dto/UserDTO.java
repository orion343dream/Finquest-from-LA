package finquest.finquest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor

@Data
public class UserDTO {
    private String id;
    private String username;
    private String password;


    public UserDTO(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
