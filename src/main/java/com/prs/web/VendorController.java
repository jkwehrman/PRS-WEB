package com.prs.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prs.business.JsonResponse;
import com.prs.business.Product;
import com.prs.business.User;
import com.prs.business.Vendor;
import com.prs.db.ProductRepository;
import com.prs.db.VendorRepository;

@CrossOrigin
@RestController
@RequestMapping("/vendors")
public class VendorController {

	@Autowired
	private VendorRepository vendorRepo;	

	@GetMapping("/") 
	public JsonResponse getAll() {
		System.out.println("VendorController.getAll()");
		JsonResponse jr = null;
		try {
			jr=JsonResponse.getInstance(vendorRepo.findAll());
		}
		catch (Exception e ) {
			jr=JsonResponse.getInstance(e);
		}
		return jr;
	}

	@GetMapping("/{id}")
	public JsonResponse get(@PathVariable int id) {
		JsonResponse jr = null;
		try {
			Optional<Vendor> u = vendorRepo.findById(id);
			if(u.isPresent())
				jr=JsonResponse.getInstance(u);
			else
				jr=JsonResponse.getInstance("No vendor found for id: "+id);
		}
		catch (Exception e ) {
			jr=JsonResponse.getInstance(e);
		}
		return jr;
	}

	@PostMapping("/")
	public JsonResponse add(@RequestBody Vendor u) {
		JsonResponse jr = null;
		//May need to enhance exception handling if more than one exception type needs to be caught
		try {
			jr=JsonResponse.getInstance(vendorRepo.save(u));
		}
		catch (Exception e ) {
			jr=JsonResponse.getInstance(e);
		}
		return jr;
	}

	@PutMapping("/")
	public JsonResponse update(@RequestBody Vendor u) {
		JsonResponse jr = null;
		//May need to enhance exception handling if more than one exception type needs to be caught
		try {
			if (vendorRepo.existsById(u.getId())) {
				jr=JsonResponse.getInstance(vendorRepo.save(u));
			}
			else {
				jr=JsonResponse.getInstance("Vendor id:  "+u.getId()+"does not exist and you are attemping to save it.");
			}
		}
		catch (Exception e ) {
			jr=JsonResponse.getInstance(e);
		}
		return jr;
	}

	@DeleteMapping("/")
	public JsonResponse delete(@RequestBody Vendor u) {
		JsonResponse jr = null;
		try {
			if (vendorRepo.existsById(u.getId())) {
				vendorRepo.delete(u);
				jr=JsonResponse.getInstance("Vendor deleted.");
			}
			else {
				jr=JsonResponse.getInstance("Vendor id:  "+u.getId()+" does not exist and you are attemping to save it.");
			} 
		}
		catch (Exception e ) {
			jr=JsonResponse.getInstance(e);
		}
		return jr;
	}



}
