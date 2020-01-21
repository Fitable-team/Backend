package net.fittable.helper

import org.junit.Test

class UniqueIDGeneratorTest {

    @Test
    void "랜덤 아이디 생성 테스트"() {
        //given
       def generatedId = ""

        //when
        generatedId = UniqueIDGenerator.generateEntityId()

        //then
        assert generatedId.length() == 8
    }
}
