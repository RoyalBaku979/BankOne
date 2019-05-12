package uni.lodz.pl.java.Dao.inter;

import uni.lodz.pl.java.beans.Account;
import uni.lodz.pl.java.beans.Customer;
import uni.lodz.pl.java.beans.IbanClass;


import java.util.List;

public interface IbanDaoInter {
    public List<IbanClass> getAll();
    public IbanClass getIbanByAccount(Account account);
    public boolean update(IbanClass iban);
    public boolean add(IbanClass iban );
    public boolean remove(IbanClass iban);
}
