package be.thebelgianeagles.coincollector.repository;

import be.thebelgianeagles.coincollector.TestUtils;
import be.thebelgianeagles.coincollector.domain.COUNTRY_NAME;
import be.thebelgianeagles.coincollector.domain.Country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class CountryRepositoryTest {
    @Autowired
    CountryRepository sut;

    @Autowired
    TestUtils testUtils;

    @BeforeEach
    void cleanUp() {
        testUtils.cleanUp();
    }

    @Test
    void shouldReturnAllCountries() {
        //Arrange
        List<Country> countries = List.of(
                testUtils.createCountry(COUNTRY_NAME.BELGIUM, "url1"),
                testUtils.createCountry(COUNTRY_NAME.FINLAND, "url2")
        );

        //Act
        List<Country> foundCountries = sut.findAll();

        //Assert
        assertThat(foundCountries)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(countries);
    }
}
