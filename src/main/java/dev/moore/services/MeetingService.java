package dev.moore.services;

import dev.moore.dtos.MeetingSpeakerInput;
import dev.moore.dtos.MeetingSpeakerOutput;
import dev.moore.entities.Meeting;

import java.util.List;

public interface MeetingService {
    List<Meeting> getAllMeetings();
    Meeting createMeeting(Meeting meeting);
    void addSpeaker(MeetingSpeakerInput meetingSpeakerInput);
    List<MeetingSpeakerOutput> getAllSpeakers();
}
