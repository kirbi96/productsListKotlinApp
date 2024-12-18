package com.example.productslist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.productslist.domain.ShopItem
import com.example.productslist.domain.ShopItemsRepository
import java.lang.RuntimeException

object ShopItemsRepositoryImpl : ShopItemsRepository {

    private val shopListLD = MutableLiveData<List<ShopItem>>()
    private val shopList = sortedSetOf<ShopItem>({ o1, o2 -> o1.id.compareTo(o2.id) })

    private var autoIncrementId = 0

    init {
        for (i in 0 until 1000) {
            val item = ShopItem("Name $i", i, i % 15 == 0)
            addShopItem(item)
        }
    }

    override fun getShopItems(): LiveData<List<ShopItem>> {
        return shopListLD
    }

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
        updateList()
    }

    override fun getShopItem(id: Int): ShopItem {
        return shopList.find {
            it.id == id
        } ?: throw RuntimeException("Element with id $id not found")
    }

    override fun editItem(shopItem: ShopItem) {
        val oldElem = getShopItem(shopItem.id)
        removeShopItem(oldElem)
        addShopItem(shopItem)
    }

    override fun removeShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateList()
    }

    private fun updateList() {
        shopListLD.value = shopList.toList()
    }
}