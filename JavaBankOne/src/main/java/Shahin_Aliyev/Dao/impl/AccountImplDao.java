package Shahin_Aliyev.Dao.impl;

import Shahin_Aliyev.Config.Config;
import Shahin_Aliyev.Dao.inter.AccountInterDao;
import Shahin_Aliyev.beans.Account;
import Shahin_Aliyev.beans.Customer;
import Shahin_Aliyev.beans.TypeOfAccount;
import Shahin_Aliyev.Config.Config;
import Shahin_Aliyev.Dao.inter.AccountInterDao;
import Shahin_Aliyev.beans.Account;
import Shahin_Aliyev.beans.Customer;
import Shahin_Aliyev.beans.TypeOfAccount;

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
