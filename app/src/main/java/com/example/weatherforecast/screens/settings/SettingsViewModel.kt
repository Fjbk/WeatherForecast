package com.example.weatherforecast.screens.settings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherforecast.repository.WeatherDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.weatherforecast.model.Unit
import kotlinx.coroutines.flow.collect


@HiltViewModel
class SettingsViewModel @Inject constructor( private val repository: WeatherDbRepository) : ViewModel() {

    //Er en tom liste i starten men favList ender med at være en liste over Unit objekter
    private val _unitList = MutableStateFlow<List<Unit>>(emptyList())
    val unitList = _unitList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getUnits().distinctUntilChanged()
                .collect { listOfUnits ->
                    if (listOfUnits.isNullOrEmpty()) {
                        Log.d("TAG", ":Empty list ")
                        _unitList.value = listOf(Unit("Celsius"))
                    } else {
                        _unitList.value = listOfUnits
                    }
                }
        }
    }

    fun insertUnit(unit: Unit) = viewModelScope.launch { repository.insertUnit(unit) }
    fun updateUnit(unit: Unit) = viewModelScope.launch { repository.updateUnit(unit) }
    fun deleteUnit(unit: Unit) = viewModelScope.launch { repository.deleteUnit(unit) }
    fun deleteAllUnits() = viewModelScope.launch { repository.deleteAllUnits() }


}

