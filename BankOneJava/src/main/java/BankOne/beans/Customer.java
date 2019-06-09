package BankOne.beans;

import java.time.ZonedDateTime;

public class Customer implements Comparable<Customer> {
    private String CustomerNumber;
    private String name;
    private  String surname;
    private  String email;
    private  String password;
    private String dateOfBirth;
    private ZonedDateTime dateOfJoinedBank;
    private static int amountOfInternationalTransfer;
    private static Customer instance;
    private Customer(){}
    public void setCustomerNumber(String customerNumber) {
        CustomerNumber = customerNumber;
    }
    public String getCustomerNumber() {
        return CustomerNumber;
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
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public ZonedDateTime getDateOfJoinedBank() {
        return dateOfJoinedBank;
    }

    public void setDateOfJoinedBank(ZonedDateTime dateOfJoinedBank) {
        this.dateOfJoinedBank = dateOfJoinedBank;
    }

    public static int getAmountOfInternationalTransfer() {
        return amountOfInternationalTransfer;
    }

    public static void setAmountOfInternationalTransfer(int amountOfInternationalTransfer) {
        Customer.amountOfInternationalTransfer = amountOfInternationalTransfer;
    }
    public static Customer getInstance(){
       return new Customer();
    }

    @Override
    public String toString() {
        String customer="Name:"+getName()+";Surname:"+getSurname()+";Email:"+getEmail()+":DataofBirth:"+getDateOfBirth();
        return customer;
    }

    @Override
    public int compareTo(Customer o) {
        return this.getDateOfJoinedBank().compareTo(o.getDateOfJoinedBank());

    }

    @Override
    public boolean equals(Object obj) {
          if(obj instanceof Customer)
          {
              Customer customer=(Customer)obj;
              if(customer.getCustomerNumber().equals(this.CustomerNumber))
              {
                  return true;
              }
          }
          return false;
    }
}
