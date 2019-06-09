package BankOne.Dao.inter;

import BankOne.beans.Account;
import BankOne.beans.IbanClass;

import java.util.List;

public interface IbanInterDao {
    public List<IbanClass> getAll();
    public IbanClass getIbanByAccount(Account account);

}
