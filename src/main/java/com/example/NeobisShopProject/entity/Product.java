package com.example.NeobisShopProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.Name;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @Name("product_id")
    private Long productId;
    @NotNull
    @Column(unique = true, nullable = false, name = "product_name")
    private String productName;

    @NotNull
    @Column(nullable = false, name = "product_price")
    private Double productPrice;

    @NotNull
    @Column(nullable = false)
    private String description;

    @Column
    private int quantity;

   @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private Category category;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<OrderDetails> orderDetails;


}
