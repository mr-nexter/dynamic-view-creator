package com.dynamicviewcreator

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.RecyclerView

class GridAdapter(private val data: MutableList<Pair<String, String>>, private val context: Context) :
    RecyclerView.Adapter<GridAdapter.GridViewHolder>() {

    class GridViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): GridViewHolder {
        val layout = LinearLayoutCompat(context)
        layout.orientation = LinearLayoutCompat.HORIZONTAL

        return GridViewHolder(layout)
    }

    override fun onBindViewHolder(viewHolder: GridViewHolder, position: Int) {
        val pair = data[position]
        val m = context.dpToPx(16)
        val tSize = context.spToPx(16f)

        val params = LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        params.setMargins(m, m, m, m)

        val textView1 = TextView(context)
        textView1.setTextColor(Color.BLACK)
        textView1.layoutParams = params
        textView1.textSize = tSize
        textView1.text = pair.first

        val textView2 = TextView(context)
        textView2.setTextColor(Color.BLACK)
        textView2.layoutParams = params
        textView2.textSize = tSize
        textView2.text = pair.second

        val layout = viewHolder.itemView as LinearLayoutCompat
        layout.addView(textView1)
        layout.addView(textView2)
    }

    override fun getItemCount() = data.size

}
