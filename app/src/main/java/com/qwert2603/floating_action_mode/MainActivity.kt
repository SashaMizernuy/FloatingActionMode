package com.qwert2603.floating_action_mode

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = ItemsAdapter()
        recycler_view.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }

    private inner class ItemsAdapter : RecyclerView.Adapter<ItemsAdapter.VH>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
            return VH(LayoutInflater.from(this@MainActivity).inflate(R.layout.item, parent, false))
        }

        override fun onBindViewHolder(holder: VH, position: Int) {
            holder.bind(position)
        }

        override fun getItemCount(): Int {
            return 100
        }

        internal inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {

            init {
                itemView.setOnClickListener { Snackbar.make(this@MainActivity.activity_main, itemView.text.text, Snackbar.LENGTH_SHORT).show() }
            }

            @SuppressLint("SetTextI18n")
            fun bind(i: Int) = with(itemView) {
                color.setBackgroundColor(Color.argb(i * 256 / 100, 0xff, 0, 0))
                text.text = "Text #" + i
            }
        }

    }
}