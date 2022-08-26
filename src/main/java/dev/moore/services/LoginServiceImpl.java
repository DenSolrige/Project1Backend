package dev.moore.services;

import dev.moore.daos.ConstituentDAO;
import dev.moore.entities.Constituent;
import dev.moore.exceptions.NoAccountFoundException;
import dev.moore.exceptions.PasswordMismatchException;
import dev.moore.exceptions.UsernameAlreadyTakenException;

import java.util.List;

public class LoginServiceImpl implements LoginService{

    private final ConstituentDAO constituentDAO;

    public LoginServiceImpl(ConstituentDAO constituentDAO) {
        this.constituentDAO = constituentDAO;
    }

    @Override
    public Constituent createAccount(Constituent constituent) {
        List<Constituent> constituentList = this.constituentDAO.getAllAccounts();
        for(Constituent c : constituentList) {
            System.out.println(c.getUsername());
            if (c.getUsername().equals(constituent.getUsername())) {
                throw new UsernameAlreadyTakenException("Username already taken");
            }
        }
        return constituentDAO.createAccount(constituent);
    }

    @Override
    public Constituent validateAccount(String username, String password) {
        Constituent constituent = this.constituentDAO.getAccountByUsername(username);
        if(constituent == null){
            throw new NoAccountFoundException("No account found with the username "+username);
        }else if(!constituent.getPassword().equals(password)){
            throw new PasswordMismatchException("Password does not match");
        }else{
            return constituent;
        }
    }
}
