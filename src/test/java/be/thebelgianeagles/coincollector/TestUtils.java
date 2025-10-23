package be.thebelgianeagles.coincollector;

import be.thebelgianeagles.coincollector.domain.COUNTRY;
import be.thebelgianeagles.coincollector.domain.Coin;
import be.thebelgianeagles.coincollector.repository.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestUtils {
    @Autowired
    private CoinRepository coinRepository;

    public void cleanUp() {
        coinRepository.deleteAll();
    }

    public Coin createCoin(String name, COUNTRY country, int year) {
        Coin coin = new Coin(name, country, year);
        return coinRepository.save(coin);
    }
}
