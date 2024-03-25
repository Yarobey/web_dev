package ru.msu.cmc.webdev.models;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "supplies")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Supplies implements CommonEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "supply_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supplier_id")
    @ToString.Exclude
    private Suppliers supplier;

    @Column(nullable = false, name = "ship_date")
    @NonNull
    private java.sql.Date ship_date;

    @Column(name = "comment")
    private String comment;

    public Supplies() {
    }
}