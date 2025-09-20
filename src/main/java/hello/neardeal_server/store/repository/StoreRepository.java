package hello.neardeal_server.store.repository;

import hello.neardeal_server.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
