package com.qwert2603.floating_action_mode_example

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
import com.qwert2603.floating_action_mode.FloatingActionMode
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item.view.*

class MainActivity : AppCompatActivity() {

    var b: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = ItemsAdapter()
        recycler_view.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        floating_action_mode.onOpenListener = object : FloatingActionMode.OnOpenListener {
            override fun onOpen() {
                fab.animate().translationY(activity_main.height - fab.top.toFloat())
            }
        }

        floating_action_mode.onCloseListener = object : FloatingActionMode.OnCloseListener {
            override fun onClose() {
                fab.animate().translationY(0f)
                if (b) {
                    Snackbar.make(activity_main, "closed", Snackbar.LENGTH_SHORT).show()
                }
                b = !b
            }
        }
    }

    private inner class ItemsAdapter : RecyclerView.Adapter<ItemsAdapter.VH>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VH(LayoutInflater.from(this@MainActivity).inflate(R.layout.item, parent, false))

        override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(position)

        override fun getItemCount() = 100

        internal inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {

            init {
                itemView.setOnClickListener {
                    Snackbar.make(this@MainActivity.activity_main, itemView.text.text, Snackbar.LENGTH_SHORT).show()
                    if (adapterPosition == 14) {
                        floating_action_mode.canDrag = !floating_action_mode.canDrag
                    }
                    if (adapterPosition == 17) {
                        floating_action_mode.canClose = !floating_action_mode.canClose
                    }
                    if (adapterPosition == 26) {
                        if (floating_action_mode.contentRes == 0) floating_action_mode.contentRes = R.layout.user_list_action_mode else
                            if (floating_action_mode.contentRes == R.layout.user_list_action_mode) floating_action_mode.contentRes = R.layout.user_list_action_mode_2 else
                                if (floating_action_mode.contentRes == R.layout.user_list_action_mode_2) floating_action_mode.contentRes = 0
                    }
                }
                itemView.setOnLongClickListener {
                    floating_action_mode.open()
                    return@setOnLongClickListener true
                }
            }

            @SuppressLint("SetTextI18n")
            fun bind(i: Int) = with(itemView) {
                color.setBackgroundColor(Color.argb(i * 256 / 100, 0, 0, 0xff))
                text.text = "Text #" + i
            }
        }

    }
}
