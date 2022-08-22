package dev.moore.daos;

import dev.moore.entities.Meeting;

import java.util.List;

public interface MeetingDAO {
    List<Meeting> getAllMeetings();
    Meeting createMeeting(Meeting meeting);
}
