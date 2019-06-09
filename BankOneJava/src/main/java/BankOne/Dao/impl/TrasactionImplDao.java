package BankOne.Dao.impl;

import BankOne.Config.Config;
import BankOne.beans.Account;
import BankOne.beans.Customer;
import BankOne.Dao.inter.TransactionInterDao;
import BankOne.beans.Transaction;
import BankOne.beans.TypeOfTransfer;

import java.time.ZonedDateTime;
import java.util.*;

public class TrasactionImplDao implements TransactionInterDao {
    Config config;
    @Override
    public List<Transaction> getAll() {
        return config.getSetsOfTransactions();
    }

    @Override
    public List<Transaction> getAllByCustomer(Customer customer) {
       List<Transaction> SetOfTransaction=getAll();
        List<Transaction>listOfTransactionByCustomer=new ArrayList<>();

        for (Transaction tr : SetOfTransaction) {
            if (tr.getSenderAccount().getCustomerAccount()==customer &&
                    (tr.getTypeOfTransfer()==TypeOfTransfer.CREDIT || tr.getTypeOfTransfer()==TypeOfTransfer.WIRE)) {
                listOfTransactionByCustomer.add(tr);
            }
            else
            {
                if (tr.getRecivierAccount().getCustomerAccount()==customer&&
                        tr.getTypeOfTransfer() == TypeOfTransfer.DEBIT)
                {
                    listOfTransactionByCustomer.add(tr);
                }
            }

        }
        return listOfTransactionByCustomer;
    }

    @Override
    public List<Transaction> getAllByAccount(Account account) {
        List<Transaction> listOfTransaction=getAll();
        List<Transaction> listOfTransactionByAccount = new ArrayList<>();
        for (Transaction tr : listOfTransaction) {
            if (tr.getSenderAccount().getNumberOfAccount().equals(account.getNumberOfAccount()) &&
                    (tr.getTypeOfTransfer()==TypeOfTransfer.CREDIT || tr.getTypeOfTransfer()==TypeOfTransfer.WIRE)) {
                listOfTransactionByAccount.add(tr);
            }
            else
                {
                   if (tr.getRecivierAccount().getNumberOfAccount().equals(account.getNumberOfAccount()) &&
                        tr.getTypeOfTransfer() == TypeOfTransfer.DEBIT)
                   {
                    listOfTransactionByAccount.add(tr);
                   }
                }

        }

        return listOfTransaction;
    }

    @Override
    public List<Transaction> getAllByType(TypeOfTransfer typeOfTransfer) {
        List<Transaction> listOfTransaction = new ArrayList<>();
        for (Transaction tr : getAll()) {
            if (tr.getTypeOfTransfer()==typeOfTransfer) {
                listOfTransaction.add(tr);
            }

        }
        return listOfTransaction;

    }

    @Override
    public List<Transaction> getAllByHistory(ZonedDateTime startDate, ZonedDateTime endDate) {

        List<Transaction> listOfTransaction = new ArrayList<>();
        for (Transaction transaction :getAll()) {
            if(transaction.getDateOfTransaction().isAfter(startDate) && transaction.getDateOfTransaction().isBefore(endDate))
            {
                listOfTransaction.add(transaction);
            }

        }
        return listOfTransaction;
    }
    @Override
    public List<Transaction> getAllByHistory(ZonedDateTime startDate, ZonedDateTime endDate,Customer customer) {

        List<Transaction> listOfTransaction = new ArrayList<>();
        for (Transaction transaction :getAllByCustomer(customer)) {
            if(transaction.getDateOfTransaction().isAfter(startDate) && transaction.getDateOfTransaction().isBefore(endDate))
            {
                listOfTransaction.add(transaction);
            }

        }
        return listOfTransaction;
    }

    @Override
    public List<Transaction> getAllByDay(ZonedDateTime day) {
        List<Transaction> listOfTransaction = new ArrayList<>();
        for (Transaction transaction :getAll()){
            if(transaction.getDateOfTransaction().equals(day))
            {
                listOfTransaction.add(transaction);
            }

        }
        return listOfTransaction;
    }

    @Override
    public List<Transaction> getFiveHigestTransactionByAmount() {
        int i=0;
        List<Transaction> listOfTransaction = new ArrayList<>();
        List<Transaction> listOfTransactionByAmmount = getAll();
        Collections.sort(listOfTransactionByAmmount);

        for (Transaction transaction :getAll()) {

             if(i==5)
             {
                 break;
             }
             else {
                 i++;
                 listOfTransaction.add(transaction);

                    }
        }
        return listOfTransaction;
    }

    @Override
    public List<Transaction> getAllByDay(ZonedDateTime day,Customer customer) {
        List<Transaction> listOfTransaction = new ArrayList<>();
        for (Transaction transaction :getAllByCustomer(customer)) {
            if(transaction.getDateOfTransaction().equals(day))
            {
                listOfTransaction.add(transaction);
            }

        }
        return listOfTransaction;
    }


}
