package com.nnk.springboot;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BidTests {

    @Autowired
    private BidListRepository bidListRepository;

    	@Test
    	public void bidListTest() {
    		BidList bid = new BidList("Account Test", "Type Test", 10.00);

    		// Save
    		bid = bidListRepository.save(bid);
    		Assert.assertNotNull(bid.getBidListId());
    		Assert.assertEquals(bid.getBidQuantity(), 10.00, 10.00);

    		// Update
    		bid.setBidQuantity(20d);
    		bid = bidListRepository.save(bid);
    		Assert.assertEquals(bid.getBidQuantity(), 20.00, 20.00);

    		// Find
    		List<BidList> listResult = bidListRepository.findAll();
    		Assert.assertTrue(listResult.size() > 0);

    		// Delete
    		Integer id = bid.getBidListId();
    		bidListRepository.delete(bid);
    		Optional<BidList> bidList = bidListRepository.findById(id);
    		Assert.assertFalse(bidList.isPresent());
    	}
}