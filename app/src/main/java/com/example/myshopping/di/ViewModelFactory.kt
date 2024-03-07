package com.example.myshopping.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
@AppScope
class ViewModelFactory @Inject constructor(
    val viewModelProviders: @JvmSuppressWildcards
    Map <Class<out ViewModel>, Provider<ViewModel>>
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModelProviders[modelClass]?.get() as T
    }
}