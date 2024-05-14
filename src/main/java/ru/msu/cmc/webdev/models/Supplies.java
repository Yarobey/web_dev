package ru.msu.cmc.webdev.models;

import lombok.*;
import jakarta.persistence.*;
import java.util.Date;

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
    private java.util.Date ship_date;

    @Column(name = "comment")
    private String comment;

    @Column(name = "supplier_name")
    @NonNull
    private String supplier_name;

    public Supplies() {
    }

    public Suppliers getSupplier(){
        return supplier;
    }

    public Date getShip_date(){
        return ship_date;
    }

    public String getComment(){
        return comment;
    }

    public String getSupplier_name(){
        return supplier_name;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void setSupplier(Suppliers supplier){
        this.supplier = supplier;
    }

    public void setShip_date(Date date){
        this.ship_date = date;
    }

    public void setComment(String comment){
        this.comment = comment;
    }

    public void setSupplier_name(String name){
        this.supplier_name = name;
    }
}