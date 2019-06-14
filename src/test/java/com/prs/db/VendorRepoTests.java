package com.prs.db;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import com.prs.business.Vendor;
import com.prs.db.VendorRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class VendorRepoTests {

	@Autowired
	private VendorRepository vendorRepo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void findByVendorNameShouldReturnVendor() {
	this.entityManager.persist(new Vendor ("B9-1201", "name", "100 Best Buy Stree Wayt", "Louisville", "KY", 34555, "502-111-9099", "geeksquad@bestbuy.com", true));
	Optional<Vendor> u = this.vendorRepo.findByName("name");
	assertThat(u.get().getName()).isEqualTo("name");

	}
}