package plalab.jpa.study01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import plalab.jpa.study01.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
