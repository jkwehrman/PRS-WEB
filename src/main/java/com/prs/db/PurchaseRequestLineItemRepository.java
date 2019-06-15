package com.prs.db;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

import com.prs.business.PurchaseRequest;
import com.prs.business.PurchaseRequestLineItem;

public interface PurchaseRequestLineItemRepository extends CrudRepository<PurchaseRequestLineItem, Integer> {

	List<PurchaseRequestLineItem> findByPurchaseRequest(PurchaseRequest purchaseRequest);



} 

