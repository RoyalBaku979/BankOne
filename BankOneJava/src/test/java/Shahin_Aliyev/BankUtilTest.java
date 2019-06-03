package Shahin_Aliyev;

import Shahin_Aliyev.Config.Config;
import Shahin_Aliyev.Dao.impl.PercentageImplDao;
import Shahin_Aliyev.Util.BankUtil;
import Shahin_Aliyev.beans.Account;
import Shahin_Aliyev.beans.Customer;
import Shahin_Aliyev.beans.Percentage;
import Shahin_Aliyev.beans.TypeOfAccount;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.*;

public class BankUtilTest {
private static Account account;

@Mock
    PercentageImplDao percentageImplDao;

@Mock
   Config config;

@InjectMocks
  BankUtil bankUtil;
@Before
  public void setup()
    {
        MockitoAnnotations.initMocks(this);
         bankUtil=new BankUtil();
         account=Account.getInstance();
        account.setNumberOfAccount("12345");
        account.setTypeOfAccount(TypeOfAccount.Saving);
         Set<Customer> setOfCustomer=new TreeSet<>();
         Customer customer=Customer.getInstance();
         customer.setDateOfJoinedBank(ZonedDateTime.of(2013,12,23,16,16,43,44, ZoneId.of("Europe/Berlin")));
         setOfCustomer.add(customer);

        Mockito.when(percentageImplDao.getInterestByAccount(account)).thenReturn(0.01);
        Mockito.when(config.getListofCustomer1()).thenReturn(setOfCustomer);

    }

    @Test
    public void createCustomer() {
        String name="Sahin";
        String surname="Aliyev";
        String password="12345";
        String email="sahin.aliyev979@gmail.com";
        String dateOfBirht="12.06.1993";
        ZonedDateTime dateOfJoinBank=ZonedDateTime.now();
        int amountOfInternationTransfer=0;

       boolean result=bankUtil.createCustomer(name,surname,email,password,dateOfBirht,dateOfJoinBank,amountOfInternationTransfer);
        assertEquals(result,true);
    }
    @Test
    public void createCustomerNameNull() {
        String name="";
        String surname="Aliyev";
        String password="12345";
        String email="sahin.aliyev979@gmail.com";
        String dateOfBirht="12.06.1993";
        ZonedDateTime dateOfJoinBank=ZonedDateTime.now();
        int amountOfInternationTransfer=0;

        boolean result=bankUtil.createCustomer(name,surname,email,password,dateOfBirht,dateOfJoinBank,amountOfInternationTransfer);
        assertEquals(result,false);
    }
    @Test
    public void createCustomerSurnameNull() {
        String name="Shahin";
        String surname="";
        String password="12345";
        String email="sahin.aliyev979@gmail.com";
        String dateOfBirht="12.06.1993";
        ZonedDateTime dateOfJoinBank=ZonedDateTime.now();
        int amountOfInternationTransfer=0;

        boolean result=bankUtil.createCustomer(name,surname,email,password,dateOfBirht,dateOfJoinBank,amountOfInternationTransfer);
        assertEquals(result,false);
    }
    @Test
    public void createCustomerPasswordNull() {
        String name="Shahin";
        String surname="Aliyev";
        String password="";
        String email="sahin.aliyev979@gmail.com";
        String dateOfBirht="12.06.1993";
        ZonedDateTime dateOfJoinBank=ZonedDateTime.now();
        int amountOfInternationTransfer=0;

        boolean result=bankUtil.createCustomer(name,surname,email,password,dateOfBirht,dateOfJoinBank,amountOfInternationTransfer);
        assertEquals(result,false);
    }
    @Test
    public void createCustomerEmailNull() {
        String name="Shahin";
        String surname="Aliyev";
        String password="1234";
        String email="";
        String dateOfBirht="12.06.1993";
        ZonedDateTime dateOfJoinBank=ZonedDateTime.now();
        int amountOfInternationTransfer=0;

        boolean result=bankUtil.createCustomer(name,surname,email,password,dateOfBirht,dateOfJoinBank,amountOfInternationTransfer);
        assertEquals(result,false);
    }
    @Test
    public void createCustomerWhenDateOFBitrhNull() {
        String name="Shahin";
        String surname="Aliyev";
        String password="1234";
        String email="sahin.aliyev979@gmail.com";
        String dateOfBirht="";
        ZonedDateTime dateOfJoinBank=ZonedDateTime.now();
        int amountOfInternationTransfer=0;

        boolean result=bankUtil.createCustomer(name,surname,email,password,dateOfBirht,dateOfJoinBank,amountOfInternationTransfer);
        assertEquals(result,false);
    }
    @Test
    public void genarateCustomerNumber() {

        String result=bankUtil.genarateCustomerNumber();
        String number=config.getListofCustomer1().size()+"";
        int size= number.length();
        while(size<5)
        {
            number="0"+number;
            size= number.length();
        }


        assertEquals(result,number);
    }
    @Test
    public void getInterestRate() {
        //Account account=Account.getInstance();

        account.setNumberOfAccount("12345");
        account.setTypeOfAccount(TypeOfAccount.Saving);


        double result=bankUtil.getInterestRate(account,percentageImplDao);
        Assert.assertEquals(result, 0.01, 0.001);
      Mockito.verify(percentageImplDao,Mockito.atLeastOnce()).getInterestByAccount(account);


    }

}