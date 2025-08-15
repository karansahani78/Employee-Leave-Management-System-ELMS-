package com.karan.leavemanagement.repository;

import com.karan.leavemanagement.model.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveRepository extends JpaRepository<LeaveRequest,Long> {
}
