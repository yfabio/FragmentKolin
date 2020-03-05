package com.tech.developer

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.concurrent.*
import kotlin.random.Random

class Dynamic : AppCompatActivity() {

    var exec: ExecutorService = Executors.newSingleThreadExecutor()

    var list = mutableListOf<String>()

    var future: Future<List<String>>? = null

    var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
       setContentView(R.layout.activity_dynamic)


        var worker = Worker(resources.openRawResource(R.raw.colors))
        future = exec.submit(worker)


    }


    fun onNext(view: View) {

      if(this.list.size == 0){
          if(future?.isDone == true) {
              future?.get()?.forEach {
                  this.list.add(it)
              }
          }
      }


        var color = getColorFromList(list)
        index++

        var df = DynamicFragment(color,index)


        supportFragmentManager.beginTransaction()
            .replace(R.id.frag,df,"frag").commit()


    }


    inner class Worker(var input: InputStream) : Callable<List<String>> {

        override fun call(): List<String> {

            var list = mutableListOf<String>()

            var br = BufferedReader(InputStreamReader(input))

            br.lines().forEach {
                list.add(it)
            }

            br.close()
            return list
        }

    }


    fun getColorFromList(list:List<String>): String {
        var index = Random.nextInt(0,list.size)
        return list.get(index)
    }





}
