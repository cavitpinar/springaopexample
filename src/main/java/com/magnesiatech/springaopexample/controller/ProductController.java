package com.magnesiatech.springaopexample.controller;

import com.magnesiatech.springaopexample.aspect.annotation.TrackTime;
import com.magnesiatech.springaopexample.dao.ProductCreationRequest;
import com.magnesiatech.springaopexample.repository.entity.Product;
import com.magnesiatech.springaopexample.service.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.magnesiatech.springaopexample.constants.RestApiConstants.APP;
import static com.magnesiatech.springaopexample.constants.RestApiConstants.PRODUCT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(APP + "/" + PRODUCT)
@Validated
public class ProductController {

    @Autowired
    private ProductService productService;

    @ApiOperation(value = "Create Product", nickname = "createProduct", response = ResponseEntity.class)
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    @TrackTime
    public ResponseEntity<Void> createProduct(@RequestBody @Valid ProductCreationRequest request) {

        Product product = Product.builder()
                .productName(request.productName)
                .productDescription(request.productDescription).build();

        productService.createProduct(product);

        return ok().build();
    }


}
