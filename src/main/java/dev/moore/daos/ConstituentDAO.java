package dev.moore.daos;

import dev.moore.entities.Constituent;

import java.util.List;

public interface ConstituentDAO {

    Constituent createAccount(Constituent constituent);
    Constituent getAccountByUsername(String username);
    List<Constituent> getAllAccounts();
}
