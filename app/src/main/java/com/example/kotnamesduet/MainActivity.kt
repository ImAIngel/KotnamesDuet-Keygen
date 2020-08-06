package com.example.kotnamesduet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.kotnamesduet.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val keygen = KeyGen()
    val adapter = RecyclerAdapter()
    var onScreen = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter.setHasStableIds(true)
        recycler.adapter = adapter

        keygen.start()

        adapter.items.addAll(keygen.cardA)
        onScreen = 0
        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.mainNewGame -> {
                keygen.start()
                adapter.items.clear()
                adapter.items.addAll(keygen.cardA)
                onScreen = 0
                adapter.notifyDataSetChanged()
            }
            R.id.mainKeycard -> {
                if (onScreen == 0) {
                    adapter.items.clear()
                    adapter.items.addAll(keygen.cardB)
                    onScreen = 1
                    adapter.notifyDataSetChanged()
                }
                else {
                    adapter.items.clear()
                    adapter.items.addAll(keygen.cardA)
                    onScreen = 0
                    adapter.notifyDataSetChanged()
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }
}