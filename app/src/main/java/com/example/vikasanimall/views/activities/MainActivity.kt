package com.example.vikasanimall.views.activities

//import com.example.vikasanimall.ui.theme

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.vikasanimall.databinding.MainActivityBinding
import com.example.vikasanimall.model.Employee
import com.example.vikasanimall.util.CustomFunctions
import com.example.vikasanimall.viewmodel.MainActivityViewModel
import com.example.vikasanimall.views.activities.adapter.EmployeesAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.util.Collections
import java.util.Random

@AndroidEntryPoint
class MainActivity : FragmentActivity() {
     private lateinit var binding : MainActivityBinding
     private lateinit var mainActivityViewModel: MainActivityViewModel;

    var  employeeList :MutableList<Employee> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainActivityViewModel =  ViewModelProvider(this).get(MainActivityViewModel::class.java)

        mainActivityViewModel.successfullyGetEmployee.observe(this, Observer {
            var responseData = it.body()
            employeeList = responseData?.employees!!
            Log.e("ResponseObject","response object is "+responseData?.employees)
            setAdapter()
        })
        binding.swipeRefreshLayout.setOnRefreshListener {

            mainActivityViewModel.getEmployees()


        }
        mainActivityViewModel.successfullyGetEmployeeSwipe.observe(this, Observer {
            var responseData = it.body()
            employeeList = responseData?.employees!!
            Log.e("ResponseObject","response object is "+responseData?.employees)
            Collections.shuffle(employeeList, Random(System.currentTimeMillis()))
            setAdapter()
            binding.swipeRefreshLayout.isRefreshing = false
        })

        mainActivityViewModel.progressBar.observe(this, Observer {
            if (it) {
                CustomFunctions.showProgressBar(
                    this@MainActivity,
                    "Loading...."
                )
            } else {
                CustomFunctions.hideProgressBar()
            }
        })
        mainActivityViewModel.listEmpty.observe(this, Observer {
            if (it) {
                CustomFunctions.showFeedbackMessage(binding.rootLayout, "List Found in Response is Empty")
            }
        })

        mainActivityViewModel.feedBackMessage.observe(this, Observer {
            CustomFunctions.showFeedbackMessage(binding.rootLayout, it)
            if(it.equals("No internet")){
                binding.swipeRefreshLayout.isRefreshing = false
            }
        })
        mainActivityViewModel.noInternet.observe(this, Observer {
            if(it) {
                CustomFunctions.showFeedbackMessage(binding.rootLayout, "No internet")
                binding.swipeRefreshLayout.isRefreshing = false
            }else{
                mainActivityViewModel.getEmployees()
                if(mainActivityViewModel.configurationChange == false)
                CustomFunctions.showFeedbackMessage(binding.rootLayout, "Back Online")
            }
        })
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()

        if (employeeList.isEmpty() == false) {
            setAdapter()
        }
    }


    override fun onPause() {
        super.onPause()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        mainActivityViewModel.configurationChange = true
    }

    fun setAdapter() {
        var adapter = EmployeesAdapter(employeeList)
        binding.recyclerEmployeeListing.layoutManager = GridLayoutManager(this,2)
        binding.recyclerEmployeeListing.adapter = adapter
    }

}






@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
   /* VikasAnimallTheme {
        Greeting("Android")
    }*/
}