package plalab.jpa.study01;

import plalab.jpa.study01.domain.Member;

public class Study01TestUtil {

    public static Member getDummyMember(String name) {
        Member member = new Member();
        member.setName(name);
        member.setCity("city");
        member.setStreet("street");
        member.setZipcode("123456");
        return member;
    }
}
