package com.prs.db;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.prs.business.PurchaseRequest;
import com.prs.business.User;

public interface PurchaseRequestRepository extends CrudRepository <PurchaseRequest, Integer> {

	public Optional<PurchaseRequest> findByUser(User ID);

	public Optional<PurchaseRequest> findByStatus(String string);

	public List<PurchaseRequest> findByStatusAndUserNot(String status, User user);

	public Object save(int i);



} 