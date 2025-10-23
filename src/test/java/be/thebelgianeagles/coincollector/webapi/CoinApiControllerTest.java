package be.thebelgianeagles.coincollector.webapi;

import be.thebelgianeagles.coincollector.TestUtils;
import be.thebelgianeagles.coincollector.domain.COUNTRY;
import be.thebelgianeagles.coincollector.domain.Coin;
import be.thebelgianeagles.coincollector.repository.CoinRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class CoinApiControllerTest {
    @Autowired
    CoinRepository sut;

    @Autowired
    TestUtils testUtils;

    @Test
    void shouldReturnAllCoins() {
        //Arrange
        List<Coin> coins = new ArrayList<>();
        coins.add(testUtils.createCoin("Albert", COUNTRY.BELGIUM, 2005));
        coins.add(testUtils.createCoin("Filip", COUNTRY.BELGIUM, 2016));
        coins.add(testUtils.createCoin("Flowers", COUNTRY.FINLAND, 1999));

        //Act
        List<Coin> foundCoins = sut.findAll();

        //Assert
        assertThat(coins)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(foundCoins);
    }

    @Test
    void shouldReturnAllCoinsByCountry() {
        //Arrange
        List<Coin> coins = new ArrayList<>();
        coins.add(testUtils.createCoin("Albert", COUNTRY.BELGIUM, 2005));
        coins.add(testUtils.createCoin("Filip", COUNTRY.BELGIUM, 2016));
        testUtils.createCoin("Flowers", COUNTRY.FINLAND, 1999);

        //Act
        List<Coin> foundCoins = sut.findAllByCountry(COUNTRY.BELGIUM);

        //Assert
        assertEquals(foundCoins.size(), 2);
        assertThat(coins)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(foundCoins);
    }

    @BeforeEach
    void cleanUp() {
        testUtils.cleanUp();
    }
}
