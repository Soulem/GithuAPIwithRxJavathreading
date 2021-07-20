package com.example.githuapiwithrxjavathreading.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.githuapiwithrxjavathreading.databinding.FragmentSearchLayoutBinding
import com.example.githuapiwithrxjavathreading.model.data.github.user.GitRetrofitUser
import com.example.githuapiwithrxjavathreading.utl.GitAPISelector
import com.example.githuapiwithrxjavathreading.view.adapter.UserRecyclerDisplayAdapter
import com.example.githuapiwithrxjavathreading.viewmodel.ObjectViewModel

class SearchLayoutFragment() : Fragment(), UserRecyclerDisplayAdapter.GitAPIUserDelegate {
    companion object {
        lateinit var searchDisplayItemFragment: SearchLayoutFragment
        const val RESULT_KEY = "SEARCH_RESULT_KEY"
        fun getInstance(gitAPISelector: GitAPISelector): SearchLayoutFragment{
            if(!this::searchDisplayItemFragment.isInitialized)// checking if lateinit property has been initialized
                searchDisplayItemFragment = SearchLayoutFragment()
            searchDisplayItemFragment.setSelector(gitAPISelector)
            return searchDisplayItemFragment
        }
    }

    private lateinit var binding: FragmentSearchLayoutBinding
    private val adapter = UserRecyclerDisplayAdapter(this)
    private lateinit var gitAPISelector : GitAPISelector

    fun setSelector(gitAPISelector: GitAPISelector){
        this.gitAPISelector = gitAPISelector
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchLayoutBinding.inflate(layoutInflater)
        binding.userListRecyclerview.adapter = adapter
        ObjectViewModel.instance.searchUser()
        ObjectViewModel.instance.gitUsersData.observe(viewLifecycleOwner, {
            adapter.apiList = it
        })

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchUserButton.setOnClickListener{
            val name : String = binding.userNameEditText.text.toString()
            ObjectViewModel.instance.searchUser(name)
            ObjectViewModel.instance.gitUserData.observe(viewLifecycleOwner, {
                adapter.apiList = listOf(it)
            })
        }
    }

    override fun selectItem(gitRetrofitItem: GitRetrofitUser) {
        gitAPISelector.openUserDetailsFragment(gitRetrofitItem)
    }
}