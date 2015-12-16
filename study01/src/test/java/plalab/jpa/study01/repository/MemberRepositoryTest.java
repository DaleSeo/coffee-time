package plalab.jpa.study01.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import plalab.jpa.study01.Study01Application;
import plalab.jpa.study01.Study01TestUtil;
import plalab.jpa.study01.domain.Member;

import javax.transaction.Transactional;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Study01Application.class)
@Transactional
@Rollback(true)
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Before
    public void setup() {
        assertNotNull(memberRepository);
    }

    @Test
    public void saveDummyMember() {
        // Given
        String name = "pizza";
        Member member = Study01TestUtil.getDummyMember(name);

        // When
        Member saveMember = memberRepository.save(member);
        Member findMember = memberRepository.findOne(saveMember.getId());

        // Then
        assertEquals(name, findMember.getName());
    }

    @Test
    public void findAll() {
        // Given
        memberRepository.save(Study01TestUtil.getDummyMember("pizza01"));
        memberRepository.save(Study01TestUtil.getDummyMember("pizza02"));
        memberRepository.save(Study01TestUtil.getDummyMember("pizza03"));

        // When
        List<Member> members = memberRepository.findAll();

        // Then
        assertTrue(members.size() == 3);
    }

    @Test
    public void deleteAll() {
        // Given
        memberRepository.save(Study01TestUtil.getDummyMember("pizza01"));
        memberRepository.save(Study01TestUtil.getDummyMember("pizza02"));
        memberRepository.save(Study01TestUtil.getDummyMember("pizza03"));

        // When
        memberRepository.deleteAll();
        List<Member> members = memberRepository.findAll();

        // Then
        assertTrue(members.size() == 0);
    }

    @Test
    public void deleteOne() {
        // Given
        memberRepository.save(Study01TestUtil.getDummyMember("pizza01"));
        memberRepository.save(Study01TestUtil.getDummyMember("pizza02"));
        Member pizza03 = memberRepository.save(Study01TestUtil.getDummyMember("pizza03"));

        // When
        memberRepository.delete(pizza03);
        List<Member> members = memberRepository.findAll();

        // Then
        assertTrue(members.size() == 2);
    }

}
