package com.example.m295lbtierartzamber.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotBlank
    private String name;

    @PastOrPresent(message = "Aufnahme-Datum darf nicht in der Zukunft liegen")
    private LocalDate geburtstag;
    private String tierart;

    @Positive
    @DecimalMin(value = "0.1", inclusive = true)
    private double gewicht;    private boolean geimpft;
    private boolean gesund;
    private LocalDate aufnahmedatum;

}
