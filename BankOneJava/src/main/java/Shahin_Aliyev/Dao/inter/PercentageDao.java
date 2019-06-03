package Shahin_Aliyev.Dao.inter;

import Shahin_Aliyev.beans.Account;
import Shahin_Aliyev.beans.Percentage;

import java.util.List;

public  interface PercentageDao {
    public List<Percentage>getAll();
    public  Double getInterestByAccount(Account account);

}
