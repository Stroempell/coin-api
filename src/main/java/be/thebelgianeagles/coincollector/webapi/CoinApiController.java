package be.thebelgianeagles.coincollector.webapi;

import be.thebelgianeagles.coincollector.domain.COUNTRY;
import be.thebelgianeagles.coincollector.domain.Coin;
import be.thebelgianeagles.coincollector.repository.CoinRepository;
import be.thebelgianeagles.coincollector.webapi.dto.CoinDto;
import be.thebelgianeagles.coincollector.webapi.mapper.CoinMapper;
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
@RequestMapping("/api/coins")
@AllArgsConstructor
public class CoinApiController {
    private final CoinRepository coinRepository;

    private final CoinMapper coinMapper;

    @GetMapping("")
    public ResponseEntity<List<CoinDto>> getAllCoins() {
        List<CoinDto> coins = coinRepository.findAll()
                .stream()
                .map(coinMapper::toCoinDto)
                .collect(Collectors.toList());
        if (coins.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(coins);
    }

    @GetMapping("/{country}")
    public ResponseEntity<List<CoinDto>> getCoinsByCountry(@PathVariable COUNTRY country) {
        List<CoinDto> coins = coinRepository.findAllByCountry(country)
                .stream()
                .map(coinMapper::toCoinDto)
                .collect(Collectors.toList());

        if (coins.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(coins);
    }

}
