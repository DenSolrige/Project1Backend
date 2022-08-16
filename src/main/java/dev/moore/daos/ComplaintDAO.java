package dev.moore.daos;

import dev.moore.entities.Complaint;

public interface ComplaintDAO {
    // Create
    Complaint createComplaint(Complaint complaint);
}
