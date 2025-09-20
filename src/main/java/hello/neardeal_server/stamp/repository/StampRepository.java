package hello.neardeal_server.stamp.repository;

import hello.neardeal_server.stamp.entity.Stamp;
import hello.neardeal_server.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StampRepository extends JpaRepository<Stamp, Long> {
    Page<Stamp> findByStore(Store store, Pageable pageable);
}
