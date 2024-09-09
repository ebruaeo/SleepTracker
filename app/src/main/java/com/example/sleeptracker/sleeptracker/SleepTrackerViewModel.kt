package com.example.sleeptracker.sleeptracker

import android.app.Application
import android.service.autofill.Transformation

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.sleeptracker.database.SleepDatabaseDao
import com.example.sleeptracker.database.SleepNight
import com.example.sleeptracker.formatNights
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SleepTrackerViewModel(
    val database: SleepDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    // private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var tonight = MutableLiveData<SleepNight?>()

    private val nights = database.getAllNights()
    init {
        initializeTonight()
    }

    private fun initializeTonight() {
        viewModelScope.launch {
            tonight.value = getTonightFromDatabase()
        }
    }
}
