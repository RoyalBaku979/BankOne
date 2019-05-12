package uni.lodz.pl.java.Dao.impl;

import uni.lodz.pl.java.Config.Config;
import uni.lodz.pl.java.Dao.inter.AccountInterDao;
import uni.lodz.pl.java.beans.Account;
import uni.lodz.pl.java.beans.Customer;
import uni.lodz.pl.java.beans.TypeOfAccount;

import java.util.ArrayList;
import java.util.List;

public class AccountImplDao implements AccountInterDao {
    @Override
    public  List<Account> getAll() {
       return Config.getListOfAccount();
    }

    @Override
    public List<Account> getAllByCustomer(Customer customer) {
       List<Account>listOfAccount=Config.getListOfAccount();
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
        List<Account>listOfAccount=Config.getListOfAccount();
        List<Account>listOfAccountByType=new ArrayList<>();
        for (Account account:listOfAccount) {
            if(account.getTypeOfAccount().equals(typeOfAccount))
            {
                listOfAccountByType.add(account);
            }

        }
        return listOfAccountByType;
    }

    @Override
    public boolean update(Account account) {
        return false;
    }

    @Override
    public boolean add(Account account) {
        return false;
    }

    @Override
    public boolean remove(Account account) {
        return false;
    }
}
