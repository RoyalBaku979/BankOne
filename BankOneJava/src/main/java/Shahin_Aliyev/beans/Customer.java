package Shahin_Aliyev.beans;

import java.util.Date;
import java.util.List;

public class Customer {
    private String CustomerNumber;
    private String name;
    private  String surname;
    private  String email;
    private  String password;
    private String dateOfBirth;
    private Date dateOfJoinedBank;
    private static int amountOfInternationalTransfer;
    private List<Account>listOfAccount;
    private List<Transaction>listFfTransaction;
    private static Customer instance;
    private Customer(){}
    public void setCustomerNumber(String customerNumber) {
        CustomerNumber = customerNumber;
    }
    public String getCustomerNumber() {
        return CustomerNumber;
    }
    public void addAccountList(Account account) {
         listOfAccount.add(account);

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public List<Account> getListOfAccount() {
        return listOfAccount;
    }

    public List<Transaction> getListFfTransaction() {
        return listFfTransaction;
    }

    public void AddTransaction(Transaction transaction) {
       listFfTransaction.add(transaction);
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfJoinedBank() {
        return dateOfJoinedBank;
    }

    public void setDateOfJoinedBank(Date dateOfJoinedBank) {
        this.dateOfJoinedBank = dateOfJoinedBank;
    }

    public static int getAmountOfInternationalTransfer() {
        return amountOfInternationalTransfer;
    }

    public static void setAmountOfInternationalTransfer(int amountOfInternationalTransfer) {
        Customer.amountOfInternationalTransfer = amountOfInternationalTransfer;
    }
    public static Customer getInstance(){
        if (instance == null)
        {
            //synchronized block to remove overhead
            synchronized (Customer.class)
            {
                if(instance==null)
                {
                    // if instance is null, initialize
                    instance = new Customer();
                }

            }
        }
        return instance;
    }

    @Override
    public String toString() {
        String customer="Name:"+getName()+";Surname:"+getSurname()+";Email:"+getEmail()+":DataofBirth:"+getDateOfBirth();
        return customer;
    }
}
