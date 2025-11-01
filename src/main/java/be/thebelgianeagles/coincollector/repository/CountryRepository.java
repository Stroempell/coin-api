package be.thebelgianeagles.coincollector.repository;

import be.thebelgianeagles.coincollector.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
}
