package com.example.myshopping.di

import android.app.Application
import com.example.myshopping.data.AppDataBase
import com.example.myshopping.data.ShopListDao
import com.example.myshopping.data.ShopListRepositoryImpl
import com.example.myshopping.domain.ShopListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {
    @AppScope
    @Binds
    fun bindRepository(impl: ShopListRepositoryImpl): ShopListRepository

    companion object {
        @AppScope
        @Provides
        fun provideShopListDao(application: Application): ShopListDao {
            return AppDataBase.getInstance(application).shopListDao()
        }
    }

}