package hello.neardeal_server.item.repository;

import hello.neardeal_server.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
