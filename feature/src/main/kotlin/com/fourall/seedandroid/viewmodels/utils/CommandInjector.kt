package com.fourall.seedandroid.viewmodels.utils

object CommandInjector : CommandProvider {

    override fun getCommand(): SingleLiveEvent<GenericCommand> = SingleLiveEvent()
}