package com.api_ecommerce.init;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.api_ecommerce.entities.Product;
import com.api_ecommerce.entities.Seller;
import com.api_ecommerce.repositories.ProductRepository;
import com.api_ecommerce.repositories.SellerRepository;

@Component
public class Seeder implements CommandLineRunner{

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SellerRepository sellerRepository;

    @Override
    public void run(String... args) throws Exception {

         Seller s1= new Seller();
        s1.setName("Pera");
        s1.setDesc("technology company");
        s1.setEmail("email@example.com");
        s1.setCityId(1);
        s1.setCountryId(3);
        s1.setRate(5.0);
        s1.setTotalIntemsSold(100);
        s1.setVerified(true);
        sellerRepository.saveAll(List.of(s1));

        List<String> links = new ArrayList<>();
        links.add("link1");
        links.add("link2");
        links.add("link3");
        Product p1= new Product();
        p1.setCanceled(false);
        p1.setName("Celphone");
        p1.setCategoryId(2);
        p1.setSubCategoryId(1);
        p1.setRate(4.5);
        p1.setStock(1000);
        p1.setDiscount(null);
        p1.setPrice(new BigDecimal(24000));
        p1.setSellerId(1);
        p1.setLinks(links);

        productRepository.saveAll(List.of(p1));
        
    }
    
}
