package hello.neardeal_server.member.repository;

import hello.neardeal_server.member.entity.TempMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TempMemberRepository extends JpaRepository<TempMember, Long> {
}
