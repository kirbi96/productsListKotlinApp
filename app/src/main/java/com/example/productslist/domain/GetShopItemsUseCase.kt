package com.example.productslist.domain

import androidx.lifecycle.LiveData

class GetShopItemsUseCase(private val shopItemsRepository: ShopItemsRepository) {
    fun getShopItems(): LiveData<List<ShopItem>> {
        return shopItemsRepository.getShopItems()
    }
}