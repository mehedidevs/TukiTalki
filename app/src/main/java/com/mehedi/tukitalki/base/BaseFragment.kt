package com.mehedi.tukitalki.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding>(

    private var layoutBinding: (inflater: LayoutInflater) -> VB


) : Fragment() {

    private lateinit var _binding: VB

    val binding: VB
        get() = _binding as VB


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = layoutBinding.invoke(layoutInflater)


        return binding.root
    }


}