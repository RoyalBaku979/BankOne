package Shahin_Aliyev.Util;

import Shahin_Aliyev.Config.Config;
import Shahin_Aliyev.Dao.impl.AccountImplDao;
import Shahin_Aliyev.Dao.impl.PercentageImplDao;
import Shahin_Aliyev.beans.Account;
import Shahin_Aliyev.beans.Customer;
import Shahin_Aliyev.beans.TypeOfAccount;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
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