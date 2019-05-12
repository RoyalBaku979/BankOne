package Shahin_Aliyev.Config;



import Shahin_Aliyev.beans.*;

import java.util.ArrayList;
import java.util.List;

public class Config {
    private static List<Account>ListOfAccount=null;
    private static List<Customer>ListofCustomer=new ArrayList<>();
    private static List<Transaction>LitsOfTransactions=null;
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

    public static void AddListOfAccount(Account account) {
        ListOfAccount.add(account);
    }

    public static List<Customer> getListofCustomer() {
        return ListofCustomer;
    }

    public static void AddListofCustomer(Customer customer) {
           ListofCustomer.add(customer);
    }

    public static List<Transaction> getLitsOfTransactions() {
        return LitsOfTransactions;
    }

    public static void AddLitsOfTransactions(Transaction transactions) {
         LitsOfTransactions.add(transactions);
    }

    public static Customer getCustomer() {
        return customer;
    }

    public static void setCustomer(Customer customer) {
        Config.customer = customer;
    }
}
