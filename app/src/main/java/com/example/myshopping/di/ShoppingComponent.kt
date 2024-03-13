package com.example.myshopping.di

import android.app.Application
import com.example.myshopping.data.ShopListProvider
import com.example.myshopping.presentation.MainActivity
import com.example.myshopping.presentation.ShopItemFragment
import dagger.BindsInstance
import dagger.Component
@AppScope
@Component(modules = [DataModule::class, ViewModelModule::class])
interface ShoppingComponent {

    fun inject(activity: MainActivity)
    fun inject(fragment: ShopItemFragment)
    fun inject(provider: ShopListProvider)

    @Component.Factory
    interface Factory{
        fun create(
            @BindsInstance application: Application
        ):ShoppingComponent
    }
}