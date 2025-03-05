package common.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import business.reservation.ReservationDTO;
import common.dto.reservation.UpdatePriceReservationDTO;
import common.dto.reservation.UpdateReservationDTO;
import common.dto.soap.response.UpdateReservationSOAP;

@Mapper
public interface ReservationMapper {
    ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);
    
    @Mapping(target = "updatePrice", source = "updatePrice")
    @Mapping(target = "updatePriceReservationDTO", source = "listPricesWithIdFlightInstance")
    UpdateReservationDTO toUpdateRevervationDTO(ReservationDTO reservationDTO, double updatePrice, List<UpdatePriceReservationDTO> listPricesWithIdFlightInstance);

    @Mapping(target = "updatePriceReservationSOAP", source = "updatePriceReservationDTO")
    UpdateReservationSOAP toUpdateReservationSOAP(UpdateReservationDTO u);
}
