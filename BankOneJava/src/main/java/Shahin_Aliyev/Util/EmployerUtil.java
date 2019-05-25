package Shahin_Aliyev.Util;

import Shahin_Aliyev.Dao.impl.AccountImplDao;
import Shahin_Aliyev.Dao.impl.IbanImplDao;
import Shahin_Aliyev.Dao.impl.PercentageImplDao;
import Shahin_Aliyev.beans.Account;
import Shahin_Aliyev.beans.Customer;
import Shahin_Aliyev.beans.TypeOfAccount;

public class EmployerUtil {

    public static Account accecptNewAccount(Account account) {
        if(checkAccount(account)!=null)
        {
            return account;
        }
        else
        {
            return null;

        }

    }
    public static Account checkAccount(Account account) {

        if(account.getCustomerAccount()==null || account.getTypeOfAccount()==null
                || account.getNumberOfAccount().trim().isEmpty())
        {

            return  null;
        }
        else {
            return checkAccountBType(account);
        }


    }
    public static Account checkAccountBType(Account account) {
        if(account.getTypeOfAccount()==TypeOfAccount.Regular)
        {
            return account;
        }
        else
        {
            return checkSavingAndInternatinolAccount(account);
        }
    }
    public static Account checkSavingAndInternatinolAccount(Account account) {
        if(account.getTypeOfAccount()==TypeOfAccount.International)
        {
            return checkInternationalAccount(account);
        }
        else
        {
            return checkSavingAccount(account);
        }
    }
    public  static Account checkSavingAccount(Account account) {
        PercentageImplDao ibanImplDao=new PercentageImplDao();

        if(ibanImplDao.getInterestByAccount(account).getPercentage()==0.01) {
            return account;
        }
        else
        {
            return null;
        }




    }
    public  static Account checkInternationalAccount(Account account) {
        IbanImplDao ibanImplDao=new IbanImplDao();

        if(ibanImplDao.getIbanByAccount(account)!=null) {
            return checkIbanNumber(account);
        }
        else
        {
            return null;
        }

    }
    public static Account checkIbanNumber(Account account){
        IbanImplDao ibanImplDao=new IbanImplDao();
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
    public static void lookPersonelDateOfCustomer(Customer customer) {
        AccountImplDao accountImplDao=new AccountImplDao();
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
