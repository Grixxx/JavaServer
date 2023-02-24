package Models;

import java.io.Serializable;
import java.math.BigDecimal;

public class Salary  implements Serializable  {
    private BigDecimal paymentPerHour;
    private int hours;
    private int premium;
    private BigDecimal hourlyRate;
    private int productionRate;
    private int numberOfItems;
    private int numberOfProducts;
    private int performanceBonusPercentage;
    private int overfulfillmentBonusPercentage;
    private int overfulfillmentBonusPlan;
    private int сolProductsPlan;
    private int coefficient;
    private int colProductsReleased;
    private BigDecimal ancillaryHourlyRate;
    private int auxiliaryServiceRate;
    private int  productionRateMain;
    private BigDecimal totalEarnings;
    private int  allWorkingTime;
    private int  employeeTime;
    private int  premiumAcord;



    private BigDecimal resultSalary;





    private static Salary instance;



    public Salary(){

    }
    public static Salary getInstance(){
        if(instance == null){
            instance = new Salary();
            return instance;
        }
        return instance;
    }


    public BigDecimal getPaymentPerHour() {
        return paymentPerHour;
    }

    public void setPaymentPerHour(BigDecimal paymentPerHour) {
        this.paymentPerHour = paymentPerHour;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getPremium() {
        return premium;
    }

    public void setPremium(int premium) {
        this.premium = premium;
    }

    public BigDecimal getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(BigDecimal hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public int getProductionRate() {
        return productionRate;
    }

    public void setProductionRate(int productionRate) {
        this.productionRate = productionRate;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    public int getNumberOfProducts() {
        return numberOfProducts;
    }

    public void setNumberOfProducts(int numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
    }

    public int getPerformanceBonusPercentage() {
        return performanceBonusPercentage;
    }

    public void setPerformanceBonusPercentage(int performanceBonusPercentage) {
        this.performanceBonusPercentage = performanceBonusPercentage;
    }

    public int getOverfulfillmentBonusPercentage() {
        return overfulfillmentBonusPercentage;
    }

    public void setOverfulfillmentBonusPercentage(int overfulfillmentBonusPercentage) {
        this.overfulfillmentBonusPercentage = overfulfillmentBonusPercentage;
    }

    public int getOverfulfillmentBonusPlan() {
        return overfulfillmentBonusPlan;
    }

    public void setOverfulfillmentBonusPlan(int overfulfillmentBonusPlan) {
        this.overfulfillmentBonusPlan = overfulfillmentBonusPlan;
    }

    public int getСolProductsPlan() {
        return сolProductsPlan;
    }

    public void setСolProductsPlan(int сolProductsPlan) {
        this.сolProductsPlan = сolProductsPlan;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    public int getColProductsReleased() {
        return colProductsReleased;
    }

    public void setColProductsReleased(int colProductsReleased) {
        this.colProductsReleased = colProductsReleased;
    }

    public BigDecimal getAncillaryHourlyRate() {
        return ancillaryHourlyRate;
    }

    public void setAncillaryHourlyRate(BigDecimal ancillaryHourlyRate) {
        this.ancillaryHourlyRate = ancillaryHourlyRate;
    }

    public int getAuxiliaryServiceRate() {
        return auxiliaryServiceRate;
    }

    public void setAuxiliaryServiceRate(int auxiliaryServiceRate) {
        this.auxiliaryServiceRate = auxiliaryServiceRate;
    }

    public int getProductionRateMain() {
        return productionRateMain;
    }

    public void setProductionRateMain(int productionRateMain) {
        this.productionRateMain = productionRateMain;
    }
    public BigDecimal getTotalEarnings() {
        return totalEarnings;
    }

    public void setTotalEarnings(BigDecimal totalEarnings) {
        this.totalEarnings = totalEarnings;
    }

    public int getAllWorkingTime() {
        return allWorkingTime;
    }

    public void setAllWorkingTime(int allWorkingTime) {
        this.allWorkingTime = allWorkingTime;
    }

    public int getEmployeeTime() {
        return employeeTime;
    }

    public void setEmployeeTime(int employeeTime) {
        this.employeeTime = employeeTime;
    }

    public int getPremiumAcord() {
        return premiumAcord;
    }

    public void setPremiumAcord(int premiumAcord) {
        this.premiumAcord = premiumAcord;
    }

    public static void setInstance(Salary instance) {
        Salary.instance = instance;
    }

    public BigDecimal getResultSalary() {
        return resultSalary;
    }

    public void setResultSalary(BigDecimal resultSalary) {
        this.resultSalary = resultSalary;
    }
}

