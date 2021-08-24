//package com.restaurantapp.restapp.model.converter.dto.toentity;
//
//import com.restaurantapp.restapp.model.dto.BranchDto;
//import com.restaurantapp.restapp.model.entity.Branch;
//import org.springframework.stereotype.Component;
//
//@Component
//public class BranchDtoToEntityConverter {
//
//    private final MenuDtoToEntityConverter menuDtoToEntityConverter;
//    private final AddressDtoToEntityConverter addressDtoToEntityConverter;
//
//    public BranchDtoToEntityConverter(MenuDtoToEntityConverter menuDtoToEntityConverter,
//                                      AddressDtoToEntityConverter addressDtoToEntityConverter) {
//        this.menuDtoToEntityConverter = menuDtoToEntityConverter;
//        this.addressDtoToEntityConverter = addressDtoToEntityConverter;
//    }
//
//    public Branch convert(BranchDto branchDto) {
//
//        return Branch.builder()
//                .id(branchDto.getId())
//                .name(branchDto.getName())
//                .menu(menuDtoToEntityConverter.convert(branchDto.getMenuDto()))
//                .status(branchDto.getStatus())
//                .address(addressDtoToEntityConverter.convert(branchDto.getAddressDto()))
//                .build();
//    }
//}
