package com.magicworld.clientsmanager.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.magicworld.clientsmanager.model.User
import com.magicworld.clientsmanager.repository.AddRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddViewModel: ViewModel() {

    private val addRepository = AddRepository()

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    private val _isSet = MutableLiveData<String>()
    val isSet: LiveData<String> = _isSet

    fun createUserInDatabase(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            addRepository.createUserInDatabase(user)
        }
    }

    fun saveUser(user: User) {
        _user.value = user
    }

    fun saveSet(set: Boolean) {
        _isSet.value =  if (set) "fijadas" else "otras"

    }

}