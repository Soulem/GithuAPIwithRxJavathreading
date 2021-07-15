package com.example.githuapiwithrxjavathreading.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.githuapiwithrxjavathreading.R
import com.example.githuapiwithrxjavathreading.databinding.FragmentRecyclerDisplayLayoutBinding
import com.example.githuapiwithrxjavathreading.databinding.FragmentSearchLayoutBinding
import com.example.githuapiwithrxjavathreading.view.adapter.RecyclerDisplayAdapter
import com.example.githuapiwithrxjavathreading.viewmodel.ObjectViewModel

class SearchLayoutFragment : Fragment() {
    companion object {
    @JvmStatic
    fun newInstance(param1: String, param2: String) =
        SearchLayoutFragment().apply {
        }
}
    private lateinit var binding: FragmentSearchLayoutBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchLayoutBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchUserButton.setOnClickListener{
            ObjectViewModel.instance.searchUser(binding.userNameEditText.text.toString())
            ObjectViewModel.instance.gitAPIData.observe(viewLifecycleOwner, {
                RecyclerDisplayAdapter.instance.apiList = it
            })
        }
    }
}