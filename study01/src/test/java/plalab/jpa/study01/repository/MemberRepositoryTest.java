package plalab.jpa.study01.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import plalab.jpa.study01.Study01Application;
import plalab.jpa.study01.domain.Member;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Study01Application.class)
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void save(){
        Member member = new Member();
        member.setName("pizza");
        memberRepository.save(member);
    }
}
