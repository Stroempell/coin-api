package be.thebelgianeagles.coincollector.webapi.mapper;

import be.thebelgianeagles.coincollector.domain.COUNTRY;
import be.thebelgianeagles.coincollector.domain.Coin;
import be.thebelgianeagles.coincollector.webapi.dto.CoinDto;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;


@Mapper(componentModel = SPRING)
public interface CoinMapper {

    @Mapping(source = "country", target = "country", qualifiedByName = "enumToDisplay")
    CoinDto toCoinDto(Coin coin);

    @Named("enumToDisplay")
    default String enumToDisplay(COUNTRY country) {
        if (country == null) return null;
        System.out.println("converting country");
        return Arrays.stream(country.name().split("_"))
                .map(w -> w.charAt(0) + w.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
    }
}
