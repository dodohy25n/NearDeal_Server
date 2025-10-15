package hello.neardeal_server.store.repository;

import hello.neardeal_server.member.entity.PartnerCategory;
import hello.neardeal_server.store.entity.PartnerStore;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerStoreRepository extends JpaRepository<PartnerStore, Long> {

    Page<PartnerStore> findByPartnerCategory(PartnerCategory partnerCategory, PageRequest of);
}
