package ru.app.fastestdelivery.presentation.main.home.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.app.fastestdelivery.domain.models.Product
import kotlin.reflect.KFunction1

class HomeProductsAdapter(
    private val onProductClicked: (Product) -> Unit,
) : RecyclerView.Adapter<HomeProductViewHolder>() {

    private var items: List<Product> = emptyList()

    fun setItems(newItems: List<Product>) {
        val diffCallback = HomeProductDiffUtilCallback(this.items, newItems)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.items = newItems

        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        HomeProductViewHolder.from(parent)

    override fun onBindViewHolder(holder: HomeProductViewHolder, position: Int) {
        holder.bind(
            item = items[position],
            onProductClicked = onProductClicked,
        )
    }

    override fun getItemCount() = items.size

}