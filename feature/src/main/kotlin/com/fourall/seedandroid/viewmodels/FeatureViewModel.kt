package com.fourall.seedandroid.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fourall.seedandroid.contract.repository.UserRepository
import com.fourall.seedandroid.model.User
import com.fourall.seedandroid.viewmodels.utils.CommandProvider
import com.fourall.seedandroid.viewmodels.utils.GenericCommand
import com.fourall.seedandroid.viewmodels.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FeatureViewModel(
    private val userRepository: UserRepository,
    private val commandProvider: CommandProvider
) : ViewModel() {

    val command: SingleLiveEvent<GenericCommand> = commandProvider.getCommand()
    val viewState: MutableLiveData<ViewState> = MutableLiveData()

    data class ViewState(
        val isLoadingUsers: Boolean = false
    )

    sealed class Command : GenericCommand() {

        class ShowUsers(val users: List<User>) : Command()
        object ShowErrorMessage : Command()
    }

    init {

        viewState.setValue(ViewState())
    }

    fun loadUsers() {

        viewState.value = currentViewState().copy(isLoadingUsers = true)

        GlobalScope.launch(Dispatchers.Main) {

            try {

                val response = userRepository.getUsers().await()

                viewState.value = currentViewState().copy(isLoadingUsers = false)

                if (response.isSuccessful) {

                    response.body()?.let { users -> command.setValue(Command.ShowUsers(users)) }
                } else {

                    command.setValue(Command.ShowErrorMessage)
                }
            } catch (e: Exception) {

                command.setValue(Command.ShowErrorMessage)
            }
        }
    }

    private fun currentViewState(): ViewState = viewState.value!!
}