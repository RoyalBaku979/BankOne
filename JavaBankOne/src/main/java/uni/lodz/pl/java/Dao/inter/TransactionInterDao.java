package uni.lodz.pl.java.Dao.inter;

import uni.lodz.pl.java.beans.*;

import java.time.ZonedDateTime;
import java.util.List;

public interface TransactionInterDao {
    public List<Transaction> getAll();
    public List<Transaction> getAllByCustomer( Customer Customer);
    public List<Transaction> getAllByAccount( Account account);
    public List<Transaction> getAllByType (TypeOfTransfer typeOfTransfer);
    public List<Transaction> getAllByHistory (ZonedDateTime startDate, ZonedDateTime endDate);
    public List<Transaction> getAllByDay (ZonedDateTime day);
    public List<Transaction> getAllByDay (ZonedDateTime day,Customer customer);
    public List<Transaction> getAllByHistory(ZonedDateTime startDate, ZonedDateTime endDate,Customer customer);
    public boolean update(Transaction transaction);
    public boolean add(Transaction transaction);
    public boolean remove(Transaction transaction);
}
