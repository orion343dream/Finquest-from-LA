package finquest.finquest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Reminder {
    private String userId;
    private Date date;
    private String text;
    private int goalId;
}
