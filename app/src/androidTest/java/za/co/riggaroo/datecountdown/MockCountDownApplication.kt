package za.co.riggaroo.datecountdown

import za.co.riggaroo.datecountdown.dao.DaggerEventDaoTest_MockCountdownComponent
import za.co.riggaroo.datecountdown.injection.CountdownComponent
import za.co.riggaroo.datecountdown.injection.MockCountdownModule

/**
 * @author rebeccafranks
 * *
 * @since 2017/05/12.
 */

class MockCountDownApplication : CountdownApplication() {

    override val countDownComponent: CountdownComponent = DaggerEventDaoTest_MockCountdownComponent.builder()
            .mockCountdownModule(MockCountdownModule(this))
            .build()
}
