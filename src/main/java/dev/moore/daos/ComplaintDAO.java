package dev.moore.daos;

import dev.moore.entities.Complaint;

import java.util.List;

public interface ComplaintDAO {
    // Create
    Complaint createComplaint(Complaint complaint);

    // Read
    List<Complaint> getAllComplaints();

    // Update
    Complaint updateComplaint(Complaint complaint);
}
