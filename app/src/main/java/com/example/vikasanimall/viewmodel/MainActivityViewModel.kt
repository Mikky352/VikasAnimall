package com.example.vikasanimall.viewmodel

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.lifecycle.MutableLiveData
import com.example.vikasanimall.model.GetemployeeResponse
import com.example.vikasanimall.network.Api
import com.example.vikasanimall.network.Repository
import com.example.vikasanimall.util.ApiException
import com.example.vikasanimall.util.Coroutines
import com.example.vikasanimall.util.CustomFunctions
import com.example.vikasanimall.util.NoInternetException
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val api: Api ,private  val application: Application)  :BaseViewModel(application = Application()){
     //lateinit var api : Api
    var successfullyGetEmployee: MutableLiveData<retrofit2.Response<GetemployeeResponse>> = MutableLiveData()
    var successfullyGetEmployeeSwipe: MutableLiveData<retrofit2.Response<GetemployeeResponse>> = MutableLiveData()
    val  applicationContext = application
    lateinit var networkRegistor : BroadcastReceiver
    var configurationChange : Boolean = true
   //private var count = 0
    init{
    //   api =  Repository.retrofit.create(Api::class.java)

       networkRegistor = object : BroadcastReceiver() {
           override fun onReceive(context: Context, intent: Intent) {
               if (intent.action == ConnectivityManager.CONNECTIVITY_ACTION) {
                   val networkInfo =
                       intent.getParcelableExtra<NetworkInfo>(ConnectivityManager.EXTRA_NETWORK_INFO)
                   if (networkInfo != null && networkInfo.detailedState == NetworkInfo.DetailedState.CONNECTED) {
                       //    Log.d(LOG_TAG, "We have internet connection. Good to go.")
                       //  CustomFunctions.showFeedbackMessage(binding.rootLayout, "Back Online")
                       noInternet.value = false
                   } else if (networkInfo != null && networkInfo.detailedState == NetworkInfo.DetailedState.DISCONNECTED) {
                       // Log.d(LOG_TAG, "We have lost internet connection")
                         noInternet.value = true
                         configurationChange = false
                       //  CustomFunctions.showFeedbackMessage(binding.rootLayout, "No internet")
                   }
               }
           }
       }

       val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        application.registerReceiver(networkRegistor, intentFilter)

     /* if(CustomFunctions.hasInternet(applicationContext)){
         *//* if(count > 0){
             count = count -1
          }*//*
          Coroutines.mainThread {
             *//* if(count > 0) {
                  noInternet.value = false
              }*//*
              progressBar.value = true
          }
          Coroutines.backThread {
              try {
                  val response : retrofit2.Response<GetemployeeResponse> = api.getEmployees()

                      Coroutines.mainThread {
                          progressBar.value = false
                          successfullyGetEmployee.value = response
                          listEmpty.value = false
                      }


              } catch (e: ApiException) {
                  Coroutines.mainThread {
                      progressBar.value = false
                      feedBackMessage.value = e.message!!
                  }
              }
          }
      }else{
          *//*Coroutines.mainThread {
              noInternet.value = true

          }
          if(count == 0) {
              count = count + 1
          }*//*

      }*/



    }

    fun getEmployees() {

        if(CustomFunctions.hasInternet(applicationContext)){
           /* if(count > 0){
              count =  count -1
            }*/


            Coroutines.mainThread {
              /*  if(count > 0) {
                    noInternet.value = false
                }*/
                progressBar.value = true
            }
            Coroutines.backThread {
                try {
                    val response : retrofit2.Response<GetemployeeResponse> = api.getEmployees()
                     val responseEmpty = api.getEmployeesEmpty()
                    if(response.body()?.employees?.isEmpty() == false) {
                        Coroutines.mainThread {
                            progressBar.value = false
                            successfullyGetEmployeeSwipe.value = response
                            listEmpty.value = false
                        }
                    }else{
                        Coroutines.mainThread {
                            progressBar.value = false
                            successfullyGetEmployeeSwipe.value = responseEmpty
                            listEmpty.value = true
                        }


                    }


                } catch (e: ApiException) {
                    Coroutines.mainThread {
                        progressBar.value = false
                        feedBackMessage.value = e.message!!
                    }
                }
            }

        }else{
            /*Coroutines.mainThread {
                noInternet.value = true

            }
            if(count==0) {
                count = count + 1
            }*/

            Coroutines.mainThread {
                progressBar.value = false
                feedBackMessage.value = "No internet"
            }
        }

    }

    override fun onCleared() {
        super.onCleared()
        if (networkRegistor != null)
           applicationContext. unregisterReceiver(networkRegistor);
    }


}
/*catch (e: NoInternetException) {
                Coroutines.mainThread {
                  //  progressBar.value = false
                    noInternet.value = true
                   // feedBackMessage.value = e.message!!
                }
            }*/