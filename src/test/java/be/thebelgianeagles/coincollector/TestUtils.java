package be.thebelgianeagles.coincollector;

import be.thebelgianeagles.coincollector.domain.COUNTRY_NAME;
import be.thebelgianeagles.coincollector.domain.Country;
import be.thebelgianeagles.coincollector.domain.Coin;
import be.thebelgianeagles.coincollector.repository.CoinRepository;
import be.thebelgianeagles.coincollector.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestUtils {
    @Autowired
    private CoinRepository coinRepository;
    @Autowired
    private CountryRepository countryRepository;

    public void cleanUp() {
        coinRepository.deleteAll();
        countryRepository.deleteAll();
    }

    public Coin createCoin(String name, Country country, int year, String url) {
        Coin coin = new Coin(name, country, year, url);
        return coinRepository.save(coin);
    }

    public Country createCountry(COUNTRY_NAME name, String url) {
        Country country = new Country(name, url);
        return countryRepository.save(country);
    }
}
