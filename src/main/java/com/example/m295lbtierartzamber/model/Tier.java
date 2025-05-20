package com.example.m295lbtierartzamber.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "tier")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtier")
    private Long id;

    private String name;
    private LocalDate geburtstag;
    private String tierart;
    private double gewicht;
    private boolean geimpft;
    private boolean gesund;
    private LocalDate aufnahmedatum;

}
