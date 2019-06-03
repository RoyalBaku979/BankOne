package Shahin_Aliyev.Util;

import Shahin_Aliyev.Dao.impl.AccountImplDao;
import Shahin_Aliyev.Dao.impl.IbanImplDao;
import Shahin_Aliyev.Dao.impl.PercentageImplDao;
import Shahin_Aliyev.beans.Account;
import Shahin_Aliyev.beans.Customer;
import Shahin_Aliyev.beans.Percentage;
import Shahin_Aliyev.beans.TypeOfAccount;

import java.sql.SQLOutput;

public class EmployerUtil {
      IbanImplDao ibanImplDao;
      PercentageImplDao percentageImplDao;
      AccountImplDao accountImplDao;
    public   Account accecptNewAccount(Account account) {
        System.out.println("Test Called");
        if(checkAccount(account)!=null)
        {
            return account;
        }
        else
        {
            return null;

        }

    }
    public  Account checkAccount(Account account) {

        if(account.getCustomerAccount()==null || account.getTypeOfAccount()==null
                || account.getNumberOfAccount().trim().isEmpty())
        {

            return  null;
        }
        else {
            return checkAccountBType(account);
        }


    }
    public   Account checkAccountBType(Account account) {
        if(account.getTypeOfAccount()==TypeOfAccount.Regular)
        {
            return account;
        }
        else
        {
            return checkSavingAndInternatinolAccount(account);
        }
    }
    public   Account checkSavingAndInternatinolAccount(Account account) {
        if(account.getTypeOfAccount()==TypeOfAccount.International)
        {
            return checkInternationalAccount(account);
        }
        else
        {
            return checkSavingAccount(account);
        }
    }
    public    Account checkSavingAccount(Account account) {


        if(percentageImplDao.getInterestByAccount(account)==0.01) {
            return account;
        }
        else
        {
            return null;
        }




    }
    public   Account checkInternationalAccount(Account account) {


        if(ibanImplDao.getIbanByAccount(account)!=null) {
            Account account1=checkIbanNumber(account);
            return account1;
        }
        else
        {
            return null;
        }

    }
    public  Account checkIbanNumber(Account account){

        String iBan=ibanImplDao.getIbanByAccount(account).getIBAN();
        if(iBan.trim().isEmpty() || iBan.length()<10)
        {
            return  null;
        }
        else
        {
            return account;
        }

    }
    public  void lookPersonelDateOfCustomer(Customer customer) {

        System.out.println("Customer name:" + customer.getName());
        System.out.println("Customer name:" + customer.getSurname());
        System.out.println("Customer name:" + customer.getDateOfBirth());
        System.out.println("Customer name:" + customer.getDateOfJoinedBank());
        System.out.println("Account");
        for (Account account : accountImplDao.getAllByCustomer(customer)) {
            System.out.println("Account Information:" + account.toString());

        }
    }
}
