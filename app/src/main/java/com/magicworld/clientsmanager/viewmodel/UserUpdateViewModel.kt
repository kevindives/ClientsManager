package com.magicworld.clientsmanager.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.magicworld.clientsmanager.model.User
import com.magicworld.clientsmanager.repository.UserUpdateRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserUpdateViewModel: ViewModel() {
    private val userUpdateRepository = UserUpdateRepository()

    private val _updateUser = MutableLiveData<User>()
    val updateUser: LiveData<User> = _updateUser

    private val messageFromFirebase = MutableLiveData<String>()
    val messageFromFirebaseDone : LiveData<String> = messageFromFirebase


    fun updateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
             messageFromFirebase.postValue(userUpdateRepository.updateUser(user))
        }

    }

    fun saveUpdateUser(updateUser: User) {
        _updateUser.value = updateUser
    }

    fun updateSet(isSet: Boolean, id: String) {
        val set = if (isSet) "fijadas" else "otras"
        viewModelScope.launch(Dispatchers.IO) {
            userUpdateRepository.updateSet(set , id)
        }
    }

    fun deleteUser(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            userUpdateRepository.deleteUser(id)
        }
    }

}