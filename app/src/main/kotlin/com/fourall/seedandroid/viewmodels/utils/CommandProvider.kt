package com.fourall.seedandroid.viewmodels.utils

interface CommandProvider {

    fun getCommand() : SingleLiveEvent<GenericCommand>
}

abstract class GenericCommand