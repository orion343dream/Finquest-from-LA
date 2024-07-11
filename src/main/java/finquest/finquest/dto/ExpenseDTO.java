package finquest.finquest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExpenseDTO {
    private String id;
    private String userId;
    private double amount;
    private String description;
    private Date date;
    private String category;
}
