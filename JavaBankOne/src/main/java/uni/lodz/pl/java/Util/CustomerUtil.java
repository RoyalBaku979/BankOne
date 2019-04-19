package uni.lodz.pl.java.Util;

import uni.lodz.pl.java.Config.Config;
import uni.lodz.pl.java.beans.Account;
import uni.lodz.pl.java.beans.Customer;
import uni.lodz.pl.java.beans.TypeOfAccount;

import java.sql.SQLOutput;

public class CustomerUtil {
    public static boolean SendRequest(TypeOfAccount typeOfAccount, Double amountOfMoney, Customer customer,String nameOfAccount){
        Account account=new Account();
        account.setAmount(0);
        account.setNumberOfAccount(genarateAccountNumber(customer));
        account.setTypeOfAccount(typeOfAccount);
        account.setAmountOfMoney(amountOfMoney);
        account.setCustomerAccount(customer);

       return OpenAccount(account);

    }
    public static boolean OpenAccount(Account account){
        if(EmployerUtil.AccecptNewAccount(account))
        {
            Config.AddListOfAccount(account);
            account.getCustomerAccount().addAccountList(account);
            return true;

        }
        else
        {

            return false;
        }

    }
    public  static String genarateNumber(Customer customer){
        String number=customer.getListOfAccount().size()+"";
        int size= number.length();
        while(size<3)
        {
            number="0"+number;
            size= number.length();
        }

        return number;


    }

    public  static String genarateAccountNumber(Customer customer){
        String banknumber="12";
        String fullNumber=banknumber+customer.getCustomerNumber()+genarateNumber(customer);
        return fullNumber;

    }

  public static String generateIbanNumber(){
 return "IbanNumber";

  }
}
