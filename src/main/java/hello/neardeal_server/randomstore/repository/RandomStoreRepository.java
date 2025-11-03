package hello.neardeal_server.randomstore.repository;

import hello.neardeal_server.store.entity.PartnerStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RandomStoreRepository extends JpaRepository<PartnerStore, Long> {

    @Query(value = """
        SELECT partner_store_id, store_name, partner_benefit
        FROM partner_store
        ORDER BY RAND() LIMIT 1
        """, nativeQuery = true)
    Object[] pickRandomRaw();

    @Query(value = """
        SELECT partner_store_id, store_name, partner_benefit
        FROM partner_store
        WHERE category = ?1
        ORDER BY RAND() LIMIT 1
        """, nativeQuery = true)
    Object[] pickRandomRawByCategory(String category);
}

//테스트용 주석