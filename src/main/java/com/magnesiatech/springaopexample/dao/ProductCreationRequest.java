package com.magnesiatech.springaopexample.dao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class ProductCreationRequest implements Serializable {

    @NotNull
    @NotBlank
    public String productName;
    @NotNull
    @NotBlank
    public String productDescription;
}
