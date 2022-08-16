package dev.moore.app;

import com.google.gson.Gson;
import dev.moore.daos.ComplaintDaoPostgres;
import dev.moore.entities.Complaint;
import dev.moore.services.ComplaintService;
import dev.moore.services.ComplaintServiceImpl;
import io.javalin.Javalin;

public class App {

    public static ComplaintService complaintService = new ComplaintServiceImpl(new ComplaintDaoPostgres());

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

        app.start();
    }
}
