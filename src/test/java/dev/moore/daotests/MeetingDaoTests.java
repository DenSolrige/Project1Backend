package dev.moore.daotests;

import dev.moore.daos.MeetingDAO;
import dev.moore.daos.MeetingDaoPostgres;
import dev.moore.entities.Meeting;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MeetingDaoTests {

    static MeetingDAO meetingDAO = new MeetingDaoPostgres();

    @Test
    void get_all_meetings_test(){
        List<Meeting> meetingList = meetingDAO.getAllMeetings();
        Assertions.assertEquals(-1,meetingList.get(0).getMeetingId());
        Assertions.assertNotEquals(0,meetingList.size());
    }
}
