package com.nrohmen.singleselectionrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.nrohmen.singleselectionrecyclerview.R.array.icon_array
import com.nrohmen.singleselectionrecyclerview.R.array.name_array
import org.jetbrains.anko.ctx
import org.jetbrains.anko.dip
import org.jetbrains.anko.frameLayout
import org.jetbrains.anko.padding
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivity : AppCompatActivity() {
    private var items: MutableList<Item> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        frameLayout {
            padding = dip(16)
            recyclerView {
                layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.HORIZONTAL, false)
                adapter = RecyclerViewAdapter(items)
            }
        }
    }


    private fun initData(){
        val image = resources.obtainTypedArray(icon_array)
        val name = resources.getStringArray(name_array)
        items.clear()
        for (i in name.indices) {
            items.add(Item(name[i], image.getResourceId(i, 0)))
        }


        //Recycle the typed array
        image.recycle()
    }
}
