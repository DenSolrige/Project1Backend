package dev.moore.dtos;

public class MeetingSpeakerInput {
    private int meetingId;
    private String username;

    public MeetingSpeakerInput() {
    }

    public MeetingSpeakerInput(int meetingId, String username) {
        this.meetingId = meetingId;
        this.username = username;
    }

    public int getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(int meetingId) {
        this.meetingId = meetingId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "MeetingSpeaker{" +
                "meetingId=" + meetingId +
                ", username='" + username + '\'' +
                '}';
    }
}
