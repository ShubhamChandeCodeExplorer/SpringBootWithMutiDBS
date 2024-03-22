package com.csi.controller;

import com.csi.exception.RecordNotFoundException;
import com.csi.model.Customer;
import com.csi.model.Product;
import com.csi.service.CustomerServiceImpl;
import com.csi.service.ProductServiecImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {
    @Autowired
    private ProductServiecImpl productServieImpl;

    @PostMapping("/saveproduct")
    public ResponseEntity<Product> saveCustomer(@Valid @RequestBody Product product) {
        return ResponseEntity.ok(productServieImpl.save(product));
    }

    @GetMapping("/findbyproductid/{prodId}")
    public ResponseEntity<Optional<Product>> findByCustId(@PathVariable int prodId) {
        return ResponseEntity.ok(productServieImpl.findByProdId(prodId));
    }

    @PutMapping("update/{prodId}")
    public ResponseEntity<Product> update(@PathVariable int custId, @Valid @RequestBody Product product) {
        Product product1 = productServieImpl.findByProdId(custId).orElseThrow(() -> new RecordNotFoundException("customer id dosent exist"));
        product1.setProdPrice(product.getProdPrice());
        product1.setProdName(product.getProdName());
        product1.setProdLaunchDate(product.getProdLaunchDate());
        return ResponseEntity.ok(productServieImpl.updateProduct(product1));
    }

    @DeleteMapping("/deletebyid/{prodId}")
    public ResponseEntity<String> deletebyId(@PathVariable int prodId) {
        productServieImpl.deleteByProdId(prodId);
        return ResponseEntity.ok("Product data deleted sucessfully!!!!");
    }


}
