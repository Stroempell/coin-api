package be.thebelgianeagles.coincollector.webapi.mapper;

import be.thebelgianeagles.coincollector.domain.Country;
import be.thebelgianeagles.coincollector.domain.Coin;
import be.thebelgianeagles.coincollector.webapi.dto.CoinDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;


@Mapper(componentModel = SPRING, uses = {CountryMapper.class} )
public interface CoinMapper {

    default CoinDto toCoinDto(Coin coin) {
        if (coin == null) return null;
        return new CoinDto(
                coin.getId(),
                coin.getName(),
                coin.getCountry() != null ? Arrays.stream(coin.getCountry().getName().toString().split("_"))
                        .map(w -> w.charAt(0) + w.substring(1).toLowerCase())
                        .collect(Collectors.joining(" ")) : null,
                coin.getYear(),
                coin.getUrl()
        );
    }



}
