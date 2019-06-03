package Shahin_Aliyev.Config;



import Shahin_Aliyev.beans.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Config {
    private Set<Customer>listofCustomer1;
    private List<IbanClass>listOfIbans1;
    private   List<Account>listOfAccount1;
    private static List<Percentage>listofPercentage1;
    private static Set<Customer>ListofCustomer=new TreeSet<>();
    private  static Set<Transaction> SetOfTransactions=new TreeSet<>();

    private static Customer customer=null;



    public static Set<Customer> getListofCustomer() {
        return ListofCustomer;
    }

    public static void addListofCustomer(Customer customer) {
           ListofCustomer.add(customer);
    }

    public  static Set<Transaction> getSetsOfTransactions() {
        return SetOfTransactions;
    }

    public static void addSetsOfTransactions(Transaction transactions) {
        SetOfTransactions.add(transactions);
    }

    public static Customer getCustomer() {
        return customer;
    }

    public static void setCustomer(Customer customer) {
        Config.customer = customer;
    }

    public  Set<Customer> getListofCustomer1() {
        return listofCustomer1;
    }

    public void setListofCustomer1(Customer customer) {
        listofCustomer1.add(customer);
    }

    public List<IbanClass> getListOfIbans1() {
        return listOfIbans1;
    }

    public void setListOfIbans1(IbanClass ibanClass) {
        listOfIbans1.add(ibanClass);
    }

    public List<Account> getListOfAccount1() {
        return listOfAccount1;
    }

    public void setListOfAccount1(Account account) {
       listOfAccount1.add(account);
    }

    public  List<Percentage> getListofPercentage1() {
        return listofPercentage1;
    }

    public  void setListofPercentage1(Percentage percentage1) {
        listofPercentage1.add(percentage1);
    }
}
