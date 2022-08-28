package dev.moore.servicetests;

import dev.moore.daos.ConstituentDaoPostgres;
import dev.moore.daos.MeetingDaoPostgres;
import dev.moore.dtos.MeetingSpeakerInput;
import dev.moore.entities.Constituent;
import dev.moore.services.MeetingService;
import dev.moore.services.MeetingServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

class MeetingServiceTests {

    @Test
    void username_must_exist_test(){
        MeetingDaoPostgres meetingDaoPostgres = Mockito.mock(MeetingDaoPostgres.class);
        Mockito.when(meetingDaoPostgres.getAllMeetings()).thenReturn(new ArrayList<>());
        ConstituentDaoPostgres constituentDaoPostgres = Mockito.mock(ConstituentDaoPostgres.class);
        Mockito.when(constituentDaoPostgres.getAccountByUsername("nonexistant account")).thenReturn(null);
        MeetingService meetingService = new MeetingServiceImpl(meetingDaoPostgres,constituentDaoPostgres);
        MeetingSpeakerInput meetingSpeakerInput = new MeetingSpeakerInput(-1,"nonexistant account");

        Assertions.assertThrows(RuntimeException.class,() -> {
            meetingService.addSpeaker(meetingSpeakerInput);
        });
    }

    @Test
    void meeting_must_exist_test(){
        MeetingDaoPostgres meetingDaoPostgres = Mockito.mock(MeetingDaoPostgres.class);
        Mockito.when(meetingDaoPostgres.getAllMeetings()).thenReturn(new ArrayList<>());
        ConstituentDaoPostgres constituentDaoPostgres = Mockito.mock(ConstituentDaoPostgres.class);
        Constituent constituent = new Constituent("Test","Test","existant account","Test",false,false);
        Mockito.when(constituentDaoPostgres.getAccountByUsername("existant account")).thenReturn(constituent);
        MeetingService meetingService = new MeetingServiceImpl(meetingDaoPostgres,constituentDaoPostgres);
        MeetingSpeakerInput meetingSpeakerInput = new MeetingSpeakerInput(99,"nonexistant account");

        Assertions.assertThrows(RuntimeException.class,() -> {
            meetingService.addSpeaker(meetingSpeakerInput);
        });
    }

}
