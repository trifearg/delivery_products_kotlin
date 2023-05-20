package ru.app.fastestdelivery.presentation.main.home.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.app.fastestdelivery.R
import ru.app.fastestdelivery.databinding.ProductCardBinding
import ru.app.fastestdelivery.domain.models.Product

class HomeProductViewHolder(private val binding: ProductCardBinding) : RecyclerView.ViewHolder(binding.root) {

    private val context = binding.root.context

    fun bind(
        item: Product,
        onProductClicked: (Product) -> Unit,
    ) = with(binding) {
        productCardName.text = item.name
        productCardWeight.text = item.weight.toString() + " гр."
        productCardPrice.text = item.price.toString() + '₽'
        productCardPlus.setOnClickListener { onProductClicked.invoke(item) }

        Glide.with(context)
            .load(item.photoUrl)
            .into(productCardImage);
    }

    companion object {
        fun from(parent: ViewGroup) = HomeProductViewHolder(
            ProductCardBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

}