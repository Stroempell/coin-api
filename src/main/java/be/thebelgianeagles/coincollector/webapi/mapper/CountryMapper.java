package be.thebelgianeagles.coincollector.webapi.mapper;

import be.thebelgianeagles.coincollector.domain.COUNTRY_NAME;
import be.thebelgianeagles.coincollector.domain.Country;
import be.thebelgianeagles.coincollector.webapi.dto.CountryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface CountryMapper {

    @Mapping(source = "name", target = "name", qualifiedByName = "enumToDisplay")
    CountryDto toCountryDto(Country country);


    @Named("enumToDisplay")
    default String enumToDisplay(COUNTRY_NAME country) {
        if (country == null) return null;
        return Arrays.stream(country.toString().split("_"))
                .map(w -> w.charAt(0) + w.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
    }
}
