package plalab.jpa.study02.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import plalab.jpa.study02.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
