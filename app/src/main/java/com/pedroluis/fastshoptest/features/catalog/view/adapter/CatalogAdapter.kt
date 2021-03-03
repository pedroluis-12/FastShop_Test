package com.pedroluis.fastshoptest.features.catalog.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pedroluis.fastshoptest.BuildConfig
import com.pedroluis.fastshoptest.R
import com.pedroluis.fastshoptest.features.catalog.model.CatalogResult
import com.pedroluis.fastshoptest.features.details.view.activity.DetailActivity
import com.pedroluis.fastshoptest.utils.bindView
import com.pedroluis.fastshoptest.utils.loadImage

class CatalogAdapter (
        private val catalogList: List<CatalogResult>,
        private val context: Context
): RecyclerView.Adapter<ViewHolderCatalog>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolderCatalog(
                    LayoutInflater.from(parent.context)
                            .inflate(R.layout.view_catalog_row, parent, false)
            )

    override fun getItemCount(): Int = catalogList.size

    override fun onBindViewHolder(holder: ViewHolderCatalog, position: Int) {
        val item = catalogList[position]
        holder.catalogPoster.loadImage(item.resultPosterPath)
        holder.catalogText.text = item.resultTitle
        holder.itemView.setOnClickListener {
            context.startActivity(DetailActivity.getLaunchIntent(context, item.resultId))
        }
    }
}

class ViewHolderCatalog(itemView: View): RecyclerView.ViewHolder(itemView) {
    val catalogPoster: ImageView by bindView(R.id.catalog_poster)
    val catalogText: TextView by bindView(R.id.catalog_text)
}