//package com.prs.db;
//
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
//import com.prs.business.Product;
//import com.prs.business.User;
//import com.prs.db.ProductRepository;
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//@AutoConfigureTestDatabase(replace=Replace.NONE)
//public class ProductRepoTests {
//	
//	@Autowired
//	private ProductRepository productRepo;
//	
//	@Autowired
//	private TestEntityManager entityManager;
//	
//	@Test
//	public void findByPartNumberShouldReturnProduct() {
//
//	this.entityManager.persist(new Product(3, "pn", "name", 10.00, "unit", "pp"));
//	Optional<Product> u = this.productRepo.findByPartNumber("pn");
//	assertThat(u.get().getPartNumber()).isEqualTo("pn");
//
//	}
//}