package Shahin_Aliyev.Dao.inter;

import Shahin_Aliyev.beans.Customer;

import java.util.List;
import java.util.Set;

public interface CustomerInterDao {
    public  Set<Customer> getAll();
    public Set<Customer> getFiveNewClient();

    public boolean update(Customer customer);
    public boolean add(Customer customer);
    public boolean remove(Customer customer);
}
