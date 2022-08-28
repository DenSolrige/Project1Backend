package dev.moore.servicetests;

import dev.moore.daos.ConstituentDaoPostgres;
import dev.moore.entities.Constituent;
import dev.moore.services.LoginService;
import dev.moore.services.LoginServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

class LoginServiceTests {

    @Test
    void username_must_be_unique_test(){
        ConstituentDaoPostgres constituentDaoPostgres = Mockito.mock(ConstituentDaoPostgres.class);
        Constituent constituent = new Constituent("Test","Test","Test","Test",false,false);
        List<Constituent> constituentList = new ArrayList<>();
        constituentList.add(constituent);
        Mockito.when(constituentDaoPostgres.getAllAccounts()).thenReturn(constituentList);
        LoginService loginService = new LoginServiceImpl(constituentDaoPostgres);

        Assertions.assertThrows(RuntimeException.class,() -> {
           loginService.createAccount(constituent);
        });
    }

    @Test
    void username_must_exist_test(){
        ConstituentDaoPostgres constituentDaoPostgres = Mockito.mock(ConstituentDaoPostgres.class);
        Mockito.when(constituentDaoPostgres.getAccountByUsername("nonexistant account")).thenReturn(null);
        LoginService loginService = new LoginServiceImpl(constituentDaoPostgres);

        Assertions.assertThrows(RuntimeException.class,() -> {
            loginService.validateAccount("nonexistant account","password");
        });
    }

    @Test
    void password_must_match_test(){
        ConstituentDaoPostgres constituentDaoPostgres = Mockito.mock(ConstituentDaoPostgres.class);
        Constituent constituent = new Constituent("Test","Test","Test","Test",false,false);
        Mockito.when(constituentDaoPostgres.getAccountByUsername("Test")).thenReturn(constituent);
        LoginService loginService = new LoginServiceImpl(constituentDaoPostgres);

        Assertions.assertThrows(RuntimeException.class,() -> {
            loginService.validateAccount("nonexistant account","password");
        });
    }
}
