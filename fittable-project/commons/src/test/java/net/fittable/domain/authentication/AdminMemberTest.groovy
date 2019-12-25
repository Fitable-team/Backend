package net.fittable.domain.authentication

import org.junit.Before
import org.junit.Test

class AdminMemberTest {

    AdminMember testMember

    @Before
    void "테스트 멤버 준비"() {
        testMember = new AdminMember("wheejuni", "1234!@#", "")
    }

    @Test
    void "패스워드 일치여부 테스트"() {
        //given
        def member = testMember

        //when
        def inputPassword = "1234!@#"

        //then
        assert member.isMatchingPassword(inputPassword)

    }
}
