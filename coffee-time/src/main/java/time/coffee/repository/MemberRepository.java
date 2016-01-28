package time.coffee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import time.coffee.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
