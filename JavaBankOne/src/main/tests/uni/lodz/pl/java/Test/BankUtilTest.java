package uni.lodz.pl.java.Test;

import org.junit.Test;
import uni.lodz.pl.java.Util.BankUtil;

import static org.junit.Assert.*;

public class BankUtilTest {

    @Test
    public void createCustomer() {
    }

    @Test
    public void genarateCustomerNumber() {
        BankUtil bankUtil=new BankUtil();
        String result=bankUtil.genarateCustomerNumber();
        assertEquals(result,"00000");
    }
}