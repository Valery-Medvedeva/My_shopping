package com.example.myshopping.presentation

import androidx.lifecycle.ViewModel
import com.example.myshopping.data.ShopListRepositoryImpl
import com.example.myshopping.domain.AddShopItemUseCase
import com.example.myshopping.domain.EditShopItemUseCase
import com.example.myshopping.domain.GetShopItemUseCase
import com.example.myshopping.domain.ShopItem
import java.lang.Exception

class ShopItemViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopItemUseCase = GetShopItemUseCase(repository)
    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    fun addShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid=validateInput(name, count)
        if (fieldsValid){
            val shopItem=ShopItem(name,count,true)
            addShopItemUseCase.addShopItem(shopItem)
        }
    }

    fun editShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid=validateInput(name, count)
        if (fieldsValid){
            val shopItem=ShopItem(name,count,true)
            editShopItemUseCase.editShopItem(shopItem)
        }
        //TODO: remake
    }

    fun getShopItem(shopItemId: Int) {
        val item = getShopItemUseCase.getShopItem(shopItemId)
    }

    private fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }

    private fun parseCount(inputCount: String?): Int {
        return try{
            inputCount?.trim()?.toInt() ?: 0
        } catch (e: Exception){
            0
        }
    }

    private fun validateInput(name:String, count: Int): Boolean{
        var result=true
        if (name.isBlank()){
            // TODO: show error input
            result=false
        }
        if (count<=0){
            // TODO: show error input
            result=false
        }
        return result
    }
}