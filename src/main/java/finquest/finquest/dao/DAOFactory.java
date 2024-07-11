package finquest.finquest.dao;

import finquest.finquest.dao.custom.impl.*;

public class DAOFactory {

    public enum DAOType {
        BUDGET, EXPENSE, INCOME, FINANCIALACCOUNT, FINANCIALGOAL, USER, REMINDER
    }

    public static SuperDAO getDAO(DAOType daoType) {
        switch (daoType) {
            case BUDGET:
                return new BudgetDAOImpl();
            case EXPENSE:
                return new ExpenseDAOImpl();
            case INCOME:
                return new IncomeDAOImpl();
            case FINANCIALACCOUNT:
                return new FinancialAccountDAOImpl();
            case FINANCIALGOAL:
                return new FinantialGoalDAOImpl();
            case USER:
                return new UserDAOImpl();
            case REMINDER:
                return new ReminderDAOImpl();
            default:
                return null;
        }
    }
}
