package BankOne.Dao.inter;

import BankOne.beans.Account;
import BankOne.beans.Percentage;

import java.util.List;

public  interface PercentageDao {
    public List<Percentage>getAll();
    public  Percentage getInterestByAccount(Account account);


}
