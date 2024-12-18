package com.example.productslist.domain

class AddShopItemUseCase(private val shopItemsRepository: ShopItemsRepository) {
    fun addShopItem(shopItem: ShopItem) {
        shopItemsRepository.addShopItem(shopItem)
    }
}