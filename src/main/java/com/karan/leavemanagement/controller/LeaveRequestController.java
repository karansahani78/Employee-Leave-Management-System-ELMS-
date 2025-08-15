package com.karan.leavemanagement.controller;

import com.karan.leavemanagement.dto.LeaveRequestDTO;
import com.karan.leavemanagement.dto.LeaveResponseDTO;
import com.karan.leavemanagement.model.LeaveRequest;
import com.karan.leavemanagement.service.LeaveRequestService;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/requests")
public class LeaveRequestController {
    private final LeaveRequestService leaveRequestService;

    public LeaveRequestController(LeaveRequestService leaveRequestService) {
        this.leaveRequestService = leaveRequestService;
    }
    @PostMapping("/apply")
    public ResponseEntity<LeaveResponseDTO> applyLeave(@RequestBody LeaveRequestDTO leaveRequestDTO) {
        LeaveResponseDTO leave =   leaveRequestService.applyLeave(leaveRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(leave);
    }
    @PutMapping("/approve/{leaveId}")
    public ResponseEntity<LeaveResponseDTO> approveLeave(@PathVariable Long leaveId, @RequestBody LeaveRequestDTO leaveRequestDTO) {
        LeaveResponseDTO approvedLeave = leaveRequestService.approveLeave(leaveRequestDTO, leaveId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(approvedLeave);
    }
    @PutMapping("/reject/{leaveId}")
    public ResponseEntity<LeaveResponseDTO> rejectLeave(@PathVariable Long leaveId, @RequestBody LeaveRequestDTO leaveRequestDTO) {
        LeaveResponseDTO rejectedLeave = leaveRequestService.rejectLeave(leaveRequestDTO, leaveId);
        return ResponseEntity.status(HttpStatus.CREATED).body(rejectedLeave);
    }
    @PutMapping("/cancel/{leaveId}")
    public ResponseEntity<LeaveResponseDTO> cancelLeave(@PathVariable Long leaveId, @RequestBody LeaveRequestDTO leaveRequestDTO) {
        LeaveResponseDTO cancelledLeave = leaveRequestService.cancelLeave(leaveRequestDTO, leaveId );
        return ResponseEntity.status(HttpStatus.CREATED).body(cancelledLeave);

    }
    @GetMapping("/leaves")
    public ResponseEntity<List<LeaveResponseDTO>> getAllLeaveRequests() {
        List<LeaveResponseDTO> leaveRequests = leaveRequestService.findAllLeaveRequests();
        return ResponseEntity.status(HttpStatus.OK).body(leaveRequests);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<LeaveResponseDTO> getLeaveRequestById(@PathVariable Long id) {
        LeaveResponseDTO leaveRequest = leaveRequestService.getLeaveRequestById(id);
        return ResponseEntity.status(HttpStatus.OK).body(leaveRequest);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLeaveRequestById(@PathVariable Long id) {
        leaveRequestService.deleteLeaveRequestById(id);
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
