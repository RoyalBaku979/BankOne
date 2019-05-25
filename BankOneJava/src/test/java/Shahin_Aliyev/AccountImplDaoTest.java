package Shahin_Aliyev;

import Shahin_Aliyev.Config.Config;
import Shahin_Aliyev.Dao.impl.AccountImplDao;
import Shahin_Aliyev.Util.CustomerUtil;
import Shahin_Aliyev.beans.Account;
import Shahin_Aliyev.beans.Customer;
import Shahin_Aliyev.beans.TypeOfAccount;
import org.junit.Test;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AccountImplDaoTest {


   public Customer createCustomer(String email,String password,String customerNumber) {
      Customer customer=Customer.getInstance();
      customer.setEmail(email);
      customer.setCustomerNumber(customerNumber);
      customer.setPassword(password);
      customer.setDateOfJoinedBank(ZonedDateTime.now());
      Config.addListofCustomer(customer);
      Config.setCustomer(customer);
      return  customer;
  }

   public Account createAccount(String email, String password, String customerNumber, TypeOfAccount typeOfAccount){

      CustomerUtil customerUtil=new CustomerUtil();
      Account account=Account.getInstance();
      account.setTypeOfAccount(typeOfAccount);
      account.setCustomerAccount(createCustomer(email,password,customerNumber));
      account.setNumberOfAccount(customerUtil.genarateAccountNumber(Config.getCustomer()));
      account.setTransactionnumber(0);
      Config.addListOfAccount(account);
      return account;

  }
    @Test
    public void getAllByCustomer(){
         AccountImplDao accountImplDao=new AccountImplDao();
         Account account=createAccount("sahin","123","0.01",TypeOfAccount.International);
        List<Account>list=accountImplDao.
        getAllByCustomer(account.getCustomerAccount());
        List<Account> list2=new ArrayList<>();
        for (Account a:Config.getListOfAccount()) {
            if(a.getCustomerAccount().equals(account.getCustomerAccount()))
            {
                list2.add(a);
            }
        }
        assertEquals(list.size(),list2.size());

    }
    @Test
    public void getAllByType(){
        AccountImplDao accountImplDao=new AccountImplDao();
        Account account=createAccount("sahin","123","0.01",TypeOfAccount.International);
        List<Account>list=accountImplDao.getAllByType(TypeOfAccount.International);
        List<Account> list2=new ArrayList<>();
        for (Account a:Config.getListOfAccount()) {
            if(a.getTypeOfAccount()==account.getTypeOfAccount())
            {
                list2.add(a);
            }
        }
        assertEquals(list.size(),list2.size());

    }
}
