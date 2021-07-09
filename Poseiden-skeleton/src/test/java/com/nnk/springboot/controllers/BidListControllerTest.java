package com.nnk.springboot.controllers;

import com.nnk.springboot.service.BidListService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {BidListController.class})
@ExtendWith(SpringExtension.class)
public class BidListControllerTest {

    @Autowired
    private BidListController bidListController;

    @MockBean
    private BidListService bidListService;

    @Test
    public void testValidate() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/bidList/validate");
        MockMvcBuilders.standaloneSetup(this.bidListController)
                       .build()
                       .perform(requestBuilder)
                       .andExpect(MockMvcResultMatchers.status().isOk())
                       .andExpect(MockMvcResultMatchers.model().<Object>size(1))
                       .andExpect(MockMvcResultMatchers.model().attributeExists("bidList"))
                       .andExpect(MockMvcResultMatchers.view().name("bidList/add"))
                       .andExpect(MockMvcResultMatchers.forwardedUrl("bidList/add"));
    }
}

