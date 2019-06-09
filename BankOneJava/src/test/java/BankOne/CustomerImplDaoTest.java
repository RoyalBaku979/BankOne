package BankOne;

import BankOne.Config.Config;
import BankOne.Dao.impl.CustomerImplDao;
import BankOne.beans.Customer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

public class CustomerImplDaoTest {

    @Mock
    Config config;
    @InjectMocks
      CustomerImplDao customerImplDao;


    @Before
      public void setup(){
        MockitoAnnotations.initMocks(this);
        Set<Customer> set=new TreeSet<>();
       Customer  customer1=Customer.getInstance();
        customer1.setCustomerNumber("00001");
        customer1.setDateOfJoinedBank(ZonedDateTime.of(2000,12,23,12,11,43,44,ZoneId.of("Europe/Berlin")));

        Customer  customer2=Customer.getInstance();
        customer2.setCustomerNumber("00002");
        customer2.setDateOfJoinedBank(ZonedDateTime.of(2012,10,23,11,12,43,44,ZoneId.of("Europe/Berlin")));

        Customer  customer3=Customer.getInstance();
        customer3.setCustomerNumber("00003");
        customer3.setDateOfJoinedBank(ZonedDateTime.of(2010,12,23,13,13,43,44,ZoneId.of("Europe/Berlin")));

        Customer  customer4=Customer.getInstance();
        customer4.setCustomerNumber("00004");
        customer4.setDateOfJoinedBank(ZonedDateTime.of(2011,12,23,14,14,43,44,ZoneId.of("Europe/Berlin")));

        Customer  customer5=Customer.getInstance();
        customer5.setCustomerNumber("00005");
        customer5.setDateOfJoinedBank(ZonedDateTime.of(2012,12,23,15,15,43,44,ZoneId.of("Europe/Berlin")));

        Customer  customer6=Customer.getInstance();
        customer6.setCustomerNumber("00006");
        customer6.setDateOfJoinedBank(ZonedDateTime.of(2013,12,23,16,16,43,44,ZoneId.of("Europe/Berlin")));
        set.add(customer1);
        set.add(customer2);
        set.add(customer3);
        set.add(customer4);
        set.add(customer5);
        Mockito.when(config.getListofCustomer1()).thenReturn(set);
    }
    @Test
      public void getFiveNewClient(){

        int size=customerImplDao.getFiveNewClient().size();
        Assert.assertEquals(size,5);
        Mockito.verify(config,Mockito.atLeastOnce()).getListofCustomer1();
    }

    @Test
    public void getAll(){

        int size=customerImplDao.getAll().size();
        Assert.assertEquals(size,5);
        Mockito.verify(config,Mockito.atLeastOnce()).getListofCustomer1();
    }
}
