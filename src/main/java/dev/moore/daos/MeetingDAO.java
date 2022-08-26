package dev.moore.daos;

import dev.moore.dtos.MeetingSpeakerInput;
import dev.moore.dtos.MeetingSpeakerOutput;
import dev.moore.entities.Meeting;

import java.util.List;

public interface MeetingDAO {
    List<Meeting> getAllMeetings();
    Meeting createMeeting(Meeting meeting);
    void addSpeaker(MeetingSpeakerInput meetingSpeakerInput);
    List<MeetingSpeakerOutput> getAllSpeakers();
}
