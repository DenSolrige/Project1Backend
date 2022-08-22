package dev.moore.app;

import com.google.gson.Gson;
import dev.moore.daos.ComplaintDaoPostgres;
import dev.moore.daos.MeetingDaoPostgres;
import dev.moore.entities.Complaint;
import dev.moore.entities.Meeting;
import dev.moore.services.ComplaintService;
import dev.moore.services.ComplaintServiceImpl;
import dev.moore.services.MeetingService;
import dev.moore.services.MeetingServiceImpl;
import io.javalin.Javalin;

import java.util.List;

public class App {

    public static ComplaintService complaintService = new ComplaintServiceImpl(new ComplaintDaoPostgres());
    public static MeetingService meetingService = new MeetingServiceImpl(new MeetingDaoPostgres());

    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.enableDevLogging();
            config.enableCorsForAllOrigins();
        });

        app.post("/complaints",ctx->{
            Gson gson = new Gson();
            String json = ctx.body();
            Complaint complaint = gson.fromJson(json, Complaint.class);
            Complaint savedComplaint = complaintService.createComplaint(complaint);
            if(savedComplaint == null){
                ctx.status(500);
                ctx.result("That's the servers bad, probably need meeting id -1 inputted in the meeting table");
                return;
            }
            String savedJson = gson.toJson(savedComplaint);
            ctx.status(201);
            ctx.result(savedJson);
        });

        app.get("/meetings", ctx -> {
            Gson gson = new Gson();
            List<Meeting> meetingList = meetingService.getAllMeetings();
            String json = gson.toJson(meetingList);
            ctx.status(200);
            ctx.result(json);
        });

        app.get("/complaints",ctx -> {
           List<Complaint> complaintList = complaintService.getAllComplaints();
           Gson gson = new Gson();
           String json = gson.toJson(complaintList);
           ctx.status(200);
           ctx.result(json);
        });

        app.patch("/complaints",ctx -> {
           String json = ctx.body();
           Gson gson = new Gson();
           Complaint complaint = gson.fromJson(json, Complaint.class);
           Complaint updatedComplaint = complaintService.updateComplaint(complaint);
           String updatedJson = gson.toJson(updatedComplaint);
           ctx.status(200);
           ctx.result(updatedJson);
        });

        app.post("/meetings", ctx -> {
           String json = ctx.body();
           Gson gson = new Gson();
           Meeting meeting = gson.fromJson(json, Meeting.class);
           Meeting savedMeeting = meetingService.createMeeting(meeting);
           String savedJson = gson.toJson(savedMeeting);
           ctx.status(200);
           ctx.result(savedJson);
        });

        app.start();
    }
}
