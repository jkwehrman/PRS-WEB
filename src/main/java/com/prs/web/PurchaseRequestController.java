package com.prs.web;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.prs.business.PurchaseRequest;
import com.prs.business.User;
import com.prs.db.PurchaseRequestRepository;
import com.prs.db.UserRepository;

@RestController
@RequestMapping("/purchase-requests")
public class PurchaseRequestController {

	@Autowired
	private PurchaseRequestRepository purchaseRequestRepo;

	@GetMapping("/")
	public JsonResponse getAll() {
		JsonResponse jr = null;
		try {
			jr=JsonResponse.getInstance(purchaseRequestRepo.findAll());
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
			Optional<PurchaseRequest> pr = purchaseRequestRepo.findById(id);
			if(pr.isPresent())
				jr=JsonResponse.getInstance(pr);
			else
				jr=JsonResponse.getInstance("No purchase request found for id: "+id);
		}
		catch (Exception e ) {
			jr=JsonResponse.getInstance(e);
		}
		return jr;
	}

	@PostMapping("/")
	public JsonResponse add(@RequestBody PurchaseRequest pr) {
		JsonResponse jr = null;
		pr.setSubmittedDate(LocalDateTime.now());
		try { 
			jr=JsonResponse.getInstance(purchaseRequestRepo.save(pr));
		}
		catch (Exception e ) {
			e.printStackTrace();
			jr=JsonResponse.getInstance(e);
		}
		return jr;
	}

	@PostMapping("/submit-new")
	public JsonResponse submitNew(@RequestBody PurchaseRequest pr) {
		JsonResponse jr = null;
		pr.setStatus("new");
		pr.setSubmittedDate(LocalDateTime.now());
		try {
			jr=JsonResponse.getInstance(purchaseRequestRepo.save(pr));
		}
		catch (Exception e ) {
			e.printStackTrace();
			jr=JsonResponse.getInstance(e);
		}
		return jr;
	}

	@PutMapping("/submit-review")
	public JsonResponse SubmitForReview(@RequestBody PurchaseRequest pr) {
		JsonResponse jr = null;
		double costLimit = 50.00;
		if (pr.getTotal() < costLimit) {
			pr.setStatus("Approved");	
		}
		else {
			pr.setStatus("review");
		}
		Date date = new Date();
		pr.setSubmittedDate(LocalDateTime.now());
		try {
			if (purchaseRequestRepo.existsById(pr.getId())) {
				jr=JsonResponse.getInstance(purchaseRequestRepo.save(pr));
			}
			else {
				jr=JsonResponse.getInstance("PurchaseRequest id:  "+pr.getId()+"does not exist and you are attemping to save it.");
			}
		}
		catch (Exception e ) {
			jr=JsonResponse.getInstance(e);
		}
		return jr;
	}

	@PutMapping("/reject")
	public JsonResponse reject(@RequestBody PurchaseRequest pr) {
		JsonResponse jr = null;
		pr.setStatus("Rejected");
		try {
			if (purchaseRequestRepo.existsById(pr.getId())) {
				jr=JsonResponse.getInstance(purchaseRequestRepo.save(pr));
			}
			else {
				jr=JsonResponse.getInstance("PurchaseRequest id:  "+pr.getId()+"does not exist and you are attemping to save it.");
			}
		}
		catch (Exception e ) {
			jr=JsonResponse.getInstance(e);
		}
		return jr;
	}

	@PutMapping("/approve")
	public JsonResponse approve(@RequestBody PurchaseRequest pr) {
		JsonResponse jr = null;
		pr.setStatus("Approved");
		pr.setReasonForRejection(null);
		try {
			if (purchaseRequestRepo.existsById(pr.getId())) {
				jr=JsonResponse.getInstance(purchaseRequestRepo.save(pr));
			}
			else {
				jr=JsonResponse.getInstance("PurchaseRequest id:  "+pr.getId()+"does not exist and you are attemping to save it.");
			}
		}
		catch (Exception e ) {
			jr=JsonResponse.getInstance(e);
		}
		return jr;
	}

	@PutMapping("/")
	public JsonResponse update(@RequestBody PurchaseRequest pr) {
		JsonResponse jr = null;
		try {
			if (purchaseRequestRepo.existsById(pr.getId())) {
				jr=JsonResponse.getInstance(purchaseRequestRepo.save(pr));


			}
			else {
				jr=JsonResponse.getInstance("PurchaseRequest id:  "+pr.getId()+"does not exist and you are attemping to save it.");
			}
		}
		catch (Exception e ) {
			jr=JsonResponse.getInstance(e);
		}
		return jr;
	}

	@DeleteMapping("/")
	public JsonResponse delete(@RequestBody PurchaseRequest pr) {
		JsonResponse jr = null;
		try {
			if (purchaseRequestRepo.existsById(pr.getId())) {
				purchaseRequestRepo.delete(pr);
				jr=JsonResponse.getInstance("Purchase Request deleted.");
			}
			else {
				jr=JsonResponse.getInstance("PurchaseRequest id:  "+pr.getId()+"does not exist and you are attemping to save it.");
			}
		}
		catch (Exception e ) {
			jr=JsonResponse.getInstance(e);
		}
		return jr;
	}

	@PostMapping("/list-review")
	public JsonResponse listReview() {
		JsonResponse jr = null;
		User user = new User(1, "gloczzzzzzkhard", "gpassword", "guilderoy", "lockheart", "123-432-3456", "guilderoy@hogwarts.edu", false,true);



		try {
			List<PurchaseRequest> pr = purchaseRequestRepo.findByStatusAndUserNot("review", user );
			if(!pr.isEmpty()) {
				jr=JsonResponse.getInstance(pr);
			}	else {
				jr=JsonResponse.getInstance("No purchase request records found for status review and user ID 3.");
			}
		} 
		catch (Exception e ) {
			jr=JsonResponse.getInstance(e);
		}
		return jr;
	}
}


