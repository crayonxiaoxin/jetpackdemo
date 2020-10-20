package com.github.viewmodel.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.viewmodel.model.PhotoItem
import com.github.viewmodel.network.PhotoRepository
import com.github.viewmodel.network.PhotoService
import com.github.viewmodel.ui.HomeFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class PhotoViewModel : ViewModel() {
    var photos: MutableLiveData<List<PhotoItem>> = MutableLiveData()
    var totals: MutableLiveData<Int> = MutableLiveData(0)

    fun loadPhotos() {
        viewModelScope.launch {
            test {
                val s = PhotoRepository.getPhotos("car", 1)
                Log.e(HomeFragment.TAG, "doNetWork: " + s.hits)
                totals.postValue(s.totalHits)
                photos.postValue(s.hits)
            }
        }
    }

    private suspend fun test(block: suspend () -> Unit) {
        try {
            block()
        } catch (e: Exception) {
            Log.e(HomeFragment.TAG, "doNetWork: " + e.message)
        }
    }

}