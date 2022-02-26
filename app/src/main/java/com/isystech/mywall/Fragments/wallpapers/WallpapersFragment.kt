package com.isystech.mywall.Fragments.wallpapers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.isystech.mywall.databinding.FragmentWallpapersBinding

class WallpapersFragment: Fragment() {
    private var mBinding: FragmentWallpapersBinding? =null
    private val binding get() = requireNotNull(mBinding)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (mBinding==null)
        {
            mBinding= FragmentWallpapersBinding.inflate(inflater,container,false)
            init()
        }
        return binding.root
    }
    private fun init()
    {
        setListeners()
    }
    private fun setListeners()
    {
        binding.imageSearch.setOnClickListener{
            findNavController().navigate(
                WallpapersFragmentDirections.actionWallpapersFragmentToSearchFragment()
            )
        }
    }
}