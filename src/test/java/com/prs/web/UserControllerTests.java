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
import com.prs.business.User;
import com.prs.db.UserRepository;
import com.prs.web.UserController;



@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserControllerTests {

	@Autowired
	private UserController userCont;

	// Testing getAll method to return list of users with data and not null.
	@Test
	public void testUserGetAll() {
		JsonResponse jr = null;
		jr = userCont.getAll();
		assertNotNull(jr);
	}

//	// Testing user add to add attributes of jSon object to database, get id of new object
//	// getById from DB, then delete object by id
//	@Test
//	public void testUserAdd() {
//		User u = new User("ccreevy","cpassword","Colin","Creevy","123-993-3456","ccreevy@hogwarts.edu", true, true);
//		JsonResponse jr = null;
//		jr = userCont.add(u);
//		assertNotNull(jr);
//		//	id = u.getId(); - in booklet, but not in class
//		
//		JSONObject json = new JSONObject(jr.   .getJSONObject("user"));
//		
//		int id = json.getNumber("id");
//		User testU = userCont.getById(id)
//		assertEquals("username", u.getUserName());
//		assertEquals("pwd", u.getPassword());
//		assertEquals("fname", u.getFirstName());
//		assertEquals("lname", u.getLastName());
//		assertEquals("phone", u.getPhoneNumber());
//		assertEquals("email", u.getEmail());
//        ,
		// delete the user
		//userRepo.delete(u);
		// confirm user deletion by getting the user by ID
		// assertFalse(userRepo.findById(u.getId())).isPresent();

	}

