package com.example.viewpagerproject

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {

    private var items = mutableListOf<Item>().apply {
        (0..10).forEach {
            add(Item(it))
        }
    }

    private val pageIds = items.map { it.hashCode().toLong() }

    override fun getItemCount(): Int = items.size

    override fun createFragment(position: Int): Fragment {

        return ViewPagerFragment.newInstance(
            items[position].id
        )
    }

    override fun getItemId(position: Int): Long {
        return if (position >= 0 && position < items.size) {
            items[position].id.toLong()
        } else {
            -1
        }
    }

    override fun containsItem(itemId: Long): Boolean {
        return pageIds.contains(itemId)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun removeFragment(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

}

data class Item(
    val id: Int
)