package com.example.vikasanimall.util

import android.app.Activity
import android.app.Application
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.vikasanimall.R
import com.google.android.material.snackbar.Snackbar

object CustomFunctions {
    var mDialogProgress: AlertDialog? = null



    fun showProgressBar(activity: Activity, message: String) {



        val alertDialog = AlertDialog.Builder(activity)
        alertDialog.setCancelable(false)
        val view = LayoutInflater.from(activity).inflate(R.layout.view_progress_dialog, null)
        val textView = view.findViewById<TextView>(R.id.textView)
        textView.setText(message)
        alertDialog.setView(view)
        mDialogProgress = alertDialog.create()
        mDialogProgress!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        if(mDialogProgress!=null) {
            if (!mDialogProgress!!.isShowing && !activity.isFinishing) {
                mDialogProgress?.show()
            }
        }


        val lp = WindowManager.LayoutParams()
        mDialogProgress!!.window.also {
            it!!.setGravity(Gravity.CENTER)
            lp.copyFrom(it.attributes)
            lp.width = WindowManager.LayoutParams.MATCH_PARENT
            lp.height = WindowManager.LayoutParams.MATCH_PARENT
            it.attributes = lp
        }
    }

    fun hideProgressBar() {
        if (mDialogProgress != null) {
            mDialogProgress!!.dismiss()
        }
    }

    fun showFeedbackMessage(view: View, message: String) {
        val snakbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        snakbar.view.setBackgroundColor(ContextCompat.getColor(view.context, R.color.teal_700))
        if (snakbar.isShown) {
            snakbar.dismiss()
        }
        snakbar.show()
    }

    fun hasInternet(activity:Application) : Boolean{
        val connectivityManager = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork

        val capabilities = connectivityManager.getNetworkCapabilities(network)
        var hasInternet = false
        capabilities?.let {
            hasInternet = it.hasCapability(NET_CAPABILITY_INTERNET)
        }
        return hasInternet

    }

}