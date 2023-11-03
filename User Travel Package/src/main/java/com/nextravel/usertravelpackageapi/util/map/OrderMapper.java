package com.nextravel.usertravelpackageapi.util.map;




import com.nextravel.usertravelpackageapi.dto.UserTravelPackageDto;
import com.nextravel.usertravelpackageapi.entity.UserTravelPackage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
     UserTravelPackageDto toOrderDto(UserTravelPackage userTravelPackage);
     UserTravelPackage toOrder(UserTravelPackageDto userTravelPackageDto);
}
