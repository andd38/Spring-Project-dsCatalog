package com.project_Spring_treino.dsCatalog.Entities;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    private Double price;
    @Column(name = "img_url")
    private String imgURL;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant date;
    @ManyToMany
    @JoinTable(
            name = "tb_product_category",
            joinColumns = @JoinColumn(name = "product_id") ,
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    public Set<Category>categories= new HashSet<>();

    public Product(Long id, String name, String description, Double price, String imgURL,Instant date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgURL = imgURL;
        this.date = date;
    }

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
