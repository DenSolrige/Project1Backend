package dev.moore.services;

import dev.moore.entities.Complaint;

import java.util.List;

public interface ComplaintService {
    Complaint createComplaint(Complaint complaint);

    List<Complaint> getAllComplaints();

    Complaint updateComplaint(Complaint complaint);
}
