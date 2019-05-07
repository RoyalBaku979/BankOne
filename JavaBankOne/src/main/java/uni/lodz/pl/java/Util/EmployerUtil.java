package uni.lodz.pl.java.Util;

import uni.lodz.pl.java.Config.Config;
import uni.lodz.pl.java.beans.Account;
import uni.lodz.pl.java.beans.Customer;
import uni.lodz.pl.java.beans.IbanClass;
import uni.lodz.pl.java.beans.TypeOfAccount;

import java.util.List;

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
        if(account.getTypeOfAccount()==TypeOfAccount.International)
        {
            for (IbanClass iban : Config.getListofIbans()) {
                if(iban.getAccountNumber().equals(account.getNumberOfAccount()))
                {
                    return  CheckIbanNumber(iban.getIBAN());
                }
            }
            return false;


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
        System.out.println("Customer name:" + customer.getName());
        System.out.println("Customer name:" + customer.getSurname());
        System.out.println("Customer name:" + customer.getDateOfBirth());
        System.out.println("Customer name:" + customer.getDateOfJoinedBank());
        System.out.println("Account");
        for (Account account : Config.getListOfAccount()) {
            System.out.println("Account Information:" + account.toString());

        }
    }
    }
