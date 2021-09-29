package com.bwaim.examplefeature

import androidx.lifecycle.ViewModel
import com.bwaim.navigation.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
public class ExampleFeatureViewModel @Inject constructor(
    private val navigationManager: NavigationManager
) : ViewModel() {
    private val _viewState = MutableStateFlow(ExampleFeatureState())
    public val viewState: StateFlow<ExampleFeatureState> = _viewState
}
