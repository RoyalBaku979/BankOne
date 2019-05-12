package uni.lodz.pl.java.Dao.inter;

import uni.lodz.pl.java.beans.Account;
import uni.lodz.pl.java.beans.Percentage;

import java.util.List;

public interface PercentageDao {
    public List<Percentage>getAll();
    public Percentage getInterestByAccount(Account account);
    public boolean update(Percentage percentage);
    public boolean add(Percentage percentage);
    public boolean remove(Percentage percentage);
}
