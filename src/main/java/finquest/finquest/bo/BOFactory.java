package finquest.finquest.bo;

import finquest.finquest.bo.custom.impl.*;

public class BOFactory {
    public enum BOType {
        BUDGET,DASHBOARD, EXPENSE, INCOME, FINANCIALACCOUNT, FINANCIALGOAL, LOGIN, REMINDER, REGISTER
    }

    public static SuperBO getBO(BOType boType) {
        switch (boType) {
            case BUDGET:
                return new BudgetBOImpl();
            case DASHBOARD:
                return new DashboardBOImpl();
            case EXPENSE:
                return  new ExpenseBOImpl();
            case INCOME:
                return new IncomeBOImpl();
            case FINANCIALACCOUNT:
                return new FinancialAccountBOImpl();
            case FINANCIALGOAL:
                return new FinancialGoalBOImpl();
            case LOGIN:
                return new LoginBOImpl();
            case REMINDER:
                return new ReminderBOImpl();
            case REGISTER:
                return new RegisterBOImpl();
            default:
                return null;
        }
    }
}
