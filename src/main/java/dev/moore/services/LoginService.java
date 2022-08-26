package dev.moore.services;

import dev.moore.entities.Constituent;

public interface LoginService {
    Constituent createAccount(Constituent constituent);
    Constituent validateAccount(String username,String password);
}
