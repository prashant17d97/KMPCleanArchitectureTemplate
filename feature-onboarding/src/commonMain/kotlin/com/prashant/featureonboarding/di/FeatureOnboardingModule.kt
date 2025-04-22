package com.prashant.featureonboarding.di

import com.prashant.featureonboarding.presentation.intro.IntroViewModel
import com.prashant.featureonboarding.presentation.login.LoginViewModel
import com.prashant.featureonboarding.usecases.LoginWithEmailUseCase
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val featureOnboardingModule =
    module {
        // Use Cases
        factory { LoginWithEmailUseCase(authRepository = get()) }
        viewModelOf(::IntroViewModel)
        viewModelOf(::LoginViewModel)
    }
