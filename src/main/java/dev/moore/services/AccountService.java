package dev.moore.services;

import dev.moore.entities.Constituent;

import java.util.List;

public interface AccountService {
    boolean registerAccount(String username);
    List<Constituent> getAllAccounts();
}
