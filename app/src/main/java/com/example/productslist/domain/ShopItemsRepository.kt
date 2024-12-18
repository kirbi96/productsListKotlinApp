package com.example.productslist.domain

import androidx.lifecycle.LiveData

interface ShopItemsRepository {
    fun getShopItems(): LiveData<List<ShopItem>>

    fun getShopItem(id: Int): ShopItem

    fun editItem(shopItem: ShopItem)

    fun removeShopItem(shopItem: ShopItem)

    fun addShopItem(shopItem: ShopItem)
}