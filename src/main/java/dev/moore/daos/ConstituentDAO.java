package dev.moore.daos;

import dev.moore.entities.Constituent;

import java.util.List;

public interface ConstituentDAO {

    Constituent createAccount(Constituent constituent);
    List<Constituent> getAllAccounts();
}
