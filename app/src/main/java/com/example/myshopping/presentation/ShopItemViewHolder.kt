package com.example.myshopping.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myshopping.R

class ShopItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val name = view.findViewById<TextView>(R.id.name)
    val count = view.findViewById<TextView>(R.id.count)
}