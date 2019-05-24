package com.abhinav.mmdb.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abhinav.mmdb.data.model.Result
import com.abhinav.mmdb.utils.Event

open class BaseViewModel: ViewModel() {

    var failureLiveData: MutableLiveData<Event<Result.Failure>> = MutableLiveData()
    var uiStateLiveData: MutableLiveData<Event<Any>> = MutableLiveData()

}
