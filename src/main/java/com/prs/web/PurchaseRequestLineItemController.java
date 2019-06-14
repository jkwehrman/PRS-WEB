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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prs.business.JsonResponse;
import com.prs.business.Product;
import com.prs.business.PurchaseRequest;
import com.prs.business.PurchaseRequestLineItem;
import com.prs.business.User;
import com.prs.db.PurchaseRequestLineItemRepository;
import com.prs.db.PurchaseRequestRepository;
import com.prs.db.ProductRepository;

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
			Optional<PurchaseRequestLineItem> u = purchaseRequestLineItemRepo.findById(id);
			if (u.isPresent())
				jr = JsonResponse.getInstance(u);
			else
				jr = JsonResponse.getInstance("No purchase request line item found for id: " + id);
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}

//	@PostMapping("/")
//	public JsonResponse add(@RequestBody PurchaseRequestLineItem u) {
//		JsonResponse jr = null;
//		try {
//			jr=JsonResponse.getInstance(purchaseRequestLineItemRepo.save(u));
//		}
//		catch (Exception e ) {
//			jr=JsonResponse.getInstance("PRLI Add failed. Exception is " + e.getMessage());		}
//		return jr;
//	}

	@PostMapping("/")
	public JsonResponse addWithRecalculating(@RequestBody PurchaseRequestLineItem u) {
		JsonResponse jr = null;
		try {
			jr = JsonResponse.getInstance(purchaseRequestLineItemRepo.save(u));
			recalculatePRTotal(u);
		} catch (Exception e) {
			jr = JsonResponse.getInstance("PRLI Add failed. Exception is " + e.getMessage());
		}
		return jr;
	}

//	@PutMapping("/")
//	public JsonResponse update(@RequestBody PurchaseRequestLineItem u) {
//		JsonResponse jr = null;
//		try {
//				jr=JsonResponse.getInstance(purchaseRequestLineItemRepo.save(u));
//				//recalculatePRTotal(u);
//		}
//		catch (Exception e ) {
//			jr=JsonResponse.getInstance("PRLI Update failed. Exception is " + e.getMessage());
//
//		}
//		return jr;
//	}

	@PutMapping("/")
	public JsonResponse updateWithRecalculatingPR(@RequestBody PurchaseRequestLineItem u) {
		JsonResponse jr = null;
		try {
			jr = JsonResponse.getInstance(purchaseRequestLineItemRepo.save(u));
			recalculatePRTotal(u);
		} catch (Exception e) {
			jr = JsonResponse.getInstance("PRLI Update failed. Exception is " + e.getMessage());

		}
		return jr;
	}

	@DeleteMapping("/")
	public JsonResponse delete(@RequestBody PurchaseRequestLineItem u) {
		JsonResponse jr = null;
		// May need to enhance exception handling if more than one exception type needs
		// to be caught
		try {
			if (purchaseRequestLineItemRepo.existsById(u.getId())) {
				purchaseRequestLineItemRepo.delete(u);
				jr = JsonResponse.getInstance("Purchase Request Line Item deleted.");
			} else {
				jr = JsonResponse.getInstance("PurchaseRequestLineItem id:  " + u.getId()
						+ "does not exist and you are attemping to save it.");
			}
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}

	@DeleteMapping("/purchase-request-line-items/")
	public JsonResponse deleteWithRecalculating(@RequestBody PurchaseRequestLineItem u) {
		JsonResponse jr = null;
		try {
			if (purchaseRequestLineItemRepo.existsById(u.getId())) {
				purchaseRequestLineItemRepo.delete(u);
				jr = JsonResponse.getInstance("Purchase Request Line Item deleted.");
				recalculatePRTotal(u);

			} else {
				jr = JsonResponse.getInstance("PurchaseRequestLineItem id:  " + u.getId()
						+ "does not exist and you are attemping to save it.");

			}
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}

	@GetMapping("/lines-for-pr/{id}")
	public JsonResponse getByPRLI(@PathVariable int id) {
		JsonResponse jr = null;
		try {
			Optional<PurchaseRequestLineItem> oprli = purchaseRequestLineItemRepo.findById(id);
			PurchaseRequestLineItem prli = oprli.get();
			PurchaseRequest pr = prli.getPurchaseRequest();

			jr = JsonResponse.getInstance(purchaseRequestLineItemRepo.findByPurchaseRequest(pr));
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}

//	@PostMapping("/purchase-request-line-items")
//	public JsonResponse addPRLI(@RequestBody PurchaseRequestLineItem u) {
//		JsonResponse jr = null;
//		try {
//			
//			jr=JsonResponse.getInstance(purchaseRequestLineItemRepo.save(u));
//			PurchaseRequest pr = u.getPurchaseRequest();
//			int q = u.getQuantity();
//			Product p = u.getProduct();
//			double t = pr.getTotal();
//			double itemp = p.getPrice();			
//			Double newT = t+(q*itemp);  
//			pr.setTotal(newT);
//			purchaseRequestRepo.save(pr);
//			jr=JsonResponse.getInstance("Line Item was added and the total $" + newT + " was updated.");
//		}
//		catch (Exception e ) {
//			jr=JsonResponse.getInstance(e);
//		}
//		return jr;
//	}

//	@DeleteMapping("/purchase-request-line-items/{id}")
//	public JsonResponse deletePRLI(@RequestBody PurchaseRequestLineItem u) {
//		JsonResponse jr = null;
//		try {
//			// 1st step get parent purchase request 
//			PurchaseRequest pr = u.getPurchaseRequest();
//			double oldT = pr.getTotal();
//			// 3rd step get product
//			Product p = u.getProduct();
//			double itemP = p.getPrice();
//			// 5th step calculate new total
//			int q = u.getQuantity();
//			double newT = oldT - (q * itemP  );
//			// 6th step delete prli
//
//			purchaseRequestLineItemRepo.delete(u);
//			// 7th step update pr
//			pr.setTotal(newT);
//			purchaseRequestRepo.save(pr);
//			jr=JsonResponse.getInstance("Line Item was deleted and the total $" + newT + " was updated.");
//
//		}
//
//		catch (Exception e ) {
//			jr=JsonResponse.getInstance(e);
//		}
//		return jr;
//	}

//	@PutMapping("/purchase-request-line-items/{id}")
//	public JsonResponse changePRLIQ(@RequestBody PurchaseRequestLineItem u) {
//		JsonResponse jr = null;
//		try {
//			// step 5 get old PRLI
//			Optional<PurchaseRequestLineItem> optional_oldPRLI = purchaseRequestLineItemRepo.findById(u.getId());
//			PurchaseRequestLineItem oldPRLI = optional_oldPRLI.get();
//			// 1st step get PRLIQ 
//			int newQ = u.getQuantity();
//			// 2nd step get PRid
//			PurchaseRequest pr = u.getPurchaseRequest();
//			// 3rd step get total
//			double t = pr.getTotal();
//			// 4th step get product
//			Product p = u.getProduct();
//			double itemP = p.getPrice();
//			// 6th step setQuantity
//			purchaseRequestLineItemRepo.save(u);
//			// 7th step calculate new total
//			double newT = pr.getTotal() - (itemP * oldPRLI.getQuantity()) + (itemP * newQ);
//			pr.setTotal(newT);
//			purchaseRequestRepo.save(pr);
//			jr=JsonResponse.getInstance("The quantity of line Item was changed and the total $" + newT + " was updated.");
//
//		}
//
//		catch (Exception e ) {
//			jr=JsonResponse.getInstance(e);
//		}
//		return jr;
//	}

	private void recalculatePRTotal(PurchaseRequestLineItem u) {
		PurchaseRequest pr = u.getPurchaseRequest();
		List<PurchaseRequestLineItem> prliList = purchaseRequestLineItemRepo.findByPurchaseRequest(pr);
		double total = 0;
		for (int i = 0; i < prliList.size(); i++) {
			total = total + prliList.get(i).getQuantity() * prliList.get(i).getProduct().getPrice();
		}
		pr.setTotal(total);
		purchaseRequestRepo.save(pr);
	}

}
