package Shahin_Aliyev.Dao.inter;

import Shahin_Aliyev.beans.Customer;

import java.util.List;

public interface CustomerInterDao {
    public  List<Customer> getAll();

    public boolean update(Customer customer);
    public boolean add(Customer customer);
    public boolean remove(Customer customer);
}
