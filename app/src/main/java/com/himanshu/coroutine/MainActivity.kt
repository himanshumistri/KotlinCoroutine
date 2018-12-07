package com.himanshu.coroutine

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

   // private var mDeferred:Deferred<Unit>?=null

    private var mUiScopeJob= Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main+mUiScopeJob

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txt_name.text=getString(R.string.name_developer)

        this.launch{
            Log.i("Tag"," Step 1 Suspend function call started")
           val deferred=async (Dispatchers.Default){
               runBackground()
            } //.await()
            Log.i("Tag"," Step 3 Suspend function call Ended")
            txt_name.text=" Step 3 Change by Corotine"
            Log.i("Tag"," Step 4 Suspend function call Ended")
        }



        //Use of await
        this.launch{
            Log.i("Tag"," Step 1 Suspend function call started")
            val deferred=async (Dispatchers.Default){
                runBackground()
            }.await()
            Log.i("Tag"," Step 3 Suspend function call Ended")
            txt_name.text=" Step 3 Change by Corotine"
            Log.i("Tag"," Step 4 Suspend function call Ended")
        }

        kotlin.run {
            Log.i("Tag","Kotlin run block")
        }
        Log.i("Tag","Activity on Created")

    }


   /* fun main()= runBlocking{
     //   launch(coroutineContext,CoroutineStart.DEFAULT,)
        launch{

        }

    }*/


    private suspend fun runBackground(){
        // Thread.sleep(1000)
         // /delayCoroutines()
         delay(3000)
         Log.i("Tag"," Step 2 Message from coroutine")
         delay(1000)
    }

    override fun onStop() {
        super.onStop()
        mUiScopeJob.cancel()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Tag","onDestroy")
    }

}
