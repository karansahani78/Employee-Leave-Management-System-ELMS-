package com.karan.leavemanagement.serviceimpl;

import com.karan.leavemanagement.dto.LeaveRequestDTO;
import com.karan.leavemanagement.dto.LeaveResponseDTO;
import com.karan.leavemanagement.exceptions.LEAVENOTFOUNDEXCEPTION;
import com.karan.leavemanagement.model.LeaveRequest;
import com.karan.leavemanagement.model.LeaveStatus;
import com.karan.leavemanagement.repository.LeaveRepository;
import com.karan.leavemanagement.service.LeaveRequestService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {

    private final LeaveRepository leaveRepository;
    public LeaveRequestServiceImpl(LeaveRepository leaveRepository) {
        this.leaveRepository = leaveRepository;
    }

    @Override
    public LeaveResponseDTO applyLeave(LeaveRequestDTO leaveRequestDTO) {
        LeaveRequest leaveRequest = LeaveRequest.builder()
                .employeeId(leaveRequestDTO.getEmployeeId())
                .startDate(leaveRequestDTO.getStartDate())
                .endDate(leaveRequestDTO.getEndDate())
                .reason(leaveRequestDTO.getReason())
                .status(LeaveStatus.PENDING)
                .actionBy("EMPLOYEE")
                .build();

        LeaveRequest savedLeaveRequest = leaveRepository.save(leaveRequest);

        return LeaveResponseDTO.builder()
                .id(savedLeaveRequest.getId())
                .employeeId(savedLeaveRequest.getEmployeeId())
                .startDate(savedLeaveRequest.getStartDate())
                .endDate(savedLeaveRequest.getEndDate())
                .reason(savedLeaveRequest.getReason())
                .status(savedLeaveRequest.getStatus())
                .actionBy(savedLeaveRequest.getActionBy())
                .remarks(savedLeaveRequest.getRemarks())
                .createdDate(savedLeaveRequest.getCreatedDate())
                .updatedDate(savedLeaveRequest.getUpdatedDate())
                .build();
    }


    @Override
    public LeaveResponseDTO approveLeave(LeaveRequestDTO leaveRequestDTO, Long leaveId) {
        LeaveRequest leave = leaveRepository.findById(leaveId).orElseThrow(()-> new LEAVENOTFOUNDEXCEPTION("leave not found for given id " +leaveId));

        if(leave.getStatus()!= LeaveStatus.PENDING) {
            throw new IllegalStateException("Only pending leaves can be approved.");

        }
        leave.setStatus(LeaveStatus.APPROVED);
        leave.setActionBy(leaveRequestDTO.getActionBy());
        leave.setRemarks(leaveRequestDTO.getReason());

        LeaveRequest saved =leaveRepository.save(leave);
        return LeaveResponseDTO.builder()
                .id(saved.getId())
                .employeeId(saved.getEmployeeId())
                .startDate(saved.getStartDate())
                .endDate(saved.getEndDate())
                .reason(saved.getReason())
                .status(saved.getStatus())
                .actionBy(saved.getActionBy())
                .remarks(saved.getRemarks())
                .createdDate(saved.getCreatedDate())
                .updatedDate(saved.getUpdatedDate())
                .build();

    }

    @Override
    public LeaveResponseDTO rejectLeave(LeaveRequestDTO leaveRequestDTO, Long leaveId) {
        LeaveRequest leave = leaveRepository.findById(leaveId).orElseThrow(()->new LEAVENOTFOUNDEXCEPTION("leave not found for given id " + leaveId));
        if(leave.getStatus()!= LeaveStatus.PENDING) {
            throw new IllegalStateException("Only pending leaves can be rejected.");
        }
        leave.setStatus(LeaveStatus.REJECTED);
        leave.setActionBy(leaveRequestDTO.getActionBy());
        leave.setReason(leaveRequestDTO.getReason());
        leave.setRemarks(leaveRequestDTO.getReason());
        LeaveRequest saved =leaveRepository.save(leave);

        return LeaveResponseDTO.builder()
                .id(saved.getId())
                .employeeId(saved.getEmployeeId())
                .startDate(saved.getStartDate())
                .endDate(saved.getEndDate())
                .reason(saved.getReason())
                .status(saved.getStatus())
                .actionBy(saved.getActionBy())
                .remarks(saved.getRemarks())
                .createdDate(saved.getCreatedDate())
                .updatedDate(saved.getUpdatedDate())
                .build();

    }

    @Override
    public LeaveResponseDTO cancelLeave(LeaveRequestDTO leaveRequestDTO, Long leaveId) {
        LeaveRequest leave = leaveRepository.findById(leaveId).orElseThrow(()-> new LEAVENOTFOUNDEXCEPTION("leave not found for given id " + leaveId));
        if(leave.getStatus()!= LeaveStatus.PENDING) {
            throw new IllegalStateException("Only pending leaves can be cancelled.");
        }
        leave.setStatus(LeaveStatus.CANCELLED);
        leave.setActionBy(leaveRequestDTO.getActionBy());
        leave.setRemarks(leaveRequestDTO.getReason());
        LeaveRequest saved =leaveRepository.save(leave);

        return LeaveResponseDTO.builder()
                .id(saved.getId())
                .employeeId(saved.getEmployeeId())
                .startDate(saved.getStartDate())
                .endDate(saved.getEndDate())
                .reason(saved.getReason())
                .status(saved.getStatus())
                .actionBy(saved.getActionBy())
                .remarks(saved.getRemarks())
                .createdDate(saved.getCreatedDate())
                .updatedDate(saved.getUpdatedDate())
                .build();
    }

    @Override
    public LeaveResponseDTO getLeaveRequestById(Long id) {
        LeaveRequest leave = leaveRepository.findById(id).orElseThrow(()-> new LEAVENOTFOUNDEXCEPTION("leave not found for given id" + id));

        return  LeaveResponseDTO.builder()
                .id(leave.getId())
                .employeeId(leave.getEmployeeId())
                .startDate(leave.getStartDate())
                .endDate(leave.getEndDate())
                .reason(leave.getReason())
                .status(leave.getStatus())
                .actionBy(leave.getActionBy())
                .remarks(leave.getRemarks())
                .createdDate(leave.getCreatedDate())
                .updatedDate(leave.getUpdatedDate())
                .build();
    }

    @Override
    public List<LeaveResponseDTO> findAllLeaveRequests() {
        List<LeaveRequest> leaveRequests = leaveRepository.findAll();
        if(leaveRequests.isEmpty()){
            return Collections.emptyList();
        }
        return leaveRequests.stream()
                .map(requests ->LeaveResponseDTO.builder()
                        .id(requests.getId())
                        .employeeId(requests.getEmployeeId())
                        .startDate(requests.getStartDate())
                        .endDate(requests.getEndDate())
                        .reason(requests.getReason())
                        .status(requests.getStatus())
                        .actionBy(requests.getActionBy())
                        .remarks(requests.getRemarks())
                        .createdDate(requests.getCreatedDate())
                        .updatedDate(requests.getUpdatedDate())
                        .build()).collect(Collectors.toList());


    }

    @Override
    public void deleteLeaveRequestById(Long id) {
        if(leaveRepository.findById(id).isPresent()){
            leaveRepository.deleteById(id);
        }
    }
}
