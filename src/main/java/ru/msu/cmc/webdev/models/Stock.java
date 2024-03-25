package ru.msu.cmc.webdev.models;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "stock")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Stock implements CommonEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "stock_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    @ToString.Exclude
    private Products product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supply_id")
    @ToString.Exclude
    private Supplies supply;

    @Column(nullable = false, name = "quantity")
    @NonNull
    private Long quantity;

    public Stock() {
    }
}