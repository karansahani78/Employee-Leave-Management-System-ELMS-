package com.karan.leavemanagement.service;

import com.karan.leavemanagement.dto.LeaveRequestDTO;
import com.karan.leavemanagement.dto.LeaveResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface LeaveRequestService {
    public LeaveResponseDTO applyLeave(LeaveRequestDTO leaveRequestDTO);
    public LeaveResponseDTO approveLeave(LeaveRequestDTO leaveRequestDTO, Long  leaveId);
    public LeaveResponseDTO rejectLeave(LeaveRequestDTO leaveRequestDTO, Long leaveId);
    public LeaveResponseDTO cancelLeave(LeaveRequestDTO leaveRequestDTO, Long  leaveId);
    public LeaveResponseDTO getLeaveRequestById(Long id);
    public List<LeaveResponseDTO> findAllLeaveRequests();
    public void deleteLeaveRequestById(Long id);

}
