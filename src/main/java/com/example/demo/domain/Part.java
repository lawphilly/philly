package com.example.demo.domain;

import com.example.demo.validators.ValidDeletePart;
import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@ValidDeletePart
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "part_type", discriminatorType = DiscriminatorType.INTEGER)
@Table(name = "Parts")
public abstract class Part implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @Min(value = 0, message = "Price value must be positive")
    private double price;

    @Min(value = 0, message = "Inventory value must be positive")
    private int inv;

    @Min(value = 0, message = "Min inventory must be positive")
    private int minInv;  // ← NEW FIELD

    @Min(value = 0, message = "Max inventory must be positive")
    private int maxInv;  // ← NEW FIELD

    @ManyToMany
    @JoinTable(
            name = "product_part",
            joinColumns = @JoinColumn(name = "part_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> products = new HashSet<>();

    public Part() {}

    public Part(String name, double price, int inv, int minInv, int maxInv) {
        this.name   = name;
        this.price  = price;
        this.inv    = inv;
        this.minInv = minInv;
        this.maxInv = maxInv;
    }

    public Part(long id, String name, double price, int inv, int minInv, int maxInv) {
        this.id     = id;
        this.name   = name;
        this.price  = price;
        this.inv    = inv;
        this.minInv = minInv;
        this.maxInv = maxInv;
    }

    // ─── Getters & Setters ───────────────────────────────────────────────────────

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getInv() { return inv; }
    public void setInv(int inv) { this.inv = inv; }

    public int getMinInv() { return minInv; }
    public void setMinInv(int minInv) { this.minInv = minInv; }

    public int getMaxInv() { return maxInv; }
    public void setMaxInv(int maxInv) { this.maxInv = maxInv; }

    public Set<Product> getProducts() { return products; }
    public void setProducts(Set<Product> products) { this.products = products; }

    /** Returns true if inv is between minInv and maxInv (inclusive) */
    public boolean isInvValid() {
        return inv >= minInv && inv <= maxInv;
    }
}
