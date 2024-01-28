package com.example.databasemigrationflyway.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Authority {
    
    @Id
    @Column(length = 16)
    String name;
}
