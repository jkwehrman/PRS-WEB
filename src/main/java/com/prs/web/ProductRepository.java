package com.prs.web;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.prs.business.Product;
import com.prs.business.Vendor;

public interface ProductRepository extends CrudRepository<Product, Integer> {

	public Optional<Product> findByVendor(Vendor i);
	public Optional<Product> findByPartNumber(String partNumber);
}
