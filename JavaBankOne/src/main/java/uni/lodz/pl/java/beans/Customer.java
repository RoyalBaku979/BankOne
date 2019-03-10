package uni.lodz.pl.java.beans;

import java.util.Date;
import java.util.List;

public class Customer {
    private String name;
    private  String surname;
    private Date dateOfBirth;
    private Date dateOfJoinedBank;
    private int amountOfInternationalTransfer;



    private List<Account>listOfAccount;

    public Customer(String name, String surname, Date dateOfBirth, Date dateOfJoinedBank) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.dateOfJoinedBank = dateOfJoinedBank;
    }

    public Customer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfJoinedBank() {
        return dateOfJoinedBank;
    }

    public void setDateOfJoinedBank(Date dateOfJoinedBank) {
        this.dateOfJoinedBank = dateOfJoinedBank;
    }

    public List<Account> getListOfAccount() {
        return listOfAccount;
    }

    public void setListOfAccount(List<Account> listOfAccount) {
        this.listOfAccount = listOfAccount;
    }
    public int getAmountOfInternationalTransfer() {
        return amountOfInternationalTransfer;
    }

    public void setAmountOfInternationalTransfer(int amountOfInternationalTransfer) {
        this.amountOfInternationalTransfer = amountOfInternationalTransfer;
    }
}
