package com.karan.leavemanagement.dto;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaveRequestDTO {
    private Long employeeId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String actionBy;
    private String reason;

}
