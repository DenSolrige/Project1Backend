package dev.moore.services;

import dev.moore.daos.ConstituentDAO;
import dev.moore.daos.MeetingDAO;
import dev.moore.dtos.MeetingSpeakerInput;
import dev.moore.dtos.MeetingSpeakerOutput;
import dev.moore.entities.Constituent;
import dev.moore.entities.Meeting;
import dev.moore.exceptions.MeetingSpeakerInvalidException;
import dev.moore.exceptions.NoAccountFoundException;

import javax.management.RuntimeMBeanException;
import java.util.List;
import java.util.Optional;

public class MeetingServiceImpl implements MeetingService {

    private final MeetingDAO meetingDAO;
    private final ConstituentDAO constituentDAO;

    public MeetingServiceImpl(MeetingDAO meetingDAO, ConstituentDAO constituentDAO) {
        this.meetingDAO = meetingDAO;
        this.constituentDAO = constituentDAO;
    }

    @Override
    public List<Meeting> getAllMeetings() {
        return this.meetingDAO.getAllMeetings();
    }

    @Override
    public Meeting createMeeting(Meeting meeting) {
        return this.meetingDAO.createMeeting(meeting);
    }

    @Override
    public void addSpeaker(MeetingSpeakerInput meetingSpeakerInput) {
        List<Meeting> meetingList = this.meetingDAO.getAllMeetings();
        Constituent constituent = this.constituentDAO.getAccountByUsername(meetingSpeakerInput.getUsername());
        Optional<Meeting> meeting = meetingList.stream().filter(m -> m.getMeetingId() == meetingSpeakerInput.getMeetingId()).findFirst();
        if(constituent == null || !meeting.isPresent()){
            throw new MeetingSpeakerInvalidException("Constituent or meeting does not exist");
        }else{
            this.meetingDAO.addSpeaker(meetingSpeakerInput);
        }
    }

    @Override
    public List<MeetingSpeakerOutput> getAllSpeakers() {
        return this.meetingDAO.getAllSpeakers();
    }
}
