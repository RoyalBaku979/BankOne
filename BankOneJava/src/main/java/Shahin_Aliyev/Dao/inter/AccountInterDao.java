package Shahin_Aliyev.Dao.inter;

import Shahin_Aliyev.beans.Account;
import Shahin_Aliyev.beans.Customer;
import Shahin_Aliyev.beans.TypeOfAccount;

import java.util.List;
import java.util.Set;

public interface AccountInterDao {
    public List<Account> getAll();
    public  List<Account> getAllByCustomer(Customer customer);
    public List<Account> getAllByType(TypeOfAccount typeOfAccount);

}
