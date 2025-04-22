package app.prashant.cleankmp.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.stopKoin

class CleanKMP : Application() {
    override fun onCreate() {
        super.onCreate()
        initiateKoin {
            androidContext(this@CleanKMP)
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }
}
