package Shahin_Aliyev.Util;

import Shahin_Aliyev.Dao.impl.AccountImplDao;
import Shahin_Aliyev.Dao.impl.IbanImplDao;
import Shahin_Aliyev.beans.Account;
import Shahin_Aliyev.beans.Customer;
import Shahin_Aliyev.beans.TypeOfAccount;

public class EmployerUtil {
    public static boolean AccecptNewAccount(Account account) {
     if(CheckAccount(account))
     {
         return true;
     }
     else
     {
         return false;

     }

    }
    public static boolean CheckAccount (Account account) {

           if(account.getCustomerAccount()==null || account.getTypeOfAccount()==null
            || account.getNumberOfAccount().trim().isEmpty())
           {

               return  false;
           }
           else {
               return CheckInternationalAccount(account);
           }


    }
    public  static boolean CheckInternationalAccount(Account account) {
        IbanImplDao ibanImplDao=new IbanImplDao();
        if(account.getTypeOfAccount()==TypeOfAccount.International)
        {
            if(ibanImplDao.getIbanByAccount(account)!=null) {
                    return CheckIbanNumber(ibanImplDao.getIbanByAccount(account).getIBAN());
                }
            else
                {
                return false;
                }

        }

            return true;


    }
    public static boolean CheckIbanNumber(String iBan){
      if(iBan.trim().isEmpty() || iBan.length()<10)
      {
          return  false;
      }
      else
      {
          return true;
      }

    }
    public static void LookPersonelDateOfCustomer(Customer customer) {
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
