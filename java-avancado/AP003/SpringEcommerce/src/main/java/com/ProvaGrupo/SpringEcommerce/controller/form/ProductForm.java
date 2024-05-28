package com.ProvaGrupo.SpringEcommerce.controller.form;

import java.math.BigDecimal;
import java.util.List;

import com.ProvaGrupo.SpringEcommerce.model.ProductAttribute;
import com.ProvaGrupo.SpringEcommerce.model.ProductRating;



public record ProductForm(String name, String description, BigDecimal price, String sku, String imageUrl, Long categoryId, List<ProductAttribute> productAttributeList, Integer quantity, String manufacturer, boolean featured, List<ProductRating> productRating) {

}