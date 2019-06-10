package BankOne;

import BankOne.Util.CustomerUtil;
import BankOne.Util.TransferUtil;
import BankOne.beans.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.ZonedDateTime;

import static org.junit.Assert.assertEquals;

public class TransferUtilTest {
    private  static CustomerUtil customerUtil;
    private static Customer customer;
    private static Account SavingAccount;
    private static IbanClass ibanClass;
    private static Account RegularAccount;
    private static Account InternationalAccount;
    private static TransferUtil transferUtilMock;
    @InjectMocks
        TransferUtil transferUtil;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

    customerUtil=new CustomerUtil();

        customer=Customer.getInstance();
        customer.setCustomerNumber("00001");
        customer.setEmail("sahin.aliyeb979@gmail.com");
        customer.setDateOfBirth("12.06.1993");
        customer.setName("Sahin");
        customer.setSurname("Aliyev");
        customer.setPassword("123456");
        customer.setAmountOfInternationalTransfer(0);
        customer.setDateOfJoinedBank(ZonedDateTime.now());
        SavingAccount=Account.getInstance();
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
        RegularAccount.setCustomerAccount(customer);
         RegularAccount.setAmount(100);
        RegularAccount.setNumberOfAccount("1200001002");
        RegularAccount.setTransactionnumber(0);


        InternationalAccount=Account.getInstance();
        InternationalAccount.setCustomerAccount(customer);
        InternationalAccount.setTypeOfAccount(TypeOfAccount.International);
        InternationalAccount.setTransactionnumber(0);
        InternationalAccount.setAmount(100);
        InternationalAccount.setNumberOfAccount("1200001003");
        ibanClass=new IbanClass();
        ibanClass.setIBAN("0123456789");
        ibanClass.setAccountNumber(InternationalAccount.getNumberOfAccount());


        customerUtil.addInterestRate(SavingAccount);

        transferUtilMock=Mockito.spy(transferUtil);

    }
    @Test
    public void createTransactionIfNotValid1(){



       Transaction result=transferUtil.createTransaction(InternationalAccount,InternationalAccount, TypeOfTransfer.WIRE,100.0);
        assertEquals(result,null);

    }
    @Test
    public void createTransactionIfNotValid2(){



        Transaction result=transferUtil.createTransaction(InternationalAccount,SavingAccount, TypeOfTransfer.WIRE,100.0);
        assertEquals(result,null);

    }
    @Test
    public void createTransactionIfWire(){



        Transaction transaction=transferUtil.createTransaction(InternationalAccount,RegularAccount, TypeOfTransfer.WIRE,100.0);
        String result=transaction.getTransactionNumber();
        assertEquals(result,customer.getAmountOfInternationalTransfer()+"");

    }
    @Test
    public void createTransactionIfDebit(){



        Transaction transaction=transferUtil.createTransaction(InternationalAccount,RegularAccount, TypeOfTransfer.WIRE,100.0);
        String result=transaction.getTransactionNumber();
        System.out.println("result:"+result);
        assertEquals(result,InternationalAccount.getTransactionnumber()+"");

    }
    @Test
    public void saveDebitTransferIfDebit(){

        Transaction transaction=new Transaction();
        transaction.setTypeOfTransfer(TypeOfTransfer.DEBIT);
        Transaction result=transferUtil.saveDebitTransfer(RegularAccount,transaction);

        assertEquals(result,transaction);


    }
    @Test
    public void saveDebitTransferIfNotDebit(){

        Transaction transaction=new Transaction();
        transaction.setTypeOfTransfer(TypeOfTransfer.WIRE);
        Transaction result=transferUtil.saveDebitTransfer(RegularAccount,transaction);

        assertEquals(result,null);


    }

    @Test
    public void getTransactionNumberForWireTransfer(){



        int result=transferUtil.getTransactionNumber(InternationalAccount);
        assertEquals(result,InternationalAccount.getCustomerAccount().getAmountOfInternationalTransfer());


    }
    @Test
    public void getTransactionNumberIfCreditTransfer(){


        int result=transferUtil.getTransactionNumber(RegularAccount);
        assertEquals(result,RegularAccount.getTransactionnumber());


    }
    @Test
    public void debitTransferIfAccountRegular(){


        boolean result=transferUtil.debitTransfer(RegularAccount,100.0);
        assertEquals(result,true);


    }
    @Test
    public void debitTransferIfSaving(){


        boolean result=transferUtil.debitTransfer(SavingAccount,100.0);
        assertEquals(result,false);


    }
    @Test
    public void debitTransferIfInternational(){

        boolean result=transferUtil.debitTransfer(InternationalAccount,100.0);
        assertEquals(result,false);


    }
    @Test
    public void doneTransferForWire(){
        Mockito.doReturn(true).when(transferUtilMock).debitTransfer(RegularAccount,1000.0);


        boolean result=transferUtilMock.doneTransfer(InternationalAccount,RegularAccount,1000.0,TypeOfTransfer.WIRE);
        assertEquals(result,true);
          Mockito.verify(transferUtilMock,Mockito.atLeastOnce()).debitTransfer(RegularAccount,1000.0);

    }
    @Test
    public void doneTransferNotCorrectForWire(){
        Mockito.doReturn(false).when(transferUtilMock).debitTransfer(SavingAccount,1000.0);


        boolean result=transferUtilMock.doneTransfer(InternationalAccount,SavingAccount,1000.0,TypeOfTransfer.WIRE);
        assertEquals(result,false);
        Mockito.verify(transferUtilMock,Mockito.atLeastOnce()).debitTransfer(SavingAccount,1000.0);


    }
    @Test
    public void doneTransferForWireTransfer(){
        Mockito.doReturn(false).when(transferUtilMock).debitTransfer(InternationalAccount,1000.0);
       boolean result=transferUtilMock.doneTransfer(InternationalAccount,InternationalAccount,1000.0,TypeOfTransfer.WIRE);
        assertEquals(result,false);
        Mockito.verify(transferUtilMock,Mockito.atLeastOnce()).debitTransfer(InternationalAccount,1000.0);


    }
    @Test
    public void checkAmountOfBalance(){
        Mockito.doReturn(true).when(transferUtilMock).doneTransfer(InternationalAccount,RegularAccount,100.0,TypeOfTransfer.WIRE);
        double amount=InternationalAccount.getAmount();
        boolean result=transferUtilMock.checkAmountOfBalance(InternationalAccount,RegularAccount,amount,TypeOfTransfer.WIRE);
        assertEquals(result,true);
        Mockito.verify(transferUtilMock,Mockito.atLeastOnce()).doneTransfer(InternationalAccount,RegularAccount,100.0,TypeOfTransfer.WIRE);
    }
    @Test
    public void checkAmountOfBalanceIfTransferAmountLess(){


        double amount=InternationalAccount.getAmount()-0.01;
        Mockito.doReturn(true).when(transferUtilMock).doneTransfer(InternationalAccount,RegularAccount,amount,TypeOfTransfer.WIRE);

        boolean result=transferUtilMock.checkAmountOfBalance(InternationalAccount,RegularAccount,amount,TypeOfTransfer.WIRE);
        assertEquals(result,true);
        Mockito.verify(transferUtilMock,Mockito.atLeastOnce()).doneTransfer(InternationalAccount,RegularAccount,amount,TypeOfTransfer.WIRE);
    }
    @Test
    public void checkAmountOfBalanceTransferAmountMore(){

        Mockito.doReturn(true).when(transferUtilMock).doneTransfer(InternationalAccount,RegularAccount,100.0,TypeOfTransfer.WIRE);
        double amount=InternationalAccount.getAmount()+0.001;
        boolean result=transferUtilMock.checkAmountOfBalance(InternationalAccount,RegularAccount,amount,TypeOfTransfer.WIRE);
        assertEquals(result,false);

    }
    @Test
    public void checkAmountOfBalanceTransferIfReciverAccountFalse(){

        Mockito.doReturn(false).when(transferUtilMock).doneTransfer(InternationalAccount,SavingAccount,100.0,TypeOfTransfer.WIRE);
        double amount=InternationalAccount.getAmount();
        boolean result=transferUtilMock.checkAmountOfBalance(InternationalAccount,SavingAccount,amount,TypeOfTransfer.WIRE);
        assertEquals(result,false);
        Mockito.verify(transferUtilMock,Mockito.atLeastOnce()).doneTransfer(InternationalAccount,SavingAccount,100.0,TypeOfTransfer.WIRE);
    }
    @Test
    public void SavingCreditTrasnferIfCorrect(){
       Mockito.doReturn(true).when(transferUtilMock).checkAmountOfBalance(SavingAccount,RegularAccount,100.0,TypeOfTransfer.CREDIT);

        boolean result=transferUtilMock.savingCreditTrasnfer(SavingAccount,RegularAccount,100.0);
        assertEquals(result,true);
        Mockito.verify(transferUtilMock,Mockito.atLeastOnce()).checkAmountOfBalance(SavingAccount,RegularAccount,100.0,TypeOfTransfer.CREDIT);
    }
    @Test
    public void SavingCreditTrasnferIfAccountSaving(){
        Mockito.doReturn(false).when(transferUtilMock).checkAmountOfBalance(SavingAccount,SavingAccount,100.0,TypeOfTransfer.CREDIT);

        boolean result=transferUtilMock.savingCreditTrasnfer(SavingAccount,SavingAccount,100);
        assertEquals(result,false);
        Mockito.verify(transferUtilMock,Mockito.never()).checkAmountOfBalance(SavingAccount,SavingAccount,100.0,TypeOfTransfer.CREDIT);
    }
    @Test
    public void SavingCreditTrasnferifInternationalAccount(){
        Mockito.doReturn(false).when(transferUtilMock).checkAmountOfBalance(InternationalAccount,InternationalAccount,100.0,TypeOfTransfer.CREDIT);

        boolean result=transferUtilMock.savingCreditTrasnfer(InternationalAccount,InternationalAccount,100);
        assertEquals(result,false);
        Mockito.verify(transferUtilMock,Mockito.never()).checkAmountOfBalance(InternationalAccount,InternationalAccount,100.0,TypeOfTransfer.CREDIT);
    }


    @Test
    public void RegularCreditTrasnfer(){
        Mockito.doReturn(true).when(transferUtilMock).checkAmountOfBalance(RegularAccount,RegularAccount,100.0,TypeOfTransfer.CREDIT);

        boolean result=transferUtilMock.regularCreditTrasnfer(RegularAccount,RegularAccount,100.0);
        assertEquals(result,true);
        Mockito.verify(transferUtilMock,Mockito.atLeastOnce()).checkAmountOfBalance(RegularAccount,RegularAccount,100.0,TypeOfTransfer.CREDIT);
    }


    @Test
    public void CreditTrasnferIfReciverAccountInternational(){
        Mockito.doReturn(false).when(transferUtilMock).regularCreditTrasnfer(RegularAccount,InternationalAccount,100.0);
        boolean result=transferUtilMock.creditTrasnfer(RegularAccount,InternationalAccount,100);
        assertEquals(result,false);
        Mockito.verify(transferUtilMock,Mockito.atLeastOnce()).regularCreditTrasnfer(RegularAccount,InternationalAccount,100.0);
        Mockito.verify(transferUtilMock,Mockito.never()).savingCreditTrasnfer(RegularAccount,InternationalAccount,100.0);
    }
    @Test
    public void CreditTrasnferIfSenderAccountInternational(){
        Mockito.doReturn(false).when(transferUtilMock).regularCreditTrasnfer(InternationalAccount,InternationalAccount,100.0);
        boolean result=transferUtilMock.creditTrasnfer(InternationalAccount,InternationalAccount,100);
        assertEquals(result,false);
        Mockito.verify(transferUtilMock,Mockito.never()).regularCreditTrasnfer(InternationalAccount,InternationalAccount,100.0);
        Mockito.verify(transferUtilMock,Mockito.never()).savingCreditTrasnfer(InternationalAccount,InternationalAccount,100.0);
    }
    @Test
    public void CreditTrasnferIfSavingTransfer(){
        Mockito.doReturn(true).when(transferUtilMock).savingCreditTrasnfer(SavingAccount,RegularAccount,100.0);
        boolean result=transferUtilMock.savingCreditTrasnfer(SavingAccount,RegularAccount,100);
        assertEquals(result,true);
        Mockito.verify(transferUtilMock,Mockito.atLeastOnce()).savingCreditTrasnfer(SavingAccount,RegularAccount,100.0);
        Mockito.verify(transferUtilMock,Mockito.never()).regularCreditTrasnfer(SavingAccount,RegularAccount,100.0);
    }
    @Test
    public void CreditTrasnferIfRegulerCreditTransfer(){
        Mockito.doReturn(true).when(transferUtilMock).regularCreditTrasnfer(RegularAccount,RegularAccount,100.0);
        boolean result=transferUtilMock.creditTrasnfer(RegularAccount,RegularAccount,100);
        assertEquals(result,true);
        Mockito.verify(transferUtilMock,Mockito.atLeastOnce()).regularCreditTrasnfer(RegularAccount,RegularAccount,100.0);
        Mockito.verify(transferUtilMock,Mockito.never()).savingCreditTrasnfer(RegularAccount,RegularAccount,100.0);

    }
    @Test
    public void WireTrasnferIfCorrect(){

        Mockito.doReturn(true).when(transferUtilMock).checkAmountOfBalance(InternationalAccount,RegularAccount,100.0,TypeOfTransfer.WIRE);
        boolean result=transferUtilMock.wireTransfer(InternationalAccount,RegularAccount,100);
        assertEquals(result,true);
        Mockito.verify(transferUtilMock,Mockito.atLeastOnce()).checkAmountOfBalance(InternationalAccount,RegularAccount,100.0,TypeOfTransfer.WIRE);

    }
    @Test
    public void WireTrasnferIfNotCorroect(){
        Mockito.doReturn(false).when(transferUtilMock).checkAmountOfBalance(RegularAccount,RegularAccount,100.0,TypeOfTransfer.WIRE);
        boolean result=transferUtilMock.wireTransfer(RegularAccount,RegularAccount,100);
        assertEquals(result,false);
        Mockito.verify(transferUtilMock,Mockito.never()).checkAmountOfBalance(RegularAccount,RegularAccount,100.0,TypeOfTransfer.WIRE);

    }
    @Test
    public void WireTrasnferIfNotCorrect2(){
        Mockito.doReturn(false).when(transferUtilMock).checkAmountOfBalance(SavingAccount,RegularAccount,100.0,TypeOfTransfer.WIRE);
        boolean result=transferUtilMock.wireTransfer(SavingAccount,RegularAccount,100);
        assertEquals(result,false);
        Mockito.verify(transferUtilMock,Mockito.never()).checkAmountOfBalance(SavingAccount,RegularAccount,100.0,TypeOfTransfer.WIRE);

    }
    @Test
    public void doTransferIfCreditTransferCorrect(){
        Mockito.doReturn(true).when(transferUtilMock).creditTrasnfer(SavingAccount,RegularAccount,100);
        boolean result=transferUtilMock.doTransfer(SavingAccount,RegularAccount,100,TypeOfTransfer.CREDIT);
        assertEquals(result,true);
        Mockito.verify(transferUtilMock,Mockito.atLeastOnce()).creditTrasnfer(SavingAccount,RegularAccount,100);
        Mockito.verify(transferUtilMock,Mockito.never()).wireTransfer(SavingAccount,RegularAccount,100);
    }
    @Test
    public void doTransferIfWire(){
        Mockito.doReturn(true).when(transferUtilMock).creditTrasnfer(InternationalAccount,RegularAccount,100);
        boolean result=transferUtilMock.doTransfer(InternationalAccount,RegularAccount,100,TypeOfTransfer.WIRE);
        assertEquals(result,true);
        Mockito.verify(transferUtilMock,Mockito.never()).creditTrasnfer(InternationalAccount,RegularAccount,100);
        Mockito.verify(transferUtilMock,Mockito.atLeastOnce()).wireTransfer(InternationalAccount,RegularAccount,100);
    }
    @Test
    public void doTransferIfCreditTransferNotCorrect(){
        Mockito.doReturn(false).when(transferUtilMock).creditTrasnfer(InternationalAccount,RegularAccount,100);
        boolean result=transferUtilMock.doTransfer(InternationalAccount,RegularAccount,100,TypeOfTransfer.CREDIT);
        assertEquals(result,false);
        Mockito.verify(transferUtilMock,Mockito.atLeastOnce()).creditTrasnfer(InternationalAccount,RegularAccount,100);
        Mockito.verify(transferUtilMock,Mockito.never()).wireTransfer(InternationalAccount,RegularAccount,100);
    }
    @Test
    public void doTransferIFWireTransferNotCorrct(){
        Mockito.doReturn(false).when(transferUtilMock).creditTrasnfer(RegularAccount,RegularAccount,100);
        boolean result=transferUtilMock.doTransfer(RegularAccount,RegularAccount,100,TypeOfTransfer.WIRE);
        assertEquals(result,false);
        Mockito.verify(transferUtilMock,Mockito.never()).creditTrasnfer(RegularAccount,RegularAccount,100);
        Mockito.verify(transferUtilMock,Mockito.atLeastOnce()).wireTransfer(RegularAccount,RegularAccount,100);

    }
    @Test
    public void doTransferDebitTransfer(){
        Mockito.doReturn(false).when(transferUtilMock).creditTrasnfer(RegularAccount,RegularAccount,100);
        boolean result=transferUtilMock.doTransfer(RegularAccount,RegularAccount,100,TypeOfTransfer.DEBIT);
        assertEquals(result,false);
        Mockito.verify(transferUtilMock,Mockito.never()).creditTrasnfer(RegularAccount,RegularAccount,100);
        Mockito.verify(transferUtilMock,Mockito.never()).wireTransfer(RegularAccount,RegularAccount,100);

    }

}
