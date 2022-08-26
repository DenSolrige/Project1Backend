package dev.moore.daotests;

import dev.moore.daos.ConstituentDAO;
import dev.moore.daos.ConstituentDaoPostgres;
import dev.moore.entities.Constituent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ConstituentDaoTests {

    static ConstituentDAO constituentDAO = new ConstituentDaoPostgres();

    @Test
    void get_all_accounts_test(){
        List<Constituent> constituentList = constituentDAO.getAllAccounts();
        System.out.println(constituentList);
        Assertions.assertNotEquals(0, constituentList.size());
    }

    @Test
    void create_account_test(){
        List<Constituent> constituentList = constituentDAO.getAllAccounts();
        String username = "username"+constituentList.size();
        Constituent constituent = new Constituent("Test","Constituent",username,"password",false,false);
        Constituent savedConstituent = constituentDAO.createAccount(constituent);
        Assertions.assertNotEquals(0,savedConstituent.getConstituentId());
    }

    @Test
    void register_account_test(){
        List<Constituent> constituentList = constituentDAO.getAllAccounts();
        Constituent constituent = constituentList.get(constituentList.size()-1);
        boolean result = constituentDAO.registerAccount(constituent.getUsername());
        Constituent registeredConstituent = constituentDAO.getAccountByUsername(constituent.getUsername());
        Assertions.assertEquals(true, result);
        Assertions.assertEquals(true,registeredConstituent.isRegistered());
    }
}
