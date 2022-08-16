package dev.moore.daos;

import dev.moore.entities.Complaint;
import dev.moore.utils.ConnectionUtil;

import java.sql.*;

public class ComplaintDaoPostgres implements ComplaintDAO{

    @Override
    public Complaint createComplaint(Complaint complaint) {
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "insert into complaint values (default,?,default,default)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, complaint.getDescription());

            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            int generatedKey = resultSet.getInt("complaint_id");
            complaint.setComplaintId(generatedKey);
            return complaint;
        }catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
