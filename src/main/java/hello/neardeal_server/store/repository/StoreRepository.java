package hello.neardeal_server.store.repository;

import hello.neardeal_server.store.StoreCategory;
import hello.neardeal_server.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {

    Page<Store> findByCategory(StoreCategory category, PageRequest of);

}
