package uni.lodz.pl.java.Util;

import uni.lodz.pl.java.Config.Config;
import uni.lodz.pl.java.beans.Customer;

import java.util.Date;
import java.util.List;

public class BankUtil {
    public  static List<Customer> CreateClinet(String name, String surname, Date dateOfBirht, Date dateOfJoinBank){
        Customer newCustomer=new Customer(name,surname,dateOfBirht,dateOfJoinBank);
        Config.AddListofCustomer(newCustomer);
        return Config.getListofCustomer();

    }
}