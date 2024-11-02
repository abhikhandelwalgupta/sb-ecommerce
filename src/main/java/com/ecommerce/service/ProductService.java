package com.ecommerce.service;

import com.ecommerce.model.Product;
import com.ecommerce.payload.ProductDTO;
import com.ecommerce.payload.ProductResponse;

public interface ProductService {
     ProductDTO addProduct(Long categoryId, Product product) ;

     ProductResponse getAllProducts() ;

     ProductResponse secarchByCategory(Long categoryId);

     ProductResponse searchProductByKeyword(String keyword);
}
