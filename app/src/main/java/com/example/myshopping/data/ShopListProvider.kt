package com.example.myshopping.data

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.util.Log
import com.example.myshopping.ShoppingApp
import com.example.myshopping.domain.ShopItem
import javax.inject.Inject

class ShopListProvider : ContentProvider() {
    @Inject
    lateinit var shopListDao: ShopListDao

    @Inject
    lateinit var mapper: ShopListMapper

    private val component by lazy {
        (context as ShoppingApp).component
    }

    private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
        addURI("com.example.myshopping", "shop_items", GET_SHOP_ITEMS_QUERY)
        addURI("com.example.myshopping", "shop_items/#", GET_SHOP_ITEMS_BY_ID_QUERY)
    }

    override fun onCreate(): Boolean {
        component.inject(this)
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        val code = uriMatcher.match(uri)
        return when (code) {
            GET_SHOP_ITEMS_QUERY -> shopListDao.getShopListCursor()
            else -> null
        }
    }

    override fun getType(uri: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        when (uriMatcher.match(uri)) {
            GET_SHOP_ITEMS_QUERY -> {
                if (values == null) return null
                val id = values.getAsInteger("id")
                val count = values.getAsInteger("count")
                val name = values.getAsString("name")
                val enabled = values.getAsBoolean("enabled")
                val item = ShopItem(name, count, enabled, id)
                shopListDao.addShopItemForContentProvider(mapper.mapEntityToDbModel(item))
            }
        }
        return null
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        when (uriMatcher.match(uri)) {
            GET_SHOP_ITEMS_QUERY -> {
                val id = selectionArgs?.get(0)?.toInt() ?: 0
                return shopListDao.deleteShopItemForContentProvider(id)
            }
        }
        return 0
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        when (uriMatcher.match(uri)) {
            GET_SHOP_ITEMS_QUERY -> {
                if (values==null) return 0
                val id = values.getAsInteger("id")
                val count = values.getAsInteger("count")
                val name = values.getAsString("name")
                val enabled = values.getAsBoolean("enabled")
                val item = ShopItem(name, count, enabled, id)
                shopListDao.addShopItemForContentProvider(mapper.mapEntityToDbModel(item))
                return 1
            }
        }
        return 0
    }

    companion object {
        const val GET_SHOP_ITEMS_QUERY = 100
        const val GET_SHOP_ITEMS_BY_ID_QUERY = 101
    }
}