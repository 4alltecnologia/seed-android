package com.fourall.seedandroid.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fourall.seedandroid.contract.repository.UserRepository
import com.fourall.seedandroid.viewmodels.FeatureViewModel
import com.fourall.seedandroid.viewmodels.utils.CommandProvider

class FeatureViewModelFactory(
    private val userRepository: UserRepository,
    private val commandProvider: CommandProvider
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        FeatureViewModel(userRepository, commandProvider) as T
}