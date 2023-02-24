package Models;

import java.io.Serializable;

public class NowUser implements Serializable {
    private int idUser;
    private String fname;
    private String lname;
    private String login;
    private String passwrod;
    private String rolee;
    private int idEmployee;
    private int idSpecialti;

    private String specialtieName;
    private String resultSalary;

    public String getResultSalary() {
        return resultSalary;
    }

    public void setResultSalary(String resultSalary) {
        this.resultSalary = resultSalary;
    }

    private static NowUser instance;
    public NowUser(){

    }
    public static NowUser getInstance(){
        if(instance == null){
            instance = new NowUser();
            return instance;
        }
        return instance;
    }
    public NowUser(int idUser, String fname, String lname, String login, String passwrod, String rolee){
        this.idUser = idUser;
        this.fname = fname;
        this.lname = lname;
        this.login = login;
        this.passwrod = passwrod;
        this.rolee = rolee;
    }
    public NowUser(String fname, String lname, String login, String passwrod){
        this.fname = fname;
        this.lname = lname;
        this.login = login;
        this.passwrod = passwrod;
    }
    public static void setInstance(NowUser nowUser){
        instance = nowUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    public int getIdUser() {
        return idUser;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }
    public String getFname() {
        return fname;
    }
    public void setLname(String lname) {
        this.lname = lname;
    }
    public String getLname() {
        return lname;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getLogin() {
        return login;
    }
    public void setPasswrod(String passwrod) {
        this.passwrod = passwrod;
    }
    public String getPasswrod() {
        return passwrod;
    }

    public String getRolee() {
        return rolee;
    }

    public void setRolee(String rolee) {
        this.rolee = rolee;
    }
    public String getSpecialtieName() {
        return specialtieName;
    }

    public void setSpecialtieName(String specialtieName) {
        this.specialtieName = specialtieName;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }
    public int getIdSpecialti() {
        return idSpecialti;
    }

    public void setIdSpecialti(int idSpecialti) {
        this.idSpecialti = idSpecialti;
    }
}
