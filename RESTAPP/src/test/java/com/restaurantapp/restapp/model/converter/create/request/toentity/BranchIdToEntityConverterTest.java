package com.restaurantapp.restapp.model.converter.create.request.toentity;

import com.restaurantapp.restapp.model.converter.dto.toentity.BranchDtoToEntityConverter;
import com.restaurantapp.restapp.model.dto.BranchDto;
import com.restaurantapp.restapp.model.entity.Branch;
import com.restaurantapp.restapp.model.entity.Menu;
import com.restaurantapp.restapp.service.impl.BranchServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BranchIdToEntityConverterTest {

    @Mock
    private BranchDtoToEntityConverter branchDtoToEntityConverter;

    @Mock
    private BranchServiceImpl branchService;

    @Test
    public void testConvert() {

        Branch branch = generateBranch();

        Mockito.when(branchDtoToEntityConverter.convert(Mockito.any(BranchDto.class))).thenReturn(branch);
        Mockito.when(branchService.getBranch(Mockito.anyLong())).thenReturn(branch);

        Branch branch1 = branchDtoToEntityConverter.convert(branchService.getBranch(Mockito.anyLong()));

        Assertions.assertEquals(branch, branch1);
    }

    private Branch generateBranch() {
        return Branch.builder()
                .name("etilerşubesi")
                .menu(Menu.builder().build())
                .build();
    }

}