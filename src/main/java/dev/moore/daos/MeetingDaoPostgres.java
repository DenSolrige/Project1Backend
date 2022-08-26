package dev.moore.daos;

import dev.moore.dtos.MeetingSpeakerInput;
import dev.moore.dtos.MeetingSpeakerOutput;
import dev.moore.entities.Meeting;
import dev.moore.utils.ConnectionUtil;

import java.sql.*;
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

    @Override
    public Meeting createMeeting(Meeting meeting) {
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "insert into meeting values(default,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, meeting.getDescription());
            preparedStatement.setString(2, meeting.getAddress());
            preparedStatement.setLong(3,meeting.getTime());

            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            int generatedKey = resultSet.getInt("meeting_id");
            meeting.setMeetingId(generatedKey);
            return meeting;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addSpeaker(MeetingSpeakerInput meetingSpeakerInput) {
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "insert into meeting_speaker values(?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, meetingSpeakerInput.getMeetingId());
            preparedStatement.setString(2, meetingSpeakerInput.getUsername());

            preparedStatement.execute();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<MeetingSpeakerOutput> getAllSpeakers() {
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "select fname,lname,app_user.username,meeting_id from meeting_speaker inner join app_user on app_user.username = meeting_speaker.username";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getResultSet();
            List<MeetingSpeakerOutput> meetingSpeakerOutputs = new ArrayList<>();
            while(resultSet.next()){
                MeetingSpeakerOutput meetingSpeakerOutput = new MeetingSpeakerOutput();
                meetingSpeakerOutput.setFname(resultSet.getString("fname"));
                meetingSpeakerOutput.setLname(resultSet.getString("lname"));
                meetingSpeakerOutput.setUsername(resultSet.getString("username"));
                meetingSpeakerOutput.setMeetingId(resultSet.getInt("meeting_id"));
                meetingSpeakerOutputs.add(meetingSpeakerOutput);
            }

            return meetingSpeakerOutputs;

        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
