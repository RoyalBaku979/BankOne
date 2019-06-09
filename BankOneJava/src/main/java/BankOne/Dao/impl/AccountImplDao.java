package BankOne.Dao.impl;

import BankOne.Config.Config;
import BankOne.beans.Account;
import BankOne.beans.Customer;
import BankOne.Dao.inter.AccountInterDao;
import BankOne.beans.TypeOfAccount;

import java.util.ArrayList;
import java.util.List;

public class AccountImplDao implements AccountInterDao {
  Config config;
    @Override
    public  List<Account> getAll() {
       return config.getListOfAccount1();
    }

    @Override
    public List<Account> getAllByCustomer(Customer customer) {
       List<Account>listOfAccount=getAll();

        List<Account>listOfAccountByCustomer=new ArrayList<>();
        for (Account account:listOfAccount) {

            if(account.getCustomerAccount().equals(customer))
            {
                listOfAccountByCustomer.add(account);
            }

        }
        return listOfAccountByCustomer;
    }

    @Override
    public List<Account> getAllByType(TypeOfAccount typeOfAccount) {
        List<Account>listOfAccount=getAll();
        List<Account>listOfAccountByType=new ArrayList<>();
        for (Account account:listOfAccount) {
            if(account.getTypeOfAccount().equals(typeOfAccount))
            {
                listOfAccountByType.add(account);
            }

        }
        return listOfAccountByType;
    }


}
