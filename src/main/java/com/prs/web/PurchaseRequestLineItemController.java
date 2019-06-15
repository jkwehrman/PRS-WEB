package com.prs.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prs.business.JsonResponse;
import com.prs.business.Product;
import com.prs.business.PurchaseRequest;
import com.prs.business.PurchaseRequestLineItem;
import com.prs.db.PurchaseRequestLineItemRepository;
import com.prs.db.PurchaseRequestRepository;

@RestController
@RequestMapping("/purchase-request-line-items")
public class PurchaseRequestLineItemController {

	@Autowired
	private PurchaseRequestLineItemRepository purchaseRequestLineItemRepo;
	@Autowired
	private PurchaseRequestRepository purchaseRequestRepo;
	@Autowired
	private ProductRepository productRepo;

	@GetMapping("/")
	public JsonResponse getAll() {
		JsonResponse jr = null;
		try {
			jr = JsonResponse.getInstance(purchaseRequestLineItemRepo.findAll());
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}

	@GetMapping("/{id}")
	public JsonResponse get(@PathVariable int id) {
		JsonResponse jr = null;
		try {
			Optional<PurchaseRequestLineItem> li = purchaseRequestLineItemRepo.findById(id);
			if (li.isPresent())
				jr = JsonResponse.getInstance(li);
			else
				jr = JsonResponse.getInstance("No purchase request line item found for id: " + id);
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}

	@PostMapping("/")
	public JsonResponse addWithRecalculating(@RequestBody PurchaseRequestLineItem li) {
		JsonResponse jr = null;
		try {
			jr = JsonResponse.getInstance(purchaseRequestLineItemRepo.save(li));
			recalculatePRTotal(li);
		} catch (Exception e) {
			jr = JsonResponse.getInstance("PRLI Add failed. Exception is " + e.getMessage());
		}
		return jr;
	}

	@PutMapping("/")
	public JsonResponse updateWithRecalculatingPR(@RequestBody PurchaseRequestLineItem li) {
		JsonResponse jr = null;
		try {
			if (purchaseRequestLineItemRepo.existsById(li.getId())) {
				jr = JsonResponse.getInstance(purchaseRequestLineItemRepo.save(li));
				recalculatePRTotal(li);
			}
			else {
				jr=JsonResponse.getInstance("PurchaseRequest id:  "+li.getId()+"does not exist and you are attemping to modify it.");
			}
		} catch (Exception e) {
			jr = JsonResponse.getInstance("PRLI Update failed. Exception is " + e.getMessage());

		}
		return jr;
	}

	@DeleteMapping("/")
	public JsonResponse deleteWithRecalculating(@RequestBody PurchaseRequestLineItem li) {
		JsonResponse jr = null;
		try {
			if (purchaseRequestLineItemRepo.existsById(li.getId())) {
				purchaseRequestLineItemRepo.delete(li);
				jr = JsonResponse.getInstance("Purchase Request Line Item deleted.");
				recalculatePRTotal(li);

			} else {
				jr = JsonResponse.getInstance("PurchaseRequestLineItem id:  " + li.getId()
				+ "does not exist and you are attemping to save it.");

			}
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}

	private void recalculatePRTotal(PurchaseRequestLineItem li) {
		PurchaseRequest pr = li.getPurchaseRequest();
		List<PurchaseRequestLineItem> prliList = purchaseRequestLineItemRepo.findByPurchaseRequest(pr);
		double total = 0;
		for (int i = 0; i < prliList.size(); i++) {
			total = total + prliList.get(i).getQuantity() * prliList.get(i).getProduct().getPrice();
		}
		pr.setTotal(total);
		purchaseRequestRepo.save(pr);
	}

}
