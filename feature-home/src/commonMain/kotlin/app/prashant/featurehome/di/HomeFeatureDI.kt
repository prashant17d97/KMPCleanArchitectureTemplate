package app.prashant.featurehome.di

import app.prashant.featurehome.presentation.home.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val homeFeatureModule =
    module {
        viewModelOf(::HomeViewModel)
    }
