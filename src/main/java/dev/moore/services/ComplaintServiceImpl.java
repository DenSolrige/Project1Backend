package dev.moore.services;

import dev.moore.daos.ComplaintDAO;
import dev.moore.entities.Complaint;

public class ComplaintServiceImpl implements ComplaintService{

    private final ComplaintDAO complaintDAO;

    public ComplaintServiceImpl(ComplaintDAO complaintDAO) {
        this.complaintDAO = complaintDAO;
    }

    @Override
    public Complaint createComplaint(Complaint complaint) {
        return this.complaintDAO.createComplaint(complaint);
    }
}
