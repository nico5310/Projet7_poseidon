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
public class BidTest {

    @Autowired
    private BidListRepository bidListRepository;

    	@Test
    	public void bidListTest() {
			BidList bidList = new BidList();
			bidList.setAccount("caisse");
			bidList.setType("action");
			bidList.setBidQuantity(10.00);
			bidListRepository.save(bidList);

    		// Save
    		bidList = bidListRepository.save(bidList);
    		Assert.assertNotNull(bidList.getBidListId());
    		Assert.assertEquals(bidList.getBidQuantity(), 10.00, 10.00);

    		// Update
    		bidList.setBidQuantity(20d);
    		bidList = bidListRepository.save(bidList);
    		Assert.assertEquals(bidList.getBidQuantity(), 20.00, 20.00);

    		// Find
    		List<BidList> listResult = bidListRepository.findAll();
    		Assert.assertTrue(listResult.size() > 0);

    		// Delete
    		Integer id = bidList.getBidListId();
    		bidListRepository.delete(bidList);
    		Optional<BidList> bidList2 = bidListRepository.findById(id);
    		Assert.assertFalse(bidList2.isPresent());
    	}
}