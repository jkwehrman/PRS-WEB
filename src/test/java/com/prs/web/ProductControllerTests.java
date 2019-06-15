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
import com.prs.business.Product;
import com.prs.web.ProductController;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ProductControllerTests {

	@Autowired
	private ProductController productCont;

	// Testing getAll method to return list of users with data and not null.
	@Test
	public void testProductGetAll() {
		JsonResponse jr = null;
		jr = productCont.getAll();
		assertNotNull(jr);
	}
}