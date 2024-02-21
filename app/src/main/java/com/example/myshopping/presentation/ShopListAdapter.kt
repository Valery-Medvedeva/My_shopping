package com.example.myshopping.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myshopping.R
import com.example.myshopping.databinding.ItemDisabledBinding
import com.example.myshopping.databinding.ItemEnabledBinding
import com.example.myshopping.domain.ShopItem
import java.lang.RuntimeException

class ShopListAdapter : ListAdapter<ShopItem, ShopItemViewHolder>(ShopItemDiffCallBack()) {

    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
    var onShopItemClickListener: ((ShopItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layout = when (viewType) {
            enabledType -> R.layout.item_enabled
            disabledType -> R.layout.item_disabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            layout,
            parent,
            false
        )
        return ShopItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = getItem(position)
        val binding=holder.binding
        when (binding){
            is ItemDisabledBinding ->{
                binding.shopItem=shopItem
            }
            is ItemEnabledBinding ->{
                binding.shopItem=shopItem
            }
        }
        binding.root.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(shopItem)
            true
        }
       binding.root.setOnClickListener {
            onShopItemClickListener?.invoke(shopItem)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).enabled) {
            enabledType
        } else {
            disabledType
        }
    }

    companion object {

        const val enabledType = 1
        const val disabledType = 0
        const val MAX_POOL_SIZE = 10
    }
}