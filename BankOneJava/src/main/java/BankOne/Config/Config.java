package BankOne.Config;



import BankOne.beans.*;


import java.util.List;
import java.util.Set;

public class Config {
    private Set<Customer>listofCustomer1;
    private List<IbanClass>listOfIbans1;
    private   List<Account>listOfAccount1;
    private  List<Percentage>listofPercentage1;

    private   List<Transaction> ListOfTransaction;

    private  Customer customer;





    public   List<Transaction> getSetsOfTransactions() {
        return ListOfTransaction;
    }

    public  void addSetsOfTransactions(Transaction transactions) {
        ListOfTransaction.add(transactions);
    }

    public  Customer getCustomer() {
        return this.customer;
    }

    public  void setCustomer(Customer customer) {
        this.customer = customer;
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
