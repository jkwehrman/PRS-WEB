//package com.prs.db;
//
//import java.sql.Date;
//import java.util.Optional;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.context.junit4.SpringRunner;
//import static org.assertj.core.api.Assertions.assertThat;
//
//import com.prs.business.PurchaseRequest;
//import com.prs.business.User;
//import com.prs.db.UserRepository;
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//@AutoConfigureTestDatabase(replace=Replace.NONE)
//public class PurchaseRequestRepoTests {
//
//	@Autowired
//	private PurchaseRequestRepository PurchaseRequestRepo;
//	
//	@Autowired
//	private TestEntityManager entityManager;
//	
//	@Test
//	public void findByUserIDShouldReturnPurchaseRequest() {
//		Date mDate = new Date(2019, 4, 11);
////		mDate.setYear(2019);
////		mDate.setMonth(4);
////		mDate.getDay(11);
//	this.entityManager.persist(new PurchaseRequest (2, "description", "justification", mDate, "deliveryMode", "status", 10.00, mDate, "reject"));
//	Optional<PurchaseRequest> u = this.PurchaseRequestRepo.findByUserID(2);
//	assertThat(u.get().getUser()).isEqualTo(2);
//
//	}
//}