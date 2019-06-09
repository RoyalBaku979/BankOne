package BankOne;

import BankOne.Config.Config;
import BankOne.Dao.impl.TrasactionImplDao;
import BankOne.beans.*;
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

import static org.junit.Assert.assertEquals;

public class TransactionImplDaoTest {
    private static Customer customer;
    private static Customer customer1;
   private TrasactionImplDao trasactionImplDaoMock;
  private static Account RegularAccount;
   private static List<Transaction> transactionsSet;

    @Mock
    Config configMock;



    @InjectMocks
    TrasactionImplDao trasactionImplDao;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);


        customer= Customer.getInstance();
        customer.setCustomerNumber("00001");
        customer.setEmail("sahin.aliyeb979@gmail.com");
        customer.setDateOfBirth("12.06.1993");
        customer.setName("Sahin");
        customer.setSurname("Aliyev");
        customer.setPassword("123456");
        customer.setAmountOfInternationalTransfer(0);
        customer.setDateOfJoinedBank(ZonedDateTime.now());

        customer1= Customer.getInstance();
        customer1.setCustomerNumber("00002");
        customer1.setEmail("sahina.aliyeb979@gmail.com");
        customer1.setDateOfBirth("12.06.1993");
        customer1.setName("Famil");
        customer1.setSurname("Ababayev");
        customer1.setPassword("12345");
        customer1.setAmountOfInternationalTransfer(0);
        customer1.setDateOfJoinedBank(ZonedDateTime.now());
     Account   SavingAccount=Account.getInstance();
        SavingAccount.setCustomerAccount(customer);
        SavingAccount.setTypeOfAccount(TypeOfAccount.Saving);
        SavingAccount.setAmount(100);
        SavingAccount.setNumberOfAccount("1200001001");
        Percentage percentage=new Percentage();
        percentage.setPercentage(0.01);
        percentage.setAccountNumber(SavingAccount.getNumberOfAccount());

        SavingAccount.setTransactionnumber(0);



        RegularAccount=Account.getInstance();
        RegularAccount.setTypeOfAccount(TypeOfAccount.Regular);
        RegularAccount.setCustomerAccount(customer1);
        RegularAccount.setAmount(100);
        RegularAccount.setNumberOfAccount("1200001002");
        RegularAccount.setTransactionnumber(0);

        Account RegularAccount2=Account.getInstance();
        RegularAccount2.setTypeOfAccount(TypeOfAccount.Regular);
        RegularAccount2.setCustomerAccount(customer);
        RegularAccount2.setAmount(100);
        RegularAccount2.setNumberOfAccount("1200001003");
        RegularAccount2.setTransactionnumber(0);


     Account   InternationalAccount=Account.getInstance();
        InternationalAccount.setCustomerAccount(customer);
        InternationalAccount.setTypeOfAccount(TypeOfAccount.International);
        InternationalAccount.setTransactionnumber(0);
        InternationalAccount.setAmount(100);
        InternationalAccount.setNumberOfAccount("1200001004");
       IbanClass ibanClass=new IbanClass();
        ibanClass.setIBAN("0123456789");
        ibanClass.setAccountNumber(InternationalAccount.getNumberOfAccount());
        transactionsSet=new ArrayList<>();
         Transaction transaction1=new Transaction();
        transaction1.setTypeOfTransfer(TypeOfTransfer.CREDIT);
        transaction1.setSenderAccount(RegularAccount);
        transaction1.setRecivierAccount(RegularAccount2);
        transaction1.setAmountOfMoney(100);
        transaction1.setDateOfTransaction(ZonedDateTime.of
                (2011,12,23,14,14,43,44,
                        ZoneId.of("Europe/Berlin")));
        transactionsSet.add(transaction1);
        Transaction transaction2=new Transaction();
        transaction2.setTypeOfTransfer(TypeOfTransfer.WIRE);
        transaction2.setSenderAccount(InternationalAccount);
        transaction2.setRecivierAccount(RegularAccount2);
        transaction2.setAmountOfMoney(100000);
        transaction2.setDateOfTransaction(
                ZonedDateTime.of(2013,12,23,14,14,43,44,
                ZoneId.of("Europe/Berlin")));
        transactionsSet.add(transaction2);
        Transaction transaction3=new Transaction();
        transaction3.setTypeOfTransfer(TypeOfTransfer.DEBIT);
        transaction3.setSenderAccount(SavingAccount);
        transaction3.setRecivierAccount(RegularAccount2);
        transaction3.setAmountOfMoney(1000);
        transaction3.setDateOfTransaction(
                ZonedDateTime.of(2015,12,23,14,14,43,44,
                        ZoneId.of("Europe/Berlin")));
        transactionsSet.add(transaction3);

        Mockito.when(configMock.getSetsOfTransactions()).thenReturn(transactionsSet);
       trasactionImplDaoMock=Mockito.spy(trasactionImplDao);




    }

    @Test
      public void getAll(){
        int result=trasactionImplDao.getAll().size();
        assertEquals(result,transactionsSet.size());

        Mockito.verify(configMock,Mockito.atLeastOnce()).getSetsOfTransactions();

    }

    @Test
    public void getAllByCustomer()
    {
        Mockito.doReturn(transactionsSet).when(trasactionImplDaoMock).getAll();
      int result=trasactionImplDaoMock.getAllByCustomer(customer1).size();
      int acctual=trasactionImplDao.getAllByCustomer(customer1).size();
        assertEquals(result,acctual);
        Mockito.verify(trasactionImplDaoMock,Mockito.atLeastOnce()).getAll();




    }
    @Test
       public void getAllByAccount(){
        Mockito.doReturn(transactionsSet).when(trasactionImplDaoMock).getAll();
        int result=trasactionImplDaoMock.getAllByAccount(RegularAccount).size();
        int acctual=trasactionImplDao.getAllByAccount(RegularAccount).size();
        assertEquals(result,acctual);
        Mockito.verify(trasactionImplDaoMock,Mockito.atLeastOnce()).getAll();

    }
    @Test
    public void getAllByType(){
        Mockito.doReturn(transactionsSet).when(trasactionImplDaoMock).getAll();
        int result=trasactionImplDaoMock.getAllByType(TypeOfTransfer.CREDIT).size();
        int acctual=trasactionImplDao.getAllByType(TypeOfTransfer.CREDIT).size();
        assertEquals(result,acctual);
        Mockito.verify(trasactionImplDaoMock,Mockito.atLeastOnce()).getAll();

    }

    @Test
    public void getAllByHistory(){
        Mockito.doReturn(transactionsSet).when(trasactionImplDaoMock).getAll();
        ZonedDateTime  after=ZonedDateTime.of(2010,12,23,14,14,43,44,ZoneId.of("Europe/Berlin"));
        ZonedDateTime  before=ZonedDateTime.of(2014,12,23,14,14,43,44,ZoneId.of("Europe/Berlin"));
        int result=trasactionImplDaoMock.getAllByHistory(after,before).size();
        int acctual=trasactionImplDao.getAllByHistory(after,before).size();
        assertEquals(result,acctual);
        Mockito.verify(trasactionImplDaoMock,Mockito.atLeastOnce()).getAll();

    }
    @Test
    public void getAllByHistoryByCustomer(){
        List<Transaction>listofTransactionByCustomer=trasactionImplDao.getAllByCustomer(customer1);
        Mockito.doReturn(listofTransactionByCustomer).when(trasactionImplDaoMock).getAllByCustomer(customer);

        ZonedDateTime  after=ZonedDateTime.of(2010,12,23,14,14,43,44,ZoneId.of("Europe/Berlin"));
        ZonedDateTime  before=ZonedDateTime.of(2014,12,23,14,14,43,44,ZoneId.of("Europe/Berlin"));
        int result=trasactionImplDaoMock.getAllByHistory(after,before,customer).size();
        int acctual=trasactionImplDao.getAllByHistory(after,before,customer).size();
        assertEquals(result,acctual);
        Mockito.verify(trasactionImplDaoMock,Mockito.atLeastOnce()).getAllByCustomer(customer);

    }

    @Test
    public void getAllByDay(){
        Mockito.doReturn(transactionsSet).when(trasactionImplDaoMock).getAll();
        ZonedDateTime day=ZonedDateTime.of
                (2011,12,23,14,14,43,44,
                        ZoneId.of("Europe/Berlin"));
        int result=trasactionImplDaoMock.getAllByDay(day).size();
        int acctual=trasactionImplDao.getAllByDay(day).size();
        assertEquals(result,acctual);
        Mockito.verify(trasactionImplDaoMock,Mockito.atLeastOnce()).getAll();

    }

    @Test
    public void getAllByDayByCustomer(){
        List<Transaction>listofTransactionByCustomer=trasactionImplDao.getAllByCustomer(customer1);
        Mockito.doReturn(listofTransactionByCustomer).when(trasactionImplDaoMock).getAllByCustomer(customer1);
        ZonedDateTime day=ZonedDateTime.of
                (2011,12,23,14,14,43,44,
                        ZoneId.of("Europe/Berlin"));
        int result=trasactionImplDaoMock.getAllByDay(day,customer1).size();
        int acctual=trasactionImplDao.getAllByDay(day,customer1).size();
        assertEquals(result,acctual);
        Mockito.verify(trasactionImplDaoMock,Mockito.atLeastOnce()).getAllByCustomer(customer1);

    }
    @Test
    public void getFiveHigestTransactionByAmount(){
        Mockito.doReturn(transactionsSet).when(trasactionImplDaoMock).getAll();
        int result=trasactionImplDaoMock.getFiveHigestTransactionByAmount().size();

        int acctual=trasactionImplDao.getFiveHigestTransactionByAmount().size();
        assertEquals(result,acctual);
        Mockito.verify(trasactionImplDaoMock,Mockito.atLeastOnce()).getAll();

    }

}
