package ru.msu.cmc.webdev.models;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "warehouse_spaces")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Warehouse_spaces implements CommonEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "place_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    @ToString.Exclude
    private Products product;

    @Column(nullable = false, name = "room")
    @NonNull
    private Long room;

    @Column(nullable = false, name = "shelf")
    @NonNull
    private Long shelf;

    @Column(name = "product_type")
    private String ptype;

    @Column(name = "empty_space")
    private boolean empty_space;

    public Warehouse_spaces() {
    }
}