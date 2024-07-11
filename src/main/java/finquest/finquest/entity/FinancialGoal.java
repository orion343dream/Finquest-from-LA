package finquest.finquest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FinancialGoal {
    private String name;
    private double amount;
    private Date date;
    private String category;
}
