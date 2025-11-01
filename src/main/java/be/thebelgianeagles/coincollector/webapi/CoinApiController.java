package be.thebelgianeagles.coincollector.webapi;

import be.thebelgianeagles.coincollector.domain.Country;
import be.thebelgianeagles.coincollector.repository.CoinRepository;
import be.thebelgianeagles.coincollector.repository.CountryRepository;
import be.thebelgianeagles.coincollector.webapi.dto.CoinDto;
import be.thebelgianeagles.coincollector.webapi.dto.CountryDto;
import be.thebelgianeagles.coincollector.webapi.mapper.CoinMapper;
import be.thebelgianeagles.coincollector.webapi.mapper.CountryMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/")
@AllArgsConstructor
public class CoinApiController {
    private final CoinRepository coinRepository;
    private final CountryRepository countryRepository;

    private final CoinMapper coinMapper;
    private final CountryMapper countryMapper;

    @GetMapping("coins")
    public ResponseEntity<List<CoinDto>> getAllCoins() {
        List<CoinDto> coins = coinRepository.findAll()
                .stream()
                .map(coinMapper::toCoinDto)
                .collect(Collectors.toList());
        if (coins.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        System.out.println("Sending all coins");

        return ResponseEntity.ok(coins);
    }

    @GetMapping("coins/{country}")
    public ResponseEntity<List<CoinDto>> getCoinsByCountry(@PathVariable Country country) {
        List<CoinDto> coins = coinRepository.findAllByCountry(country)
                .stream()
                .map(coinMapper::toCoinDto)
                .collect(Collectors.toList());

        if (coins.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        System.out.println("Sending coins by country");
        return ResponseEntity.ok(coins);
    }

    @GetMapping("countries")
    public ResponseEntity<List<CountryDto>> getCoinsByCountry() {
        List<CountryDto> countries = countryRepository.findAll()
                .stream()
                .map(countryMapper::toCountryDto)
                .collect(Collectors.toList());
        if (countries.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(countries);
    }

}
