package com.example.productslist.presentation

import androidx.lifecycle.ViewModel
import com.example.productslist.data.ShopItemsRepositoryImpl
import com.example.productslist.domain.EditShopItemUseCase
import com.example.productslist.domain.GetShopItemsUseCase
import com.example.productslist.domain.RemoveShopItemUseCase
import com.example.productslist.domain.ShopItem

class MainViewModel : ViewModel() {

    private var repository = ShopItemsRepositoryImpl

    private var getShopItemsUseCase = GetShopItemsUseCase(repository)
    private var removeShopItemUseCase = RemoveShopItemUseCase(repository)
    private var editShopItemUseCase = EditShopItemUseCase(repository)

    val shopItems = getShopItemsUseCase.getShopItems()

    fun deleteItem(item: ShopItem) {
        removeShopItemUseCase.removeShopItem(item)
    }

    fun changeDisableState(item: ShopItem) {
        val newItem = item.copy(disabled = !item.disabled)

        editShopItemUseCase.editItem(newItem)
    }
}