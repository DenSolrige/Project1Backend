package dev.moore.entities;

public class Meeting {
    private int meetingId;
    private String description;
    private String address;
    private long time;

    public Meeting() {
    }

    public Meeting(String description, String address, long time) {
        this.description = description;
        this.address = address;
        this.time = time;
    }

    public int getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(int meetingId) {
        this.meetingId = meetingId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "meetingId=" + meetingId +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", time=" + time +
                '}';
    }
}
