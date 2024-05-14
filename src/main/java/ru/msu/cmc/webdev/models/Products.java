package ru.msu.cmc.webdev.models;

import lombok.*;
import jakarta.persistence.*;
import java.util.Objects;
import java.util.Date;

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
    private Long size3;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "description")
    private String description;

    @Column(name = "expiration_date")
    private java.util.Date expiration_date;

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

    public String getName(){
        return name;
    }

    public String getType(){
        return type;
    }

    public Long getSize1(){
        return size1;
    }

    public Long getSize2(){
        return size2;
    }

    public Long getSize3(){
        return size3;
    }

    public Long getQuantity(){
        return quantity;
    }

    public String getDescription(){
        return description;
    }

    public Date getExpiration_date(){
        return expiration_date;
    }

    public void setId(Long id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }

    public void setType(String ptype){
        this.type = ptype;
    }

    public void setSize1(Long size){
        this.size1 = size;
    }

    public void setSize2(Long size){
        this.size2 = size;
    }

    public void setSize3(Long size){
        this.size3 = size;
    }

    public void setQuantity(Long quantity){
        this.quantity = quantity;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setExpiration_date(Date date){
        this.expiration_date= date;
    }
}