package net.fittable.domain.business.reservation

import org.junit.Before
import org.junit.Test

class RegularSessionTest {

    RegularSession testSession

    @Before
    void "셋업"() {
        testSession = new RegularSession()
    }

    @Test
    void "하나의 일자 추가"() {
        def weekday

        //given
        testSession.addRegularDay("화요일")

        //when
        weekday = testSession.getWeekdayEnums()

        //then
        assert weekday.head() == Weekday.TUESDAY

    }

    @Test
    void "여러개의 일자 추가"() {
        def daySet

        //given
        testSession.addRegularDay("월요일")
        testSession.addRegularDay("수요일")

        //then
        assert testSession.getWeekdayEnums().size() == 2

    }


}
