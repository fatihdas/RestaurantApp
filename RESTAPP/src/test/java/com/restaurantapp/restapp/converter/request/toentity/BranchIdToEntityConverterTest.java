package com.restaurantapp.restapp.converter.request.toentity;

import com.restaurantapp.restapp.model.converter.create.request.BranchIdToEntityConverter;
import com.restaurantapp.restapp.model.dto.BranchDto;
import com.restaurantapp.restapp.model.entity.Branch;
import com.restaurantapp.restapp.model.entity.Menu;
import com.restaurantapp.restapp.service.impl.BranchServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BranchIdToEntityConverterTest {

    @Spy
    @InjectMocks
    private BranchIdToEntityConverter branchIdToEntityConverter;

    @Mock
    private BranchServiceImpl branchService;

    @Test
    public void testConvert() {

        Branch branchExpected = this.generateBranch();

        Mockito.when(branchService.getBranchByid(Mockito.anyLong())).thenReturn(new Branch());

        Branch branchActual = branchIdToEntityConverter.convert(Mockito.anyLong());

        Assertions.assertEquals(branchExpected, branchActual);
    }

    private Branch generateBranch() {
        return Branch.builder()
                .name("etilerşubesi")
                .menu(Menu.builder().build())
                .build();
    }

}