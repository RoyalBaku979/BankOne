package Shahin_Aliyev.Dao.inter;

import Shahin_Aliyev.beans.*;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

public interface TransactionInterDao {
    public Set<Transaction> getAll();
    public Set<Transaction> getAllByCustomer(Customer Customer);
    public Set<Transaction> getAllByAccount(Account account);
    public Set<Transaction> getAllByType(TypeOfTransfer typeOfTransfer);
    public Set<Transaction> getAllByHistory(ZonedDateTime startDate, ZonedDateTime endDate);
    public Set<Transaction> getAllByDay(ZonedDateTime day);
    public Set<Transaction> getFiveHigestTransactionByAmount(double amount);
    public Set<Transaction> getAllByDay(ZonedDateTime day, Customer customer);
    public Set<Transaction> getAllByHistory(ZonedDateTime startDate, ZonedDateTime endDate, Customer customer);

}
