package com.example.productslist.domain

data class ShopItem(
    val name: String,
    val count: Int,
    val disabled: Boolean,
    var id: Int = UNDEFINED_ID,
) {
    companion object {
        const val UNDEFINED_ID = -1
    }
}
