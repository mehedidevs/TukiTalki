package com.mehedi.tukitalki.ui.home


import android.os.Bundle
import android.view.View
import com.mehedi.tukitalki.R
import com.mehedi.tukitalki.base.BaseFragment
import com.mehedi.tukitalki.databinding.FragmentHomeBinding


class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pagerAdapter = FPagerAdapter(parentFragmentManager)
        binding.fragmentViewpager.adapter = pagerAdapter
        binding.tabLayout.setupWithViewPager(binding.fragmentViewpager)

        binding.tabLayout.getTabAt(0)?.setIcon(R.drawable.ic_round_user_24)
        binding.tabLayout.getTabAt(1)?.setIcon(R.drawable.ic_round_chat_alt_24)
        binding.tabLayout.getTabAt(2)?.setIcon(R.drawable.ic_profile_24)

    }


}

