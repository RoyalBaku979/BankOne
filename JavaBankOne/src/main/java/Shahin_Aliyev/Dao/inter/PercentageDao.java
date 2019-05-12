package Shahin_Aliyev.Dao.inter;

import Shahin_Aliyev.beans.Account;
import Shahin_Aliyev.beans.Percentage;

import java.util.List;

public interface PercentageDao {
    public List<Percentage>getAll();
    public Percentage getInterestByAccount(Account account);
    public boolean update(Percentage percentage);
    public boolean add(Percentage percentage);
    public boolean remove(Percentage percentage);
}
