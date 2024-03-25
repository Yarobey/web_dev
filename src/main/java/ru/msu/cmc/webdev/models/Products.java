package ru.msu.cmc.webdev.models;

import lombok.*;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "products")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Products implements CommonEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "product_id")
    private Long id;

    @Column(nullable = false, name = "name")
    @NonNull
    private String name;

    @Column(name = "product_type")
    private String type;

    @Column(name = "size1")
    private Long size1;

    @Column(name = "size2")
    private Long size2;

    @Column(name = "size3")
    private Long address;

    @Column(name = "description")
    private String description;

    @Column(name = "expiration_date")
    private Long expiration_date;

    public Products() {
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Products other = (Products) obj;
        return Objects.equals(id, other.id)
                && name.equals(other.name)
                && Objects.equals(type, other.type);
    }
}