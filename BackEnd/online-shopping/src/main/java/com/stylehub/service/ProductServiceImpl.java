package com.stylehub.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.stylehub.dao.ProductDao;
import com.stylehub.model.Product;
import com.stylehub.utility.StorageService;


@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	
	@Autowired 
	private ProductDao productDao;
	
	@Autowired
	private StorageService storageService;

	@Override
	public void addProduct(Product product, MultipartFile productImmage) {
		
		String productImageName = storageService.store(productImmage);
		
		product.setImageName(productImageName);
		
		productDao.save(product);
	}

	
	@Override
	public void deleteProduct(Product product) {
	    // Delete the product image from the file system, if it exists
	    if (product.getImageName() != null) {
	        storageService.delete(product.getImageName());
	    }
	    
	    // Delete the product from the database
	    productDao.delete(product);
	}


	@Override
	public void updateProduct(Product product, MultipartFile image) {
	    if (!image.isEmpty()) {
	        // Store the new image and update the product's image name
	        String newImageName = storageService.store(image);
	        product.setImageName(newImageName);
	    }
	    
	    // Update the product's details
	    productDao.save(product);
	}



}
