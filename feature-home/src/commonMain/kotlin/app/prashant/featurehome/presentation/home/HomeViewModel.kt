package app.prashant.featurehome.presentation.home

import androidx.lifecycle.ViewModel
import com.prashant.uialert.api.AlertDispatcher
import org.koin.core.component.KoinComponent

class HomeViewModel(
    private val alertDispatcher: AlertDispatcher,
) : ViewModel(), KoinComponent
