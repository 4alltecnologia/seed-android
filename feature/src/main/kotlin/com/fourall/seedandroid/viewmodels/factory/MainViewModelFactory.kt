package com.fourall.seedandroid.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fourall.seedandroid.viewmodels.MainViewModel
import com.fourall.seedandroid.viewmodels.utils.CommandProvider

class MainViewModelFactory(
    private val commandProvider: CommandProvider
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        MainViewModel(commandProvider) as T
}