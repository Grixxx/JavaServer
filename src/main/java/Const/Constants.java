package Const;

public class Constants {

    public static final String USER_TABLE = "users";
    public static final String USER_ID = "idUser";
    public static final String USER_FNAME= "firstName";
    public static final String USER_LNAME= "lastName";
    public static final String USER_LOGIN = "login";
    public static final String USER_PASSWORD = "password";
    public static final String USER_ROLE = "rolee";


    public static final String EMPLOYEE_TABLE = "employees";
    public static final String EMPLOYEE_ID = "idEmployee";
    public static final String EMPLOYEE_IDFK_USER = "idUserFK";
    public static final String EMPLOYEE_IDFK_SPEC = "idSpecialtieFK";
    public static final String EMPLOYEE_RESULT = "resultSalary";
    public static final String EMPLOYEE_RATE = "monthlyTariffRate";

    public static final String TIME_SALARY_TABLE = "timesalary";
    public static final String TIME_SALARY_ID = "idTimeSalary";
    public static final String TIME_SALARY_USER_ID = "idEmployeeFK";
    public static final String TIME_SALARY_PAYHOUR = "paymentPerHour";
    public static final String TIME_SALARY_HOUR = "hours";
    public static final String TIME_SALARY_PREMIUM = "premium";

    public static final String SPECIALTIE_TABLE = "specialties";
    public static final String SPECIALTIE_ID = "idSpecialtie";
    public static final String SPECIALTIE_NAME = "specialtieName";


}
