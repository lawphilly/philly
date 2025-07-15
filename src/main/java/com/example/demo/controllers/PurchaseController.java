package com.example.demo.controllers;

import com.example.demo.domain.Product;
import com.example.demo.repositories.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class PurchaseController {

    private final ProductRepository productRepository;

    public PurchaseController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping("/buyProduct")
    public String buyProduct(@RequestParam("productId") Long id,
                             RedirectAttributes redirectAttrs) {
        Optional<Product> opt = productRepository.findById(id);
        if (opt.isPresent()) {
            Product p = opt.get();
            if (p.getInv() > 0) {
                p.setInv(p.getInv() - 1);
                productRepository.save(p);
                redirectAttrs.addFlashAttribute("message", "Purchased \"" + p.getName() + "\" successfully");
            } else {
                redirectAttrs.addFlashAttribute("error", "Sorry, \"" + p.getName() + "\" is out of stock");
            }
        } else {
            redirectAttrs.addFlashAttribute("error", "Product not found (ID: " + id + ")");
        }
        return "redirect:/";
    }
}
