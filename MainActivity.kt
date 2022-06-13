package netvirta.app.test

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    lateinit var time : TextView
    lateinit var recycler: ListView
    lateinit var startButton: AppCompatButton
    var start = true
    var running = false
    val timeList: MutableList<String> = ArrayList()
    var timeElapsed  = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startButton = findViewById(R.id.start)
        time =  findViewById(R.id.time)
        time.text = "0"
        recycler = findViewById(R.id.recycler)

        var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, timeList.toTypedArray())
        recycler.adapter = adapter
    }

    fun startOrStop(view: View) {
        if(start){
            startButton.text = "Stop"
            running = true
            timeElapsed = 0
            val handler = Handler(Looper.getMainLooper())
            handler.post(object : Runnable {
                override fun run() {
                    // Set the text view text.
                    time.text = timeElapsed.toString()

                    // If running is true, increment the
                    // seconds variable.
                    if (running) {
                        timeElapsed++
                    }

                    // Post the code again
                    // with a delay of 1 millissecond.
                    handler.postDelayed(this, 1)
                }
            })
        } else {
            startButton.text = "Start"
            running = false
        }
        start = !start
    }

    fun split(view: View) {
        timeList.add(time.text.toString())
        var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, timeList.toTypedArray())
        recycler.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}