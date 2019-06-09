package BankOne;

import BankOne.Config.Config;
import BankOne.Dao.impl.AccountImplDao;
import BankOne.Util.CustomerUtil;
import BankOne.beans.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AccountImplDaoTest {
    private static Account account;
    private static List<Account> accountList;
    private static List<Account> internationalAccountList;
    private static Customer customer;
    @Mock
    Config config;
    @InjectMocks
    AccountImplDao accountImplDao;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
         customer=Customer.getInstance();
      Account  savingAccount=Account.getInstance();
        customer=Customer.getInstance();
        customer.setCustomerNumber("00001");
        customer.setEmail("sahin.aliyeb979@gmail.com");
        customer.setDateOfBirth("12.06.1993");
        customer.setName("Sahin");
        customer.setSurname("Aliyev");
        customer.setPassword("123456");
        customer.setDateOfJoinedBank(ZonedDateTime.now());

        savingAccount.setCustomerAccount(customer);
        savingAccount.setTypeOfAccount(TypeOfAccount.Saving);

        savingAccount.setNumberOfAccount("1200001001");
        Percentage percentage=new Percentage();
        percentage.setPercentage(0.01);
        percentage.setAccountNumber(savingAccount.getNumberOfAccount());

        savingAccount.setTransactionnumber(0);



      Account  regularAccount=Account.getInstance();
        regularAccount.setTypeOfAccount(TypeOfAccount.Regular);
        regularAccount.setCustomerAccount(customer);

        regularAccount.setNumberOfAccount("1200001002");
        regularAccount.setTransactionnumber(0);


      Account  internationalAccount=Account.getInstance();
        internationalAccount.setCustomerAccount(customer);
        internationalAccount.setTypeOfAccount(TypeOfAccount.International);
        internationalAccount.setTransactionnumber(0);

        internationalAccount.setNumberOfAccount("1200001003");
       IbanClass ibanClass=new IbanClass();
        ibanClass.setIBAN("0123456789");
        ibanClass.setAccountNumber(internationalAccount.getNumberOfAccount());

      CustomerUtil customerUtil=new CustomerUtil();
        customerUtil.addInterestRate(savingAccount);
    accountList=new ArrayList<>();
     accountList.add(internationalAccount);
     accountList.add(savingAccount);
     accountList.add(regularAccount);

        internationalAccountList=new ArrayList<>();
        internationalAccountList.add(internationalAccount);
     Mockito.when(config.getListOfAccount1()).thenReturn(accountList);



    }
    @Test
    public void getAllByCustomer(){

        assertEquals(accountList.size(),accountImplDao.getAllByCustomer(customer).size());
        Mockito.verify(config,Mockito.atLeastOnce()).getListOfAccount1();

    }
    @Test
    public void getAllByType(){
        Mockito.when(config.getListOfAccount1()).thenReturn(internationalAccountList);

        assertEquals(internationalAccountList.size(),accountImplDao.getAllByType(TypeOfAccount.International).size());
        Mockito.verify(config,Mockito.atLeastOnce()).getListOfAccount1();
    }
    @Test
    public void getAll(){

        assertEquals(accountList.size(),accountImplDao.getAll().size());
        Mockito.verify(config,Mockito.atLeastOnce()).getListOfAccount1();

    }
}
