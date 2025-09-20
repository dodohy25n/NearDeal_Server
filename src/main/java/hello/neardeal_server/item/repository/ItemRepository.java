package hello.neardeal_server.item.repository;

import hello.neardeal_server.item.entity.Item;
import hello.neardeal_server.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Page<Item> findByStore(Store store, Pageable pageable);
}
