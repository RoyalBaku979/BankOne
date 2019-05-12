package Shahin_Aliyev.Dao.inter;

import Shahin_Aliyev.beans.Account;
import Shahin_Aliyev.beans.Customer;
import Shahin_Aliyev.beans.TypeOfAccount;

import java.util.List;

public interface AccountInterDao {
    public  List<Account> getAll();
    public  List<Account> getAllByCustomer(Customer customer);
    public List<Account> getAllByType(TypeOfAccount typeOfAccount);
    public boolean update(Account account);
    public boolean add(Account account);
    public boolean remove(Account account);
}
