package uni.lodz.pl.java.Util;

import uni.lodz.pl.java.Config.Config;
import uni.lodz.pl.java.beans.Account;

import java.util.List;

public class EmployerUtil {
    public static boolean AccecptNewAccount(Account account)
    {
     if(CheckAccount(account))
     {
         return true;
     }
     else
     {
         return false;

     }

    }
    public static boolean CheckAccount (Account account)
    {
        if(Config.getListOfAccount().size()==0)
        {
            return false;
        }

        for (Account a: Config.getListOfAccount()) {
            if(a.equals(account))
            {
                return false;

            }

        }
        return true;


    }

}
