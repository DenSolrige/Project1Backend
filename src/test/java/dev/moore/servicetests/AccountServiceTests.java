package dev.moore.servicetests;

import dev.moore.daos.ConstituentDaoPostgres;
import dev.moore.services.AccountService;
import dev.moore.services.AccountServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class AccountServiceTests {

    @Test
    void username_must_exist_test(){
        ConstituentDaoPostgres constituentDaoPostgres = Mockito.mock(ConstituentDaoPostgres.class);
        Mockito.when(constituentDaoPostgres.getAccountByUsername("nonexistant account")).thenReturn(null);
        AccountService accountService = new AccountServiceImpl(constituentDaoPostgres);

        Assertions.assertThrows(RuntimeException.class,() -> {
            accountService.registerAccount("nonexistant account");
        });
    }

}
