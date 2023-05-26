package com.example.database

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.database.DataBase.UserEntity
import com.example.database.DataBase.UserRepository
import kotlinx.coroutines.launch

class MainActivityViewModel() : ViewModel() {

    private var _userData = MutableLiveData<List<UserEntity>>()
    val userData: MutableLiveData<List<UserEntity>> = _userData

    fun insertUser(user: UserEntity) {
        UserRepository.insert(user)
    }

    fun updateUser(user: UserEntity) {
        UserRepository.updateUsers(user)
    }

    fun deleteUser(name: String) {
        UserRepository.deleteUsers(name)
    }

    fun getAllUser() {
        viewModelScope.launch {
            _userData.value = UserRepository.getAllUserData()
        }
    }

    fun searchUser(userName: String) {
        viewModelScope.launch {
            _userData.value = UserRepository.searchUser(userName)
        }
    }

    //    for use when have to inject data to view model
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
//                val userDao = UserDataBase.getDatabase(ContextProvider.getContext()).userDao()
                MainActivityViewModel(
//                    userDao = userDao,
                )
            }
        }
    }
}