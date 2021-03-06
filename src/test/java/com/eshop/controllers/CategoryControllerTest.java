package com.eshop.controllers;

import com.eshop.model.Category;
import com.eshop.model.Item;
import com.eshop.services.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CategoryControllerTest {

    @InjectMocks
    private CategoryController categoryController;

    @Mock
    private CategoryService categoryService;

    @Test
    public void shouldGetAllItemsInACategoryGivenCategoryId(){
        List<Item> mockItems = Arrays.asList(
                new Item("LG Nexus 5X", new Category(), "sdf", 12, 20, "nexus.png"),
                new Item("MacBookPro", new Category(), "sdf", 12, 20, "macbook.png")
        );
        when(categoryService.getItemsByCategoryId(1l)).thenReturn(mockItems);
        List<Item> items = categoryController.getAllItemsByCategory(1l);
        verify(categoryService).getItemsByCategoryId(1l);
        assertEquals(mockItems, items);
    }
}
