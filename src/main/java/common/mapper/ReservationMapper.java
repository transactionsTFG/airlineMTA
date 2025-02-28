package common.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import business.reservation.ReservationDTO;
import common.dto.reservation.UpdateReservationDTO;
import common.dto.soap.response.UpdateReservationSOAP;

@Mapper
public interface ReservationMapper {
    ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);
    
    @Mapping(target = "updatePrice", source = "updatePrice")
    UpdateReservationDTO toUpdateRevervationDTO(ReservationDTO reservationDTO, double updatePrice);

    UpdateReservationSOAP toUpdateReservationSOAP(UpdateReservationDTO updateReservationDTO);
}
