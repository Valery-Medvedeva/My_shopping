package com.example.myshopping.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.myshopping.domain.ShopItem

class ShopItemDiffCallBack: DiffUtil.ItemCallback<ShopItem>() {
    override fun areItemsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem==newItem
    }
}