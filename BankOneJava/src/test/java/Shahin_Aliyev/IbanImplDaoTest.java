package Shahin_Aliyev;

import Shahin_Aliyev.Config.Config;
import Shahin_Aliyev.Dao.impl.CustomerImplDao;
import Shahin_Aliyev.Dao.impl.IbanImplDao;
import Shahin_Aliyev.beans.Account;
import Shahin_Aliyev.beans.Customer;
import Shahin_Aliyev.beans.IbanClass;
import Shahin_Aliyev.beans.TypeOfAccount;
import org.junit.Assert;
import org.junit.Before;
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

public class IbanImplDaoTest {
private static Account account;
private static IbanClass ibanClass;
  private static  List<IbanClass> listOfIbans;
    @Mock
    Config config;
    @InjectMocks
    IbanImplDao ibanImplDao;


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
        account.setTypeOfAccount(TypeOfAccount.Regular);
        account.setCustomerAccount(customer);

        account.setNumberOfAccount("1200001001");
        account.setTransactionnumber(0);
         ibanClass=new IbanClass();
        ibanClass.setIBAN("saal12061993");
        ibanClass.setAccountNumber(account.getNumberOfAccount());
        listOfIbans=new ArrayList<>();
        listOfIbans.add(ibanClass);
        Mockito.when(config.getListOfIbans1()).thenReturn(listOfIbans);
    }
    @Test
    public void getIbanByAccount(){

        IbanClass result=ibanImplDao.getIbanByAccount(account);
        Assert.assertEquals(result,ibanClass);
        Mockito.verify(config,Mockito.atLeastOnce()).getListOfIbans1();
    }
    @Test
    public void getIbanByAccountifNull(){
        account.setNumberOfAccount("122213123");
        IbanClass result=ibanImplDao.getIbanByAccount(account);
        Assert.assertEquals(result,null);
        Mockito.verify(config,Mockito.atLeastOnce()).getListOfIbans1();
    }
    @Test
    public void getAll(){

        int size=ibanImplDao.getAll().size();
        Assert.assertEquals(size,listOfIbans.size());
        Mockito.verify(config,Mockito.atLeastOnce()).getListOfIbans1();
    }
}
