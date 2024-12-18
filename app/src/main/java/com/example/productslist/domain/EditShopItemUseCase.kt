package com.example.productslist.domain

class EditShopItemUseCase(private val shopItemsRepository: ShopItemsRepository) {
    fun editItem(shopItem: ShopItem) {
        shopItemsRepository.editItem(shopItem)
    }
}