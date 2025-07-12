package com.example.demo.bootstrap;

import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Product;
import com.example.demo.repositories.OutsourcedPartRepository;
import com.example.demo.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final OutsourcedPartRepository outsourcedPartRepository;
    private final ProductRepository productRepository;

    public BootStrapData(OutsourcedPartRepository outsourcedPartRepository,
                         ProductRepository productRepository) {
        this.outsourcedPartRepository = outsourcedPartRepository;
        this.productRepository        = productRepository;
    }

    @Override
    public void run(String... args) {
        long partsCount    = outsourcedPartRepository.count();
        long productsCount = productRepository.count();

        // Only seed data if none exists yet
        if (partsCount == 0 && productsCount == 0) {
            // --- PARTS ---
            OutsourcedPart p1 = new OutsourcedPart();
            p1.setCompanyName("Mike’s Bikes Factory");
            p1.setName("Wheel");
            p1.setInv(10);
            p1.setPrice(29.99);
            outsourcedPartRepository.save(p1);

            OutsourcedPart p2 = new OutsourcedPart();
            p2.setCompanyName("Mike’s Bikes Factory");
            p2.setName("Saddle");
            p2.setInv(20);
            p2.setPrice(14.99);
            outsourcedPartRepository.save(p2);

            OutsourcedPart p3 = new OutsourcedPart();
            p3.setCompanyName("Mike’s Bikes Factory");
            p3.setName("Pedal");
            p3.setInv(30);
            p3.setPrice(9.99);
            outsourcedPartRepository.save(p3);

            OutsourcedPart p4 = new OutsourcedPart();
            p4.setCompanyName("Mike’s Bikes Factory");
            p4.setName("Handlebar");
            p4.setInv(15);
            p4.setPrice(19.99);
            outsourcedPartRepository.save(p4);

            OutsourcedPart p5 = new OutsourcedPart();
            p5.setCompanyName("Mike’s Bikes Factory");
            p5.setName("Chain");
            p5.setInv(25);
            p5.setPrice(11.49);
            outsourcedPartRepository.save(p5);

            // --- PRODUCTS ---
            productRepository.save(new Product("Mountain Bike", 299.99,  5));
            productRepository.save(new Product("Road Bike",     249.99,  4));
            productRepository.save(new Product("Electric Bike", 499.99,  3));
            productRepository.save(new Product("Unicycle",       79.99,  6));
            productRepository.save(new Product("Touring Bike",  349.99,  2));
        }

        // Confirm load
        System.out.println("Loaded parts:    " + outsourcedPartRepository.count());
        System.out.println("Loaded products: " + productRepository.count());
    }
}
