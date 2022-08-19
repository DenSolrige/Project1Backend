package dev.moore.services;

import dev.moore.daos.ComplaintDAO;
import dev.moore.entities.Complaint;

import java.util.List;

public class ComplaintServiceImpl implements ComplaintService{

    private final ComplaintDAO complaintDAO;

    public ComplaintServiceImpl(ComplaintDAO complaintDAO) {
        this.complaintDAO = complaintDAO;
    }

    @Override
    public Complaint createComplaint(Complaint complaint) {
        return this.complaintDAO.createComplaint(complaint);
    }

    @Override
    public List<Complaint> getAllComplaints() {
        return this.complaintDAO.getAllComplaints();
    }

    @Override
    public Complaint updateComplaint(Complaint complaint) {
        return this.complaintDAO.updateComplaint(complaint);
    }
}
