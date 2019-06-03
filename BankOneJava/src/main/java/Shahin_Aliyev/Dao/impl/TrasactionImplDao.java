package Shahin_Aliyev.Dao.impl;

import Shahin_Aliyev.Config.Config;
import Shahin_Aliyev.Dao.inter.TransactionInterDao;
import Shahin_Aliyev.beans.Account;
import Shahin_Aliyev.beans.Customer;
import Shahin_Aliyev.beans.Transaction;
import Shahin_Aliyev.beans.TypeOfTransfer;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class TrasactionImplDao implements TransactionInterDao {
    Config config;
    @Override
    public Set<Transaction> getAll() {
        return config.getSetsOfTransactions();
    }

    @Override
    public Set<Transaction> getAllByCustomer(Customer customer) {
       Set<Transaction> SetOfTransaction=config.getSetsOfTransactions();
        Set<Transaction>listOfTransactionByCustomer=new TreeSet<>();

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
    public Set<Transaction> getAllByAccount(Account account) {
        Set<Transaction> listOfTransaction=getAll();
        Set<Transaction> listOfTransactionByAccount = new TreeSet<>();
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
    public Set<Transaction> getAllByType(TypeOfTransfer typeOfTransfer) {
        Set<Transaction> listOfTransaction = new TreeSet<>();
        for (Transaction tr : config.getSetsOfTransactions()) {
            if (tr.getTypeOfTransfer()==typeOfTransfer) {
                listOfTransaction.add(tr);
            }

        }
        return listOfTransaction;

    }

    @Override
    public Set<Transaction> getAllByHistory(ZonedDateTime startDate, ZonedDateTime endDate) {

        Set<Transaction> listOfTransaction = new TreeSet<>();
        for (Transaction transaction :config.getSetsOfTransactions()) {
            if(transaction.getDateOfTransaction().isAfter(startDate) && transaction.getDateOfTransaction().isBefore(endDate))
            {
                listOfTransaction.add(transaction);
            }

        }
        return listOfTransaction;
    }
    @Override
    public Set<Transaction> getAllByHistory(ZonedDateTime startDate, ZonedDateTime endDate,Customer customer) {

        Set<Transaction> listOfTransaction = new TreeSet<>();
        for (Transaction transaction :getAllByCustomer(customer)) {
            if(transaction.getDateOfTransaction().isAfter(startDate) && transaction.getDateOfTransaction().isBefore(endDate))
            {
                listOfTransaction.add(transaction);
            }

        }
        return listOfTransaction;
    }

    @Override
    public Set<Transaction> getAllByDay(ZonedDateTime day) {
        Set<Transaction> listOfTransaction = new TreeSet<>();
        for (Transaction transaction :config.getSetsOfTransactions()) {
            if(transaction.getDateOfTransaction().equals(day))
            {
                listOfTransaction.add(transaction);
            }

        }
        return listOfTransaction;
    }

    @Override
    public Set<Transaction> getFiveHigestTransactionByAmount(double amount) {
        int i=0;
        List<Transaction> listOfTransaction = new ArrayList<>();
        for (Transaction transaction :config.getSetsOfTransactions()) {

            { if(i==5) break;
               i++;
                listOfTransaction.add(transaction);
            }

        }
        return null;
    }

    @Override
    public Set<Transaction> getAllByDay(ZonedDateTime day,Customer customer) {
        Set<Transaction> listOfTransaction = new TreeSet<>();
        for (Transaction transaction :getAllByCustomer(customer)) {
            if(transaction.getDateOfTransaction().equals(day))
            {
                listOfTransaction.add(transaction);
            }

        }
        return listOfTransaction;
    }


}
