package uni.lodz.pl.java.Util;

import uni.lodz.pl.java.Config.Config;
import uni.lodz.pl.java.Dao.impl.AccountImplDao;
import uni.lodz.pl.java.Dao.impl.PercentageImplDao;
import uni.lodz.pl.java.beans.Account;
import uni.lodz.pl.java.beans.Customer;
import uni.lodz.pl.java.beans.Percentage;
import uni.lodz.pl.java.beans.TypeOfAccount;

import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BankUtil {
public boolean CreateCustomer(String name,String surname,String email,String password,String dateOfBirht,Date dateOfJoinBank,int amountOfInternationTransfer){
    Customer customer=Customer.getInstance();
    if(name.trim().isEmpty() ||surname.trim().isEmpty() || email.trim().isEmpty() || password.trim().isEmpty()
   || dateOfBirht.trim().isEmpty())
    {

        return false;
    }
    else {
        customer.setCustomerNumber(genarateCustomerNumber());
        customer.setDateOfBirth(dateOfBirht);
        customer.setDateOfJoinedBank(Calendar.getInstance().getTime());
        customer.setEmail(email);
        customer.setName(name);
        customer.setSurname(surname);
        customer.setPassword(password);
        customer.setAmountOfInternationalTransfer(0);
        Config.AddListofCustomer(customer);
        return true;
       }

}
public static String genarateCustomerNumber() {
   String number=Config.getListofCustomer().size()+"";
       int size= number.length();
       while(size<5)
       {
           number="0"+number;
           size= number.length();
       }

    return number;

}
private static Runnable interestRateProcces(List<Account>listOfSavingAccount){

    Runnable r=new Runnable() {
        @Override
        public void run() {
            for (Account savingAccount:listOfSavingAccount) {
                savingAccount.setAmount(savingAccount.getAmount()+savingAccount.getAmount()*getInterestRate(savingAccount));

            }
        }
    };
  return r;
}
private static double getInterestRate(Account account) {
    PercentageImplDao percentageImplDao=new PercentageImplDao();
    return percentageImplDao.getInterestByAccount(account).getPercentage();
        

}
private static boolean AddInterestRate(){


   try {
       Runnable r=interestRateProcces(new AccountImplDao().getAllByType(TypeOfAccount.Saving));
       ScheduledExecutorService executor = Executors.newScheduledThreadPool ( 1 );
       executor.scheduleAtFixedRate(r,0L,1L, TimeUnit.MINUTES);
   }
   catch (Exception ex)
   {
       return false;
   }

  return true;

}

   
}