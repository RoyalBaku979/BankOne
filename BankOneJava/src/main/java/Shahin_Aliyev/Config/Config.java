package Shahin_Aliyev.Config;



import Shahin_Aliyev.beans.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Config {
    private static List<Account>ListOfAccount=new ArrayList<>();
    private static Set<Customer>ListofCustomer=new TreeSet<>();
    private static Set<Transaction> SetOfTransactions=new TreeSet<>();
    private static List<IbanClass>listofIbans=new ArrayList<>();
    private static List<Percentage>listofPercentage=new ArrayList<>();
    private static Customer customer=null;

    public static List<IbanClass> getListofIbans() {
        return listofIbans;
    }

    public static void addListofIbans(IbanClass ibans) {
        listofIbans.add(ibans);
    }

    public static List<Percentage> getListofPercentage() {
        return listofPercentage;
    }

    public static void setListofPercentage(Percentage percentage) {
        listofPercentage.add(percentage);
    }

    public static List<Account> getListOfAccount() {
        return ListOfAccount;
    }

    public static void addListOfAccount(Account account) {
        ListOfAccount.add(account);
    }

    public static Set<Customer> getListofCustomer() {
        return ListofCustomer;
    }

    public static void addListofCustomer(Customer customer) {
           ListofCustomer.add(customer);
    }

    public static Set<Transaction> getSetsOfTransactions() {
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
}
