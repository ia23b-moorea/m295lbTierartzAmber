package com.example.m295lbtierartzamber.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "tierarztbesuch")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tierarztbesuch {

    @Id
    private Long id;

    private LocalDate datum;
    private String beschreibung;
    @Digits(integer = 6, fraction = 2)
    private double kosten;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tier_idtier", nullable = false)
    private Tier tier;


}

