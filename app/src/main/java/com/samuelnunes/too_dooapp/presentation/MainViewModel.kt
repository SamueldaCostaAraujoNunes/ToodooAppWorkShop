package com.samuelnunes.too_dooapp.presentation

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

    fun setStateAdd() = _screenState.postValue(ScreenState.ADD)
    fun setStateEdit() = _screenState.postValue(ScreenState.EDIT)
    fun setStateSave() = _screenState.postValue(ScreenState.SAVE)

}

enum class ScreenState(@DrawableRes val icon: Int) {
    ADD(R.drawable.ic_add), EDIT(R.drawable.ic_edit), SAVE(R.drawable.ic_save)
}
