package com.example.NeobisShopProject.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import jdk.jfr.Name;
import lombok.*;

import java.util.List;


@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Name("category_id")
    @Column(nullable = false)
    private Long categoryId;

    @Column(unique = true, name = "category_name")
    private String categoryName;

   @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private List<Product> products;
}
