package com.example.productslist.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.productslist.R
import com.example.productslist.domain.ShopItem

class ShopListAdapter : ListAdapter<ShopItem, ShopItemViewHolder>(ShopItemDiffCallback()) {
    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
    var onShopItemClickListener: ((ShopItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layout = when (viewType) {
            ITEM_DISABLED -> R.layout.item_shop_disabled
            ITEM_ENABLED -> R.layout.item_shop_enabled
            else -> throw RuntimeException("Unknown view type $viewType")
        }

        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = getItem(position)

        holder.tvName.text = shopItem.name
        holder.tvCount.text = shopItem.count.toString()

        holder.view.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(shopItem)
            true
        }

        holder.view.setOnClickListener() {
            onShopItemClickListener?.invoke(shopItem)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val element = getItem(position)

        return if (element.disabled) {
            ITEM_DISABLED
        } else {
            ITEM_ENABLED
        }
    }

    companion object {
        const val ITEM_DISABLED = 100
        const val ITEM_ENABLED = 101
        const val MAX_PULL_SIZE = 10
    }
}