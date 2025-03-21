package com.example.vikasanimall.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

object Coroutines {

    lateinit var scope: Job
    fun backThread(work:suspend (()->Unit)){
        scope = CoroutineScope(Dispatchers.IO).launch {
            work()
        }
    }

    fun mainThread(work:(()->Unit))=
        CoroutineScope(Dispatchers.Main).launch {
            work()
        }
}