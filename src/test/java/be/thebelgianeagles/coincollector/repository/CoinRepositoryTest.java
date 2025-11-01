package be.thebelgianeagles.coincollector.repository;

import be.thebelgianeagles.coincollector.TestUtils;
import be.thebelgianeagles.coincollector.domain.COUNTRY_NAME;
import be.thebelgianeagles.coincollector.domain.Country;
import be.thebelgianeagles.coincollector.domain.Coin;
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
public class CoinRepositoryTest {
    @Autowired
    CoinRepository sut;

    @Autowired
    TestUtils testUtils;

    @Test
    void shouldReturnAllCoins() {
        //Arrange
        Country belgium = testUtils.createCountry(COUNTRY_NAME.BELGIUM, "urlC1");
        Country finland = testUtils.createCountry(COUNTRY_NAME.FINLAND, "urlC1");


        List<Coin> coins = new ArrayList<>();
        coins.add(testUtils.createCoin("Albert", belgium, 2005, "url1"));
        coins.add(testUtils.createCoin("Filip", belgium, 2016, "url2"));
        coins.add(testUtils.createCoin("Flowers", finland, 1999, "url3"));

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
        Country belgium = testUtils.createCountry(COUNTRY_NAME.BELGIUM, "urlC1");
        Country finland = testUtils.createCountry(COUNTRY_NAME.FINLAND, "urlC1");

        List<Coin> coins = new ArrayList<>();
        coins.add(testUtils.createCoin("Albert", belgium, 2005, "url1"));
        coins.add(testUtils.createCoin("Filip", belgium, 2016, "url2"));
        testUtils.createCoin("Flowers", finland, 1999, "url3");

        //Act
        List<Coin> foundCoins = sut.findAllByCountry(belgium);

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
