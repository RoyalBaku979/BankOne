package uni.lodz.pl.java.Util;

import uni.lodz.pl.java.Config.Config;
import uni.lodz.pl.java.beans.Account;
import uni.lodz.pl.java.beans.Customer;
import uni.lodz.pl.java.beans.TypeOfAccount;

public class CustomerUtil {
    public static boolean OpenAccount(TypeOfAccount typeOfAccount, Double amountOfMoney, Customer customer,String nameOfAccount){
        Account account=new Account();
        account.setAmount(0);
        account.setNumberOfAccount(0);
        account.setTypeOfAccount(typeOfAccount);
        account.setAmountOfMoney(amountOfMoney);
        account.setCustomerAccount(customer);
       if(EmployerUtil.AccecptNewAccount(account))
       {
           account.setAcceptedAccount(true);
           Config.AddListOfAccount(account);
           return true;

       }
       else
       {

           return false;
       }

    }
}
