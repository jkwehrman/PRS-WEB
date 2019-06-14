package com.prs.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.prs.business.JsonResponse;
import com.prs.business.PurchaseRequestLineItem;
import com.prs.db.PurchaseRequestLineItemRepository;
import com.prs.web.PurchaseRequestLineItemController;



@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PurchaseRequestLineItemControllerTests {


	@Autowired
	private PurchaseRequestLineItemController PurchaseRequestLineItemCont;

	// Testing getAll method to return list of users with data and not null.
	@Test
	public void testPurchaseRequestLineItemGetAll() {
		JsonResponse jr = null;
		jr = PurchaseRequestLineItemCont.getAll();
		assertNotNull(jr);
	}
}