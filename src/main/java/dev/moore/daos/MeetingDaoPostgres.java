package dev.moore.daos;

import dev.moore.entities.Meeting;
import dev.moore.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MeetingDaoPostgres implements MeetingDAO{

    @Override
    public List<Meeting> getAllMeetings() {
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "select * from meeting";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getResultSet();
            List<Meeting> meetingList = new ArrayList<>();
            while(resultSet.next()){
                Meeting meeting = new Meeting();
                meeting.setMeetingId(resultSet.getInt("meeting_id"));
                meeting.setDescription(resultSet.getString("description"));
                meeting.setAddress(resultSet.getString("address"));
                meeting.setTime(resultSet.getLong("time"));
                meetingList.add(meeting);
            }

            return meetingList;

        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

}
