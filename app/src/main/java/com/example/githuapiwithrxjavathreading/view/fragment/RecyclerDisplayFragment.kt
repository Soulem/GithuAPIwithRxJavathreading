package com.example.githuapiwithrxjavathreading.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.example.githuapiwithrxjavathreading.R
import com.example.githuapiwithrxjavathreading.databinding.FragmentRecyclerDisplayLayoutBinding
import com.example.githuapiwithrxjavathreading.view.adapter.RecyclerDisplayAdapter
import com.example.githuapiwithrxjavathreading.viewmodel.ObjectViewModel

class RecyclerDisplayFragment : Fragment() {
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RecyclerDisplayFragment().apply {
            }
    }

    private lateinit var binding: FragmentRecyclerDisplayLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecyclerDisplayLayoutBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerViewDisplay.adapter = RecyclerDisplayAdapter.instance
        ObjectViewModel.instance.gitAPIData.observe(viewLifecycleOwner, {
            RecyclerDisplayAdapter.instance.apiList = it
        })
    }

}