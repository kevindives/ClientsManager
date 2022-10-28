package com.magicworld.clientsmanager.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.magicworld.clientsmanager.model.User
import com.magicworld.clientsmanager.repository.ListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel: ViewModel() {

    val listRepository = ListRepository()

    private val userLoad = MutableLiveData<ArrayList<User>>()
    val onUserLoaded: LiveData<ArrayList<User>> = userLoad

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading


    fun getUserFromFirebase(){
        viewModelScope.launch(Dispatchers.IO) {
            userLoad.postValue(listRepository.getSuperheroesFromFireBase())
            _isLoading.postValue(false)
        }

    }
}