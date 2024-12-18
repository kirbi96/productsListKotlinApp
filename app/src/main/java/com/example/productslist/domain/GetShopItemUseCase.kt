package com.example.productslist.domain

class GetShopItemUseCase(private val shopItemsRepository: ShopItemsRepository) {
    fun getShopItem(id: Int): ShopItem {
        return shopItemsRepository.getShopItem(id)
    }
}