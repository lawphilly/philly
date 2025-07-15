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
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            if (product.getInv() > 0) {
                product.setInv(product.getInv() - 1);
                productRepository.save(product);
                redirectAttrs.addFlashAttribute("message",
                        "Successfully purchased: " + product.getName());
            } else {
                redirectAttrs.addFlashAttribute("error",
                        "Sorry, “" + product.getName() + "” is out of stock.");
            }
        } else {
            redirectAttrs.addFlashAttribute("error",
                    "Product not found for ID: " + id);
        }
        return "redirect:/";
    }
}
