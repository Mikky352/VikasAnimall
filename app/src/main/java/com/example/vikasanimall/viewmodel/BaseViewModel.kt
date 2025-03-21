package com.example.vikasanimall.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel(application: Application) : AndroidViewModel(application = Application()) {
    var feedBackMessage: MutableLiveData<String> = MutableLiveData()
    var progressBar: MutableLiveData<Boolean> = MutableLiveData()
    var listEmpty: MutableLiveData<Boolean> = MutableLiveData()
    var noInternet :  MutableLiveData<Boolean> = MutableLiveData()

}