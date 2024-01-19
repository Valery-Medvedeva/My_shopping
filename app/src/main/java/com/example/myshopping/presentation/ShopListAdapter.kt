package com.example.myshopping.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myshopping.R
import com.example.myshopping.domain.ShopItem

class ShopListAdapter : RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {

    val list = listOf<ShopItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
       val view=LayoutInflater.from(parent.context).inflate(R.layout.item_enabled, parent, false)
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem=list[position]
        holder.name.text=shopItem.name
        holder.count.text=shopItem.count.toString()
        holder.view.setOnLongClickListener{
            true
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ShopItemViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        val name=view.findViewById<TextView>(R.id.name)
        val count=view.findViewById<TextView>(R.id.count)
    }
}