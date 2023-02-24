package Result;

import Models.Salary;

import java.math.BigDecimal;

public class Calculate {

    public static BigDecimal calculateTimeSalary(){
        BigDecimal result = Salary.getInstance().getPaymentPerHour().multiply(new BigDecimal(Salary.getInstance().getHours()));
        return result;
    }
    public static BigDecimal calculateTimePremiumSalary(){
        BigDecimal timeSalary = Salary.getInstance().getPaymentPerHour().multiply(new BigDecimal(Salary.getInstance().getHours()));
        int resultPremium = 1+Salary.getInstance().getPremium()/100;
        BigDecimal result = timeSalary.multiply(new BigDecimal(resultPremium));
        return result;
    }



    public static BigDecimal calculateStraightPieceworkSalary(){
       BigDecimal rate = Salary.getInstance().getHourlyRate().divide(new BigDecimal(Salary.getInstance().getProductionRate()));
       BigDecimal result0 = rate.multiply(new BigDecimal(Salary.getInstance().getNumberOfProducts()));
       BigDecimal result = result0.multiply(new BigDecimal(Salary.getInstance().getNumberOfItems()));
        return result;
    }

    public static BigDecimal calculatePremiumPieceworkSalary(){
        BigDecimal rate = Salary.getInstance().getHourlyRate().divide(new BigDecimal(Salary.getInstance().getProductionRate()));
        BigDecimal result0 = rate.multiply(new BigDecimal(Salary.getInstance().getNumberOfProducts()));
        BigDecimal result1 = result0.multiply(new BigDecimal(Salary.getInstance().getNumberOfItems()));
        int prem = 1+((Salary.getInstance().getPerformanceBonusPercentage()+Salary.getInstance().getOverfulfillmentBonusPercentage()+Salary.getInstance().getOverfulfillmentBonusPlan())/100);
        BigDecimal result = result1.multiply(new BigDecimal(prem));
        return result;
    }
    public static BigDecimal calculateProgressivePieceworkSalary(){
        BigDecimal rate = Salary.getInstance().getHourlyRate().divide(new BigDecimal(Salary.getInstance().getProductionRate()));
        BigDecimal result0 = rate.multiply(new BigDecimal(Salary.getInstance().getNumberOfProducts()));



        BigDecimal result1 = result0.multiply(new BigDecimal(Salary.getInstance().getСolProductsPlan()));

        int n = Salary.getInstance().getColProductsReleased()-Salary.getInstance().getСolProductsPlan();
        BigDecimal result2x = result0.multiply(new BigDecimal(n));
        BigDecimal result2 = result2x.multiply(new BigDecimal(Salary.getInstance().getCoefficient()));
        BigDecimal result = result1.add(result2);

        return result;
    }
    public static BigDecimal calculateIndirectPieceworkSalary(){
        int n = Salary.getInstance().getAuxiliaryServiceRate()*Salary.getInstance().getProductionRateMain();
        BigDecimal rate = Salary.getInstance().getAncillaryHourlyRate().divide(new BigDecimal(n));

        BigDecimal result0 = rate.multiply(new BigDecimal(Salary.getInstance().getNumberOfProducts()));
        BigDecimal result = result0.multiply(new BigDecimal(Salary.getInstance().getNumberOfItems()));
        return result;
    }
    public static BigDecimal calculateAccordPieceworkSalary(){
        int time = Salary.getInstance().getAllWorkingTime()*Salary.getInstance().getEmployeeTime();
        BigDecimal result = Salary.getInstance().getTotalEarnings().divide(new BigDecimal(time));
        return result;
    }
    public static BigDecimal calculateAccordPremiumPieceworkSalary(){
        int time = Salary.getInstance().getAllWorkingTime()*Salary.getInstance().getEmployeeTime();
        BigDecimal result1 = Salary.getInstance().getTotalEarnings().divide(new BigDecimal(time));
        int resultPremium = 1+Salary.getInstance().getPremiumAcord()/100;
        BigDecimal result = result1.multiply(new BigDecimal(resultPremium));
        return result;
    }
}
