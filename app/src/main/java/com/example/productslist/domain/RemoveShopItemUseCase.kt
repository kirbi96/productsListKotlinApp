package com.example.productslist.domain

class RemoveShopItemUseCase(private val shopItemsRepository: ShopItemsRepository) {
    fun removeShopItem(shopItem: ShopItem) {
        shopItemsRepository.removeShopItem(shopItem)
    }
}