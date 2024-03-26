package ru.msu.cmc.webdev.models;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "products_ordered")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Products_ordered implements CommonEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_number")
    @ToString.Exclude
    private Orders order;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    @ToString.Exclude
    private Products product;

    @Column(nullable = false, name = "quantity")
    @NonNull
    private Long quantity;

    public Products_ordered() {
    }
}