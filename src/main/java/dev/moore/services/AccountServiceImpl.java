package dev.moore.services;

import dev.moore.daos.ConstituentDAO;
import dev.moore.entities.Constituent;
import dev.moore.exceptions.NoAccountFoundException;
import dev.moore.exceptions.PasswordMismatchException;

import java.util.List;

public class AccountServiceImpl implements AccountService{

    private final ConstituentDAO constituentDAO;

    public AccountServiceImpl(ConstituentDAO constituentDAO) {
        this.constituentDAO = constituentDAO;
    }

    @Override
    public boolean registerAccount(String username) {
        Constituent constituent = this.constituentDAO.getAccountByUsername(username);
        if(constituent == null){
            throw new NoAccountFoundException("No account found with the username "+username);
        }else{
            return this.constituentDAO.registerAccount(username);
        }
    }

    @Override
    public List<Constituent> getAllAccounts() {
        return this.constituentDAO.getAllAccounts();
    }
}
