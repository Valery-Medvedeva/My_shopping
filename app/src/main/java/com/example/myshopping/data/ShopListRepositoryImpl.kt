package com.example.myshopping.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myshopping.domain.ShopItem
import com.example.myshopping.domain.ShopListRepository
import java.lang.RuntimeException

object ShopListRepositoryImpl : ShopListRepository {

    private var newId = 0

    private val shopList = mutableListOf<ShopItem>()

    private val shopListLD=MutableLiveData<List<ShopItem>>()

    init {
        for (i in 0 until 10){
            val item=ShopItem("name $i", i, true)
            addShopItem(item)
        }
    }
    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLD
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopList.find { it.id == shopItemId } ?: throw RuntimeException("not found")
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldItem = getShopItem(shopItem.id)
        shopList.remove(oldItem)
        addShopItem(shopItem)
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateList()
    }

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id==ShopItem.UNDEFINED_ID) {
            shopItem.id = newId++
        }
        shopList.add(shopItem)
        updateList()
    }

    private fun updateList(){
        shopListLD.value= shopList.toList()
    }
}