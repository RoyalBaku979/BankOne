package Shahin_Aliyev.Util;

import Shahin_Aliyev.Config.Config;
import Shahin_Aliyev.Dao.impl.AccountImplDao;
import Shahin_Aliyev.Dao.impl.PercentageImplDao;
import Shahin_Aliyev.beans.Account;
import Shahin_Aliyev.beans.Customer;
import Shahin_Aliyev.beans.Percentage;
import Shahin_Aliyev.beans.TypeOfAccount;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BankUtil {
    Config config;
public boolean createCustomer(String name, String surname, String email, String password, String dateOfBirht, ZonedDateTime dateOfJoinBank, int amountOfInternationTransfer){
    Customer customer=Customer.getInstance();
    if(name.trim().isEmpty() ||surname.trim().isEmpty() || email.trim().isEmpty() || password.trim().isEmpty()
   || dateOfBirht.trim().isEmpty())
    {

        return false;
    }
    else {

        customer.setDateOfBirth(dateOfBirht);
        customer.setDateOfJoinedBank(ZonedDateTime.now());
        customer.setEmail(email);
        customer.setName(name);
        customer.setSurname(surname);
        customer.setPassword(password);
        customer.setAmountOfInternationalTransfer(0);
        config.addListofCustomer(customer);
        customer.setCustomerNumber(genarateCustomerNumber());
        return true;
       }

}
public  String genarateCustomerNumber() {
   String number=config.getListofCustomer().size()+"";
       int size= number.length();
       while(size<5)
       {
           number="0"+number;
           size= number.length();
       }

    return number;

}
public  Runnable interestRateProcces(List<Account>listOfSavingAccount, PercentageImplDao percentageImplDao){

    Runnable r=new Runnable() {
        @Override
        public void run() {
            for (Account savingAccount:listOfSavingAccount) {
                savingAccount.setAmount(savingAccount.getAmount()+savingAccount.getAmount()*getInterestRate(savingAccount,percentageImplDao));

            }
        }
    };
  return r;
}

    public  double getInterestRate(Account account,PercentageImplDao percentageImplDao) {
        // PercentageImplDao percentageImplDao=new PercentageImplDao();
        return percentageImplDao.getInterestByAccount(account);


    }

private  boolean AddInterestRate(PercentageImplDao percentageImplDao){


   try {
       Runnable r=interestRateProcces(new AccountImplDao().getAllByType(TypeOfAccount.Saving),percentageImplDao );
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