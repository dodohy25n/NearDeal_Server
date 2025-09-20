package hello.neardeal_server.member.repository;

import hello.neardeal_server.member.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
