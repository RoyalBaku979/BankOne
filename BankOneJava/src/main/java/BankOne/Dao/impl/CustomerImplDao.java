package BankOne.Dao.impl;

import BankOne.Config.Config;
import BankOne.beans.Customer;
import BankOne.Dao.inter.CustomerInterDao;

import java.util.Set;
import java.util.TreeSet;

public class CustomerImplDao implements CustomerInterDao {
    Config config;
    @Override
    public Set<Customer> getAll() {

        return config.getListofCustomer1();
    }
    @Override
    public Set<Customer> getFiveNewClient(){
     Set<Customer> setOfFiveNewCustomer=new TreeSet<>();
     int i=0;
        for (Customer customer:getAll()) {
            if(i==5) break;
            i++;
            setOfFiveNewCustomer.add(customer);

        }
      return setOfFiveNewCustomer;
    }


}
