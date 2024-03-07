package com.example.myshopping

import android.app.Application
import com.example.myshopping.di.DaggerShoppingComponent

class ShoppingApp: Application() {
    val component by lazy {
        DaggerShoppingComponent.factory().create(this)
    }
}