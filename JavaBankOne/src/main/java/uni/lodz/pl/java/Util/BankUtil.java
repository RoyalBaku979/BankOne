package uni.lodz.pl.java.Util;

import uni.lodz.pl.java.Config.Config;
import uni.lodz.pl.java.beans.Customer;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class BankUtil {
public boolean CreateCustomer(String name,String surname,String email,String password,String dateOfBirht,Date dateOfJoinBank,int amountOfInternationTransfer){
    Customer customer=Customer.getInstance();
    if(name.trim().isEmpty() ||surname.trim().isEmpty() || email.trim().isEmpty() || password.trim().isEmpty()
   || dateOfBirht.trim().isEmpty())
    {

        return false;
    }
    else {
        customer.setCustomerNumber(genarateCustomerNumber());
        customer.setDateOfBirth(dateOfBirht);
        customer.setDateOfJoinedBank(Calendar.getInstance().getTime());
        customer.setEmail(email);
        customer.setName(name);
        customer.setSurname(surname);
        customer.setPassword(password);
        customer.setAmountOfInternationalTransfer(0);
        Config.AddListofCustomer(customer);
        return true;
       }

}
public static String genarateCustomerNumber() {
   String number=Config.getListofCustomer().size()+"";
       int size= number.length();
       while(size<5)
       {
           number="0"+number;
           size= number.length();
       }

    return number;

}
   
}