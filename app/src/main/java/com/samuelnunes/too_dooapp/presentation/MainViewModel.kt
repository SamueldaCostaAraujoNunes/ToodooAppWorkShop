package com.samuelnunes.too_dooapp.presentation

import android.service.autofill.CustomDescription
import androidx.annotation.DrawableRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.samuelnunes.too_dooapp.R
import kotlinx.coroutines.flow.*

class MainViewModel : ViewModel() {

    private val _loading = MutableStateFlow(true)
    private val _screenState = MutableLiveData<ScreenState>()

    val loading: StateFlow<Boolean>
        get() = _loading

    val screenState: LiveData<ScreenState>
        get() = _screenState

    fun showLoading() = _loading.update { true }

    fun hideLoading() = _loading.update { false }

    fun setState(screenState: ScreenState) = _screenState.postValue(screenState)

}

enum class ScreenState(@DrawableRes val icon: Int, private val description: String) {
    ADD(R.drawable.ic_add, "Insira uma Nota"),
    EDIT(R.drawable.ic_edit, "Edite esta Nota"),
    SAVE(R.drawable.ic_save, "Salve esta Nota");

    override fun toString(): String = description
}
