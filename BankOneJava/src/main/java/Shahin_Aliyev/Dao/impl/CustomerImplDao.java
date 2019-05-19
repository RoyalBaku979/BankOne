package Shahin_Aliyev.Dao.impl;

import Shahin_Aliyev.Config.Config;
import Shahin_Aliyev.Dao.inter.CustomerInterDao;
import Shahin_Aliyev.beans.Customer;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class CustomerImplDao implements CustomerInterDao {
    @Override
    public Set<Customer> getAll() {
        return Config.getListofCustomer();
    }
    @Override
    public Set<Customer> getFiveNewClient(){
     Set<Customer> setOfFiveNewCustomer=new TreeSet<>();
     int i=0;
        for (Customer customer:Config.getListofCustomer()) {
            if(i==5) break;
            i++;
            setOfFiveNewCustomer.add(customer);

        }
      return setOfFiveNewCustomer;
    }

    @Override
    public boolean update(Customer customer) {
        return false;
    }

    @Override
    public boolean add(Customer customer) {
        return false;
    }

    @Override
    public boolean remove(Customer customer) {
        return false;
    }
}
