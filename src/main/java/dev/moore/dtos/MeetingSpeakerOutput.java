package dev.moore.dtos;

public class MeetingSpeakerOutput {
    private String fname;
    private String lname;
    private int meetingId;

    public MeetingSpeakerOutput() {
    }

    public MeetingSpeakerOutput(String fname, String lname, int meetingId) {
        this.fname = fname;
        this.lname = lname;
        this.meetingId = meetingId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public int getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(int meetingId) {
        this.meetingId = meetingId;
    }

    @Override
    public String toString() {
        return "MeetingSpeakerOutput{" +
                "fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", meetingId=" + meetingId +
                '}';
    }
}
