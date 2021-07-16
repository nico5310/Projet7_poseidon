package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.ConcurrentModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {BidListService.class})
@ExtendWith(SpringExtension.class)
public class BidListServiceTest {

    @MockBean
    private BidListRepository bidListRepository;

    @Autowired
    private BidListService bidListService;

    @Test
    @DisplayName("HomeBidList test")
    public void homeBidListTest() {
        //GIVEN
        List<BidList> bidList = new ArrayList<>();
        //WHEN
        when(bidListRepository.findAll()).thenReturn(bidList);
        //THEN
        assertEquals("bidList/list", bidListService.home(new ConcurrentModel()));
        verify(bidListRepository).findAll();
    }

    @Test
    @DisplayName("Validate BidList test")
    public void validateBidListTest() {
        //GIVEN
        BidList bidList = new BidList();
        bidList.setBidListId(1);
        bidList.setAccount("Account");
        bidList.setType("Type");
        bidList.setBidQuantity(10.0);
        //WHEN
//        bidListService.validate(bidList, new ConcurrentModel());
        when(bidListRepository.save(bidList)).thenReturn(bidList);
        bidListService.validate(bidList);
        //THEN
        verify(bidListRepository).save(bidList);

    }

    @Test
    @DisplayName("ShowUpdate BidList test")
    public void showUpdateFormBidListTest() {
        //GIVEN
        BidList bidList = new BidList();
        bidList.setBidListId(1);
        bidList.setAccount("Account");
        bidList.setType("Type");
        bidList.setBidQuantity(10.0);
        //WHEN
        when(bidListRepository.findById(1)).thenReturn(Optional.of(bidList));
        bidListService.showUpdateForm(1, new ConcurrentModel());
        //THEN
        verify(bidListRepository).findById(1);
    }

    @Test
    @DisplayName("update BidList test")
    public void updateBidTest() {
        //GIVEN
        BidList bidList = new BidList();
        bidList.setBidListId(1);
        bidList.setAccount("Account");
        bidList.setType("Type");
        bidList.setBidQuantity(10.0);
        List<BidList> bidListList = new ArrayList<>();
        when(bidListRepository.findAll()).thenReturn(bidListList);
        when(bidListRepository.save(bidList)).thenReturn(bidList);
        BidList bidList1 = new BidList();
        bidList1.setBidListId(1);
        bidList1.setAccount("Account");
        bidList1.setType("Type");
        bidList1.setBidQuantity(20.0);
        bidListService.updateBid(1, bidList1, new ConcurrentModel());
        //WHEN

        //THEN
        verify(bidListRepository).findAll();
        verify(bidListRepository).save(bidList1);
        assertEquals(1, bidList1.getBidListId().intValue());
        assertEquals(20.0, bidList1.getBidQuantity().intValue());

    }

    @Test
    @DisplayName("Delete BidList test")
    public void deleteBidTest() {
        //GIVEN
        BidList bidList = new BidList();
        bidList.setBidListId(1);
        bidList.setAccount("Account");
        bidList.setType("Type");
        bidList.setBidQuantity(10.0);
        //WHEN
        bidListRepository.deleteById(1);
        //THEN
        verify(bidListRepository).deleteById(1);
    }

}

