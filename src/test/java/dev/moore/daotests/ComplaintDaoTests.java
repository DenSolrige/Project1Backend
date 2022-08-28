package dev.moore.daotests;

import dev.moore.daos.ComplaintDAO;
import dev.moore.daos.ComplaintDaoPostgres;
import dev.moore.entities.Complaint;
import dev.moore.entities.ComplaintStatus;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ComplaintDaoTests {

    static ComplaintDAO complaintDAO = new ComplaintDaoPostgres();
    static List<Complaint> complaintList = new ArrayList<>();

    @Test
    @Order(1)
    void create_complaint_test(){
        Complaint complaint = new Complaint("Xanathar blew up a local orphanage (test complaint, ignored)");
        Complaint savedComplaint = complaintDAO.createComplaint(complaint);
        Assertions.assertNotEquals(0,savedComplaint.getComplaintId());
    }

    @Test
    @Order(2)
    void get_all_complaints_test(){
        complaintList = complaintDAO.getAllComplaints();
        System.out.println(complaintList);
        Assertions.assertNotEquals(0, complaintList.size());
    }

    @Test
    @Order(3)
    void update_complaint_test(){
        Complaint complaint = complaintList.get(complaintList.size()-1);
        ComplaintStatus previousStatus = complaint.getComplaintStatus();
        complaint.setComplaintStatus(ComplaintStatus.Ignored);
        complaintDAO.updateComplaint(complaint);
        List<Complaint> updatedComplaintList = complaintDAO.getAllComplaints();
        Assertions.assertNotEquals(previousStatus,updatedComplaintList.get(updatedComplaintList.size()-1).getComplaintStatus());
    }
}
