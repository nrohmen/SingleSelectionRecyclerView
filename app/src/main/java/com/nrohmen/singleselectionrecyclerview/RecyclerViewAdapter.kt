package com.nrohmen.singleselectionrecyclerview

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.nrohmen.singleselectionrecyclerview.R.id.item_image
import com.nrohmen.singleselectionrecyclerview.R.id.item_name
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by root on 1/16/18.
 */
class RecyclerViewAdapter(private val items: List<Item>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = items[position]
        initializeViews(model, holder, position)
    }

    private var lastCheckedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(ItemUI().createView(AnkoContext.create(parent.context, parent)))
    }


    private fun initializeViews(model: Item, holder: RecyclerView.ViewHolder, position: Int) {
        holder as ItemViewHolder
        model.image?.let { holder.image.setImageResource(it) }
        holder.name.text = model.name

        if (position == lastCheckedPosition) {
            holder.image.setColorFilter(ContextCompat.getColor(holder.itemView.context, R.color.colorAccent), android.graphics.PorterDuff.Mode.SRC_IN)
        } else{
            holder.image.setColorFilter(ContextCompat.getColor(holder.itemView.context, R.color.colorSecondary), android.graphics.PorterDuff.Mode.SRC_IN)
        }

        holder.itemView.onClick {
            lastCheckedPosition = position
            notifyItemRangeChanged(0, items.size)
        }
    }

    override fun getItemCount(): Int = items.size


    class ItemUI : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui) {
                verticalLayout {
                    lparams(width = wrapContent, height = wrapContent)
                    padding = dip(16)
                    gravity = Gravity.CENTER_HORIZONTAL

                    imageView {
                        id = R.id.item_image
                    }.lparams{
                        height = dip(30)
                        width = dip(30)
                    }

                    textView {
                        id = R.id.item_name
                        textSize = 14f
                    }.lparams{
                        margin = dip(5)
                    }

                }
            }
        }
    }



    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val name: TextView = view.find(item_name)
        val image: ImageView = view.find(item_image)
    }
}