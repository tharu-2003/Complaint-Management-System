package com.ijse.gdse72.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Complaint {
    private String c_id;
    private String u_id;
    private String title;
    private String description;
    private String status;
    private Timestamp created_at;
    private Timestamp updated_at;
    private String remarks;
}
