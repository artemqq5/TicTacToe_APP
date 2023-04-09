package com.sgjedo.tiktaktoe

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.postDelayed
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.sgjedo.tiktaktoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val win by lazy {
        Win()
    }

    companion object {
        private const val state_X = 'X'
        private const val state_O = 'O'
    }

    private val blackList = mutableSetOf<ImageView>()
    private lateinit var imageList: ArrayList<ImageView>

    private var state = state_O

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            imageList = arrayListOf(t1, t2, t3, t4, t5, t6, t7, t8, t9)

            t1.setOnClickListener(this@MainActivity)
            t2.setOnClickListener(this@MainActivity)
            t3.setOnClickListener(this@MainActivity)
            t4.setOnClickListener(this@MainActivity)
            t5.setOnClickListener(this@MainActivity)
            t6.setOnClickListener(this@MainActivity)
            t7.setOnClickListener(this@MainActivity)
            t8.setOnClickListener(this@MainActivity)
            t9.setOnClickListener(this@MainActivity)

        }


    }

    override fun onClick(p0: View?) {
        blackList.add(p0 as ImageView)
        for (i in imageList) {
            i.isEnabled = false
        }

        state = if (state == state_O) {
            p0.setImageResource(R.drawable.circle)
            p0.setTag("circle")
            state_X
        } else {
            p0.setImageResource(R.drawable.cross)
            p0.setTag("cross")
            state_O
        }

        val result = binding.run {
            win.checkFight(
                (t1.tag ?: "none") as String, (t2.tag ?: "none") as String,
                (t3.tag ?: "none") as String, (t4.tag ?: "none") as String,
                (t5.tag ?: "none") as String, (t6.tag ?: "none") as String,
                (t7.tag ?: "none") as String, (t8.tag ?: "none") as String,
                (t9.tag ?: "none") as String, circle = "circle", cross = "cross",
            )
        }

        Log.i("myLog", "$result")

        if (result || blackList.size == imageList.size) {
            Handler(Looper.myLooper()!!).postDelayed(1000) {
                MaterialAlertDialogBuilder(this)
                    .setTitle("Game Over")
                    .setMessage("to Restart press ok or close the dialog")
                    .setPositiveButton("ok") { d, _ ->
                        d.dismiss()
                    }.show()

                blackList.clear()

                for (i in imageList) {
                    i.tag = ""
                    i.setImageResource(android.R.color.transparent)
                    i.isEnabled = true
                }
            }

        }
        else {
            for (i in imageList) {
                if (i !in blackList) i.isEnabled = true
            }
        }

    }
}