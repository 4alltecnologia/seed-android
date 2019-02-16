package com.fourall.seedandroid.viewmodels

import androidx.lifecycle.ViewModel
import com.fourall.seedandroid.model.User
import com.fourall.seedandroid.viewmodels.utils.CommandProvider
import com.fourall.seedandroid.viewmodels.utils.GenericCommand
import com.fourall.seedandroid.viewmodels.utils.SingleLiveEvent

class MainViewModel(private val commandProvider: CommandProvider) : ViewModel() {

    val command: SingleLiveEvent<GenericCommand> = commandProvider.getCommand()

    sealed class Command : GenericCommand() {

        class ShowUsers(users: List<User>) : Command()
    }

    fun loadUsers() {
    }
}