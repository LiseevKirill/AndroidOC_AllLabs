package com.example.lab5

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ColorAdapter(
    private val context: Context,
    private val items: List<ColorItem>
) : RecyclerView.Adapter<ColorAdapter.ColorViewHolder>() {

    inner class ColorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewNumber: TextView = view.findViewById(R.id.textViewNumber)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_color_number, parent, false)
        return ColorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        val item = items[position]
        holder.textViewNumber.text = item.number.toString()
        val background = holder.textViewNumber.background.mutate()
        background.setTint(item.color)
        holder.textViewNumber.background = background


        holder.itemView.setOnClickListener {
            AlertDialog.Builder(context)
                .setTitle("Значення елемента")
                .setMessage("Число: ${item.number}")
                .setPositiveButton("OK", null)
                .show()
        }
    }

    override fun getItemCount(): Int = items.size
}
