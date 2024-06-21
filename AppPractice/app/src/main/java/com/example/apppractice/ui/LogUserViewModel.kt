package com.example.apppractice.ui
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apppractice.data.LogUserRepository
import com.example.apppractice.model.LogUserEntyModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// View model
@HiltViewModel
class LogUserViewModel
      @Inject constructor(private val repository: LogUserRepository) : ViewModel() {

    // Objects
    private val _lstUsers = MutableStateFlow<List<LogUserEntyModel>>(listOf())
    val listUsers : StateFlow<List<LogUserEntyModel>> get() = _lstUsers

    // Add new question
    fun vmAddNewUser(nUser: LogUserEntyModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNewUserRepo(nUser)
        }
    }

    fun vmGetAllUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            _lstUsers.value = repository.getAllUsersRepo()
        }
    }

    fun vmLoginInUsers(nUser: String, nPassword: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.loginInUserRepo(nUser, nPassword)
        }
    }

      // setValue() runs on the main thread.
      // postValue() runs on the background thread.

      /*No se puede actualizar LiveData directamente desde un hilo en segundo plano. En Android,
      LiveData debe observarse desde el hilo principal (UI), y la actualización de LiveData
      también debe realizarse desde el  hilo principal para garantizar la coherencia y evitar
      condiciones de carrera.*/
}