package dev.moore.services;

import dev.moore.daos.MeetingDAO;
import dev.moore.entities.Meeting;

import java.util.List;

public class MeetingServiceImpl implements MeetingService {

    private final MeetingDAO meetingDAO;

    public MeetingServiceImpl(MeetingDAO meetingDAO) {
        this.meetingDAO = meetingDAO;
    }

    @Override
    public List<Meeting> getAllMeetings() {
        return this.meetingDAO.getAllMeetings();
    }
}
