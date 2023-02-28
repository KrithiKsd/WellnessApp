

package com.example.wellnessapp.ui.task

import com.example.wellnessapp.data.Task


/**
 * Represents Ui State for an Item.
 */
data class ItemUiState(
    val id: Int = 0,
    val name: String = "",
    val isDone:Boolean = false,
    /*val price: String = "",
    val quantity: String = "",*/
    val actionEnabled: Boolean = false
)

/**
 * Extension function to convert [ItemUiState] to [Item]. If the value of [ItemUiState.price] is
 * not a valid [Double], then the price will be set to 0.0. Similarly if the value of
 * [ItemUiState] is not a valid [Int], then the quantity will be set to 0
 */
fun ItemUiState.toItem(): Task = Task(
    id = id,
    name = name,
    isDone= false
    /*price = price.toDoubleOrNull() ?: 0.0,
    quantity = quantity.toIntOrNull() ?: 0*/
)

/**
 * Extension function to convert [Item] to [ItemUiState]
 */
fun Task.toItemUiState(actionEnabled: Boolean = false): ItemUiState = ItemUiState(
    id = id,
    name = name,
    isDone=isDone,
  /*  price = price.toString(),
    quantity = quantity.toString(),*/
    actionEnabled = actionEnabled
)

fun ItemUiState.isValid() : Boolean {
   // return name.isNotBlank() && price.isNotBlank() && quantity.isNotBlank()
    return name.isNotBlank()
 }
