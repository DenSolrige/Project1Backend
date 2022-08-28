package dev.moore.daos;

import dev.moore.entities.Complaint;
import dev.moore.entities.ComplaintStatus;
import dev.moore.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Complaint> getAllComplaints() {
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "select * from complaint";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Complaint> complaintList = new ArrayList<>();
            while(resultSet.next()){
                Complaint complaint = new Complaint();
                complaint.setComplaintId(resultSet.getInt("complaint_id"));
                complaint.setDescription(resultSet.getString("description"));
                complaint.setComplaintStatus(ComplaintStatus.valueOf(resultSet.getString("status")));
                complaint.setMeetingId(resultSet.getInt("meeting_id"));
                complaintList.add(complaint);
            }

            return complaintList;

        }catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Complaint updateComplaint(Complaint complaint) {
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "update complaint set status = ?, meeting_id = ? where complaint_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,complaint.getComplaintStatus().toString());
            preparedStatement.setInt(2,complaint.getMeetingId());
            preparedStatement.setInt(3,complaint.getComplaintId());

            preparedStatement.executeUpdate();

            return complaint;

        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
