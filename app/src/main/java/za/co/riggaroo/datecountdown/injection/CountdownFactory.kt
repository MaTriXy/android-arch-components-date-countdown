package za.co.riggaroo.datecountdown.injection

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

import za.co.riggaroo.datecountdown.CountdownApplication

/**
 * @author rebeccafranks
 * *
 * @since 2017/05/10.
 */

class CountdownFactory(private val application: CountdownApplication) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val t = super.create(modelClass)
        if (t is CountdownComponent.Injectable) {
            t.inject(application.countDownComponent)
        }
        return t
    }
}
