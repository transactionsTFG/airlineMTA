package common.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import common.dto.flight.IdFlightInstanceWithSeatsDTO;
import common.dto.soap.request.IdFlightInstanceWithSeatsSOAP;

@Mapper
public interface IdFlightWithSeatsMapper {
    IdFlightWithSeatsMapper INSTANCE = Mappers.getMapper(IdFlightWithSeatsMapper.class);
    IdFlightInstanceWithSeatsDTO toDTO(IdFlightInstanceWithSeatsSOAP idFlightInstanceWithSeatsSOAP);
}
