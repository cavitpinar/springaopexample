package com.magnesiatech.springaopexample.repository.entity;

import com.magnesiatech.springaopexample.repository.entity.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "product")
@Data
@SuperBuilder
@Builder
public class Product extends BaseEntity {

    @NotNull
    @NotBlank
    private String productName;

    @NotNull
    @NotBlank
    private String productDescription;

}
