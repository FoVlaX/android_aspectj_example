package com.example.viewpagerproject

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.viewpagerproject.databinding.ViewPagerFragmentLayoutBinding
import com.example.viewpagerproject.viewmodel.MainViewModel

class ViewPagerFragment : Fragment() {


    private lateinit var viewModel: MainViewModel

    private var _binding: ViewPagerFragmentLayoutBinding? = null

    private val binding: ViewPagerFragmentLayoutBinding
        get() = _binding!!

    private var itemId: Int = 0


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ViewPagerFragmentLayoutBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initData()
        initViewModel()
        initViews()
    }

    private fun initData() {
        itemId = arguments?.getInt(ITEM_ID_KEY) ?: 0
    }

    @SuppressLint("SetTextI18n")
    private fun initViews() {
        binding.title.text = "FRAGMENT NUMBER ${itemId + 1}"
        binding.removeButton.setOnClickListener {
            viewModel.clickOnRemoveButton(itemId)
            binding.title.text = "REMOVED"
            binding.removeButton.visibility = View.GONE
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        private const val ITEM_ID_KEY = "position_key"

        fun newInstance(
            itemId: Int
        ): ViewPagerFragment {
            return ViewPagerFragment().apply {
                arguments = Bundle().apply {
                    putInt(ITEM_ID_KEY, itemId)
                }
            }
        }

    }

}