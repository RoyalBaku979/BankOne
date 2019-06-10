package BankOne;

import BankOne.Config.Config;

import BankOne.Dao.impl.PercentageImplDao;
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

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class PercentageImplDaoTest {
    private static Account account;
    private static Percentage percentage;
    private static List<Percentage> listOfPercentage;
    @Mock
    Config config;
    @InjectMocks
    PercentageImplDao percentageImplDao;


    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        Customer customer=Customer.getInstance();
        customer=Customer.getInstance();
        customer.setCustomerNumber("00001");
        customer.setEmail("sahin.aliyev979@gmail.com");
        customer.setDateOfBirth("12.06.1993");
        customer.setName("Sahin");
        customer.setSurname("Aliyev");
        customer.setPassword("123456");
        customer.setDateOfJoinedBank(ZonedDateTime.now());

        account=Account.getInstance();
        account.setTypeOfAccount(TypeOfAccount.Saving);
        account.setCustomerAccount(customer);

        account.setNumberOfAccount("1200001001");
        account.setTransactionnumber(0);
        percentage=new Percentage();
        percentage.setAccountNumber(account.getNumberOfAccount());
        percentage.setPercentage(new Double(0.01));
       listOfPercentage=new ArrayList<>();
        listOfPercentage.add(percentage);
        Mockito.when(config.getListofPercentage1()).thenReturn(listOfPercentage);
    }
    @Test
    public void getPercenateByAccount(){

        Double result=percentageImplDao.getInterestByAccount(account).getPercentage();
        Assert.assertEquals(result,percentage.getPercentage());
        Mockito.verify(config,Mockito.atLeastOnce()).getListofPercentage1();
    }
    @Test
    public void getPercenateByAccountIfNull(){
        account.setNumberOfAccount("122213123");
        Percentage result=percentageImplDao.getInterestByAccount(account);
        Assert.assertEquals(result,null);
        Mockito.verify(config,Mockito.atLeastOnce()).getListofPercentage1();
    }

    @Test
    public void getAll(){

        int size =listOfPercentage.size();
        Assert.assertEquals(size,percentageImplDao.getAll().size());
        Mockito.verify(config,Mockito.atLeastOnce()).getListofPercentage1();
    }
}
