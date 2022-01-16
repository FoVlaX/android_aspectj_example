package com.example.viewpagerproject

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.viewpagerproject.cards.TestCard
import com.example.viewpagerproject.cards.TestCardList
import com.example.viewpagerproject.databinding.ActivityMainBinding
import com.example.viewpagerproject.pojo.Card
import com.example.viewpagerproject.viewmodel.MainViewModel
import java.util.*


class MainActivity : ComponentActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private val pageChangeListener = object : ViewPager2.OnPageChangeCallback() {

        private val offscreenPageLimit
            get() = 2

        private val removedSet = HashSet<Long>()

        fun requestRemoveItemOnPositionWhenItemOffscreen(
            itemId: Int
        ) {
            removedSet.add(itemId.toLong())
        }

        override fun onPageSelected(position: Int) {
            val prevRemPos = position - offscreenPageLimit
            val nextRemPos = position + offscreenPageLimit
            val prevRemId = viewPagerAdapter.getItemId(prevRemPos)
            val nextRemId = viewPagerAdapter.getItemId(nextRemPos)
            if (removedSet.contains(prevRemId)) {
                removedSet.remove(prevRemId)
                viewPagerAdapter.removeFragment(prevRemPos)
            }
            if (removedSet.contains(nextRemId)) {
                removedSet.remove(nextRemId)
                viewPagerAdapter.removeFragment(nextRemPos)
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { 
            MainView()
        }
        initViewModel()
        viewModel.networkRequest()
    }
    
    @Composable
    fun MainView() {
        TestCardList(
            cards = generateCards()
        )
    }

    private fun generateCards(): List<Card> {
        return mutableListOf<Card>().apply {
            (0 until 25).forEach {
                add(
                    Card(
                        testField1 = "title $it",
                        testField2 = "description $it"
                    )
                )
            }
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(
            MainViewModel::class.java
        )
    }

    private fun initViewPager() {
        /*viewPagerAdapter = ViewPagerAdapter(
            this
        )
        binding.viewPager.adapter = viewPagerAdapter
        binding.viewPager.orientation = ViewPager2.ORIENTATION_VERTICAL
        viewModel.onRemoveButtonClickLiveData.observe(this) { itemId ->
            pageChangeListener.requestRemoveItemOnPositionWhenItemOffscreen(
                itemId
            )
        }
        binding.viewPager.registerOnPageChangeCallback(pageChangeListener)
        */
    }
}