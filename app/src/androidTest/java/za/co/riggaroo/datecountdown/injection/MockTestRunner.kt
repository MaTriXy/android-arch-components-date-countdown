package za.co.riggaroo.datecountdown.injection

import android.app.Application
import android.content.Context
import android.support.test.runner.AndroidJUnitRunner

import za.co.riggaroo.datecountdown.MockCountDownApplication

/**
 * @author rebeccafranks
 * *
 * @since 2017/05/12.
 */

class MockTestRunner : AndroidJUnitRunner() {
    @Throws(InstantiationException::class, IllegalAccessException::class, ClassNotFoundException::class)
    override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
        return super.newApplication(cl, MockCountDownApplication::class.java.name, context)
    }
}
