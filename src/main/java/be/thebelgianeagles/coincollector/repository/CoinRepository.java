package be.thebelgianeagles.coincollector.repository;

import be.thebelgianeagles.coincollector.domain.COUNTRY;
import be.thebelgianeagles.coincollector.domain.Coin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoinRepository extends JpaRepository<Coin, Integer> {
    List<Coin> findAllByCountry(COUNTRY country);
}
