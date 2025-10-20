package hello.neardeal_server.randomstore.repository;

import hello.neardeal_server.store.entity.PartnerStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RandomStoreRepository extends JpaRepository<PartnerStore, Long> {

    @Query(value = "SELECT * FROM partner_store ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<PartnerStore> pickRandom();

    @Query(value = "SELECT * FROM partner_store WHERE category = ?1 ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<PartnerStore> pickRandomByCategory(String category);
}
