package ru.app.fastestdelivery.presentation.main.bag.recycler

import androidx.recyclerview.widget.DiffUtil
import ru.app.fastestdelivery.domain.models.Product

class BagProductDiffUtilCallback(
    private val oldList: List<Product>,
    private val newList: List<Product>
): DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldTab = oldList[oldItemPosition]
        val newTab = newList[newItemPosition]

        return oldTab.id == newTab.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldTab = oldList[oldItemPosition]
        val newTab = newList[newItemPosition]

        return oldTab == newTab
    }

}