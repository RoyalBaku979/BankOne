package uni.lodz.pl.java.Config;

import uni.lodz.pl.java.beans.Account;
import uni.lodz.pl.java.beans.Customer;
import uni.lodz.pl.java.beans.Transaction;

import java.util.List;

public class Config {
    private static List<Account>ListOfAccount=null;
    private static List<Customer>ListofCustomer=null;
    private static List<Transaction>LitsOfTransactions=null;

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


}
