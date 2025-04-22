package app.prashant.cleankmp.di

import app.prashant.cleankmp.presentation.MainViewModel
import app.prashant.featurehome.di.homeFeatureModule
import com.prashant.core.di.coreModule
import com.prashant.coredata.di.includesDataModule
import com.prashant.featureonboarding.di.featureOnboardingModule
import com.prashant.uialert.di.alertModule
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

/**
 * Initializes Koin dependency injection framework with the provided additional modules.
 * It starts Koin with the given modules, including commonModule and platformModule by default.
 *
 * @param additionalModules Optional list of additional Koin modules to be included.
 *
 * @see Module
 * @see startKoin
 *
 * @author Prashant Singh
 */
fun initiateKoin(appDeclaration: KoinApplication.() -> Unit) {
    startKoin {
        appDeclaration()
        modules(commonAppModule)
        modules(platformModule)
        includesDataModule()
        modules(featureOnboardingModule)
        modules(homeFeatureModule)
    }
}

val commonAppModule =
    module {
        includes(alertModule)
        includes(coreModule)
        viewModelOf(::MainViewModel)
    }
