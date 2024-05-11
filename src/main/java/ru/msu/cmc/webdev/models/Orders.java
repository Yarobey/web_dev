package ru.msu.cmc.webdev.models;

import lombok.*;
import jakarta.persistence.*;
import java.util.Date;


@Entity
@Table(name = "orders")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Orders implements CommonEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "order_number")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    @ToString.Exclude
    private Customers customer;

    @Column(nullable = false, name = "ship_date")
    @NonNull
    private java.util.Date ship_date;

    @Column(name = "comment")
    private String comment;

    @Column(name = "customer_name")
    @NonNull
    private String customer_name;

    public Orders() {
    }
}