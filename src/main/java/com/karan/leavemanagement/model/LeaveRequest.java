package com.karan.leavemanagement.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaveRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long employeeId;
    @Enumerated(EnumType.STRING)
    private LeaveStatus status;
    private String reason;
    private String actionBy;
    private String remarks;
    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;
    @UpdateTimestamp
    private LocalDateTime updatedDate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @PrePersist
    public void onCreate() {
        this.createdDate = LocalDateTime.now();
    }
    @PreUpdate
    public void onUpdate() {
        this.updatedDate = LocalDateTime.now();
    }
}
