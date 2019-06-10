package BankOne;

import BankOne.Dao.impl.CustomerImplDao;
import BankOne.Dao.impl.PercentageImplDao;
import BankOne.Util.BankUtil;
import BankOne.beans.Account;
import BankOne.beans.Customer;
import BankOne.beans.Percentage;
import BankOne.beans.TypeOfAccount;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.*;

public class BankUtilTest {
private static Account account;
private static BankUtil bankUtilMock;
@Mock
    PercentageImplDao percentageImplDao;

@Mock
    CustomerImplDao customerImplDao;


@InjectMocks
  BankUtil bankUtil;
@Before
  public void setup()
    {
        MockitoAnnotations.initMocks(this);

         account=Account.getInstance();
        account.setNumberOfAccount("12345");
        account.setTypeOfAccount(TypeOfAccount.Saving);
         Set<Customer> setOfCustomer=new TreeSet<>();
          Customer customer=Customer.getInstance();
          customer.setDateOfJoinedBank(ZonedDateTime.of(2013,12,23,16,16,43,44, ZoneId.of("Europe/Berlin")));
          customer.setCustomerNumber("00001");
          customer.setEmail("sahin.aliyeb979@gmail.com");
          customer.setDateOfBirth("12.06.1993");
          customer.setName("Sahin");
          customer.setSurname("Aliyev");
          customer.setPassword("123456");
        Percentage percentage=new Percentage();
        percentage.setInterestRateTime(ZonedDateTime.now());
        percentage.setAccountNumber(account.getNumberOfAccount());
        percentage.setPercentage(0.01);
         setOfCustomer.add(customer);

        Mockito.when(percentageImplDao.getInterestByAccount(account)).thenReturn(percentage);
        Mockito.when(customerImplDao.getAll()).thenReturn(setOfCustomer);
           bankUtilMock = Mockito.spy(bankUtil);
    }

    @Test
    public void createCustomer() {
      Mockito.doReturn("00001").when(bankUtilMock).genarateCustomerNumber();

        String name="Sahin";
        String surname="Aliyev";
        String password="12345";
        String email="sahin.aliyev979@gmail.com";
        String dateOfBirht="12.06.1993";
        ZonedDateTime dateOfJoinBank=ZonedDateTime.now();
        int amountOfInternationTransfer=0;

       boolean result=bankUtilMock.createCustomer(name,surname,email,password,dateOfBirht,dateOfJoinBank,amountOfInternationTransfer);
        assertEquals(result,true);
        Mockito.verify(bankUtilMock,Mockito.atLeastOnce()).genarateCustomerNumber();
    }
    @Test
    public void createCustomerNameNull() {
        Mockito.doReturn("00001").when(bankUtilMock).genarateCustomerNumber();
        Mockito.verify(bankUtilMock,Mockito.never()).genarateCustomerNumber();
        String name="";
        String surname="Aliyev";
        String password="12345";
        String email="sahin.aliyev979@gmail.com";
        String dateOfBirht="12.06.1993";
        ZonedDateTime dateOfJoinBank=ZonedDateTime.now();
        int amountOfInternationTransfer=0;

        boolean result=bankUtilMock.createCustomer(name,surname,email,password,dateOfBirht,dateOfJoinBank,amountOfInternationTransfer);
        assertEquals(result,false);
    }
    @Test
    public void createCustomerSurnameNull() {
        Mockito.doReturn("00001").when(bankUtilMock).genarateCustomerNumber();
        Mockito.verify(bankUtilMock,Mockito.never()).genarateCustomerNumber();
        String name="Shahin";
        String surname="";
        String password="12345";
        String email="sahin.aliyev979@gmail.com";
        String dateOfBirht="12.06.1993";
        ZonedDateTime dateOfJoinBank=ZonedDateTime.now();
        int amountOfInternationTransfer=0;

        boolean result=bankUtilMock.createCustomer(name,surname,email,password,dateOfBirht,dateOfJoinBank,amountOfInternationTransfer);
        assertEquals(result,false);
    }
    @Test
    public void createCustomerPasswordNull() {
        Mockito.doReturn("00001").when(bankUtilMock).genarateCustomerNumber();
        Mockito.verify(bankUtilMock,Mockito.never()).genarateCustomerNumber();
        String name="Shahin";
        String surname="Aliyev";
        String password="";
        String email="sahin.aliyev979@gmail.com";
        String dateOfBirht="12.06.1993";
        ZonedDateTime dateOfJoinBank=ZonedDateTime.now();
        int amountOfInternationTransfer=0;

        boolean result=bankUtilMock.createCustomer(name,surname,email,password,dateOfBirht,dateOfJoinBank,amountOfInternationTransfer);
        assertEquals(result,false);
    }
    @Test
    public void createCustomerEmailNull() {
        Mockito.doReturn("00001").when(bankUtilMock).genarateCustomerNumber();
        Mockito.verify(bankUtilMock,Mockito.never()).genarateCustomerNumber();
        String name="Shahin";
        String surname="Aliyev";
        String password="1234";
        String email="";
        String dateOfBirht="12.06.1993";
        ZonedDateTime dateOfJoinBank=ZonedDateTime.now();
        int amountOfInternationTransfer=0;

        boolean result=bankUtilMock.createCustomer(name,surname,email,password,dateOfBirht,dateOfJoinBank,amountOfInternationTransfer);
        assertEquals(result,false);
    }
    @Test
    public void createCustomerWhenDateOFBitrhNull() {
        Mockito.doReturn("00001").when(bankUtilMock).genarateCustomerNumber();
        Mockito.verify(bankUtilMock,Mockito.never()).genarateCustomerNumber();
        String name="Shahin";
        String surname="Aliyev";
        String password="1234";
        String email="sahin.aliyev979@gmail.com";
        String dateOfBirht="";
        ZonedDateTime dateOfJoinBank=ZonedDateTime.now();
        int amountOfInternationTransfer=0;

        boolean result=bankUtilMock.createCustomer(name,surname,email,password,dateOfBirht,dateOfJoinBank,amountOfInternationTransfer);
        assertEquals(result,false);
    }
    @Test
    public void genarateCustomerNumber() {


        String number=customerImplDao.getAll().size()+"";
        int size= number.length();
        while(size<5)
        {
            number="0"+number;
            size= number.length();
        }

        String result=bankUtil.genarateCustomerNumber();
        assertEquals(result,number);
        Mockito.verify(customerImplDao,Mockito.atLeastOnce()).getAll();
    }
    @Test
    public void getInterestRate() {


        account.setNumberOfAccount("12345");
        account.setTypeOfAccount(TypeOfAccount.Saving);


        double result=bankUtil.getInterestRate(account).getPercentage();
        Assert.assertEquals(result, 0.01, 0.001);
      Mockito.verify(percentageImplDao,Mockito.atLeastOnce()).getInterestByAccount(account);


    }

}