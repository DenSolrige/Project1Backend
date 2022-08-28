package dev.moore.daotests;

import dev.moore.daos.ConstituentDAO;
import dev.moore.daos.ConstituentDaoPostgres;
import dev.moore.daos.MeetingDAO;
import dev.moore.daos.MeetingDaoPostgres;
import dev.moore.dtos.MeetingSpeakerInput;
import dev.moore.dtos.MeetingSpeakerOutput;
import dev.moore.entities.Constituent;
import dev.moore.entities.Meeting;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

class MeetingDaoTests {

    static MeetingDAO meetingDAO = new MeetingDaoPostgres();
    static ConstituentDAO constituentDAO = new ConstituentDaoPostgres();

    @Test
    void get_all_meetings_test(){
        List<Meeting> meetingList = meetingDAO.getAllMeetings();
        Assertions.assertEquals(-1,meetingList.get(0).getMeetingId());
        Assertions.assertNotEquals(0,meetingList.size());
    }

    @Test
    void create_meeting_test(){
        Meeting meeting = meetingDAO.createMeeting(new Meeting("Test Meeting -ignore","doesn't matter",System.currentTimeMillis()/1000));
        Assertions.assertNotEquals(0,meeting.getMeetingId());
    }

    @Test
    void get_meeting_speakers_test(){
        List<MeetingSpeakerOutput> meetingSpeakerOutputs = meetingDAO.getAllSpeakers();
        Assertions.assertNotEquals(0,meetingSpeakerOutputs.size());
    }

    @Test
    void create_meeting_speaker_test(){
        List<Meeting> meetingList = meetingDAO.getAllMeetings();
        Meeting meeting = meetingList.get(meetingList.size()-1);

        List<Constituent> constituentList = constituentDAO.getAllAccounts();
        constituentList.sort(Comparator.comparing(Constituent::getConstituentId).reversed());
        Optional<Constituent> testConstituent = constituentList.stream().filter(c -> c.getFname().equals("Test")).findFirst();

        MeetingSpeakerInput meetingSpeakerInput = new MeetingSpeakerInput(meeting.getMeetingId(),testConstituent.get().getUsername());
        meetingDAO.addSpeaker(meetingSpeakerInput);
        List<MeetingSpeakerOutput> meetingSpeakerOutputs = meetingDAO.getAllSpeakers();
        MeetingSpeakerOutput meetingSpeakerOutput = meetingSpeakerOutputs.get(meetingSpeakerOutputs.size()-1);
        Assertions.assertEquals(meetingSpeakerInput.getMeetingId(),meetingSpeakerOutput.getMeetingId());
    }
}
