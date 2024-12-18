package com.example.productslist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.productslist.data.ShopItemsRepositoryImpl
import com.example.productslist.domain.AddShopItemUseCase
import com.example.productslist.domain.EditShopItemUseCase
import com.example.productslist.domain.GetShopItemUseCase
import com.example.productslist.domain.ShopItem

class ShopItemViewModel: ViewModel() {
    private var repository = ShopItemsRepositoryImpl

    private var getShopItemUseCase = GetShopItemUseCase(repository)
    private var addShopItemUseCase = AddShopItemUseCase(repository)
    private var editShopItemUseCase = EditShopItemUseCase(repository)

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
            get() = _errorInputName

    private val _errorInputCount = MutableLiveData<Boolean>()
    val errorInputCount: LiveData<Boolean>
        get() = _errorInputCount

    private val _shopItem = MutableLiveData<ShopItem>()
    val shopItem: LiveData<ShopItem>
        get() = _shopItem

    private val _closeScreen = MutableLiveData<Unit>()
    val closeScreen: LiveData<Unit>
        get() = _closeScreen

    fun getShopItem (id: Int) {
        val item = getShopItemUseCase.getShopItem(id)
        _shopItem.value = item
    }

    fun addShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)

        if(fieldsValid){
            val item = ShopItem(name, count, false)
            addShopItemUseCase.addShopItem(item)
            finishWork()
        }
    }

    fun editShopItem(inputName: String?, inputCount: String?, id: Int) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)

        if(fieldsValid){
            _shopItem.value?.let {
                val item = it.copy(name = name, count = count, id = id)
                editShopItemUseCase.editItem(item)
                finishWork()
            }
        }

    }

    private fun parseName (name: String?): String{
        return name?.trim() ?: ""
    }

    private fun parseCount (count: String?): Int{
        return try {
            count?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    private fun validateInput (name: String, count: Int): Boolean {
        if(name.isBlank()) {
            _errorInputName.value = true
            return false
        }

        if (count <= 0){
            _errorInputCount.value = true
             return false
        }

        return true
    }

    fun resetErrorInputName () {
        _errorInputName.value = false
    }

    fun resetErrorInputCount () {
        _errorInputCount.value = false
    }

    private fun finishWork () {
        _closeScreen.value = Unit
    }
}