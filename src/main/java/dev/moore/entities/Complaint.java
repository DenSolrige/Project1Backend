package dev.moore.entities;

public class Complaint {

    private int complaintId;
    private String description;
    private ComplaintStatus complaintStatus = ComplaintStatus.Unreviewed;
    private int meetingId = -1;

    public Complaint() {
    }

    public Complaint(String description) {
        this.description = description;
    }

    public int getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(int complaintId) {
        this.complaintId = complaintId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ComplaintStatus getComplaintStatus() {
        return complaintStatus;
    }

    public void setComplaintStatus(ComplaintStatus complaintStatus) {
        this.complaintStatus = complaintStatus;
    }

    public int getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(int meetingId) {
        this.meetingId = meetingId;
    }

    @Override
    public String toString() {
        return "Complaint{" +
                "complaintId=" + complaintId +
                ", description='" + description + '\'' +
                ", complaintStatus=" + complaintStatus +
                ", meetingId=" + meetingId +
                '}';
    }
}
