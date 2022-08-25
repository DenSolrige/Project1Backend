package dev.moore.daos;

import dev.moore.entities.Constituent;
import dev.moore.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConstituentDaoPostgres implements ConstituentDAO{

    @Override
    public Constituent createAccount(Constituent constituent) {
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "insert into app_user values (default, ?, ?, ?, ?, default, default)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,constituent.getFname());
            preparedStatement.setString(2,constituent.getLname());
            preparedStatement.setString(3,constituent.getUsername());
            preparedStatement.setString(4,constituent.getPassword());
//            preparedStatement.setBoolean(5,constituent.isCouncilMember());
//            preparedStatement.setBoolean(6,constituent.isRegistered());

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            int generatedKey = resultSet.getInt("app_user_id");
            constituent.setConstituentId(generatedKey);
            return constituent;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Constituent> getAllAccounts() {
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "select * from app_user";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Constituent> constituentList = new ArrayList<>();
            while(resultSet.next()){
                Constituent constituent = new Constituent();
                constituent.setConstituentId(resultSet.getInt("app_user_id"));
                constituent.setFname(resultSet.getString("fname"));
                constituent.setLname(resultSet.getString("lname"));
                constituent.setUsername(resultSet.getString("username"));
                constituent.setPassword(resultSet.getString("password"));
                constituent.setCouncilMember(resultSet.getBoolean("is_council_member"));
                constituent.setRegistered(resultSet.getBoolean("is_registered"));
                constituentList.add(constituent);
            }
            return constituentList;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
