package com.karan.leavemanagement.dto;

import com.karan.leavemanagement.model.LeaveStatus;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaveResponseDTO {
    private Long id;
    private Long employeeId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LeaveStatus status;
    private String reason;
    private String actionBy;
    private String remarks;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
