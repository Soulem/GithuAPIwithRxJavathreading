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
        lateinit var userDisplayItemFragment: UserDisplayFragment
        const val RESULT_KEY = "REPO_RESULT_KEY"
        fun getInstance(gitAPIRetrofitItem: GitRetrofitUser): UserDisplayFragment{
            if(!this::userDisplayItemFragment.isInitialized)// checking if lateinit property has been initialized
                userDisplayItemFragment = UserDisplayFragment()

            return userDisplayItemFragment.also {
                it.arguments = Bundle().also { bnd ->
                    bnd.putParcelable(RESULT_KEY, gitAPIRetrofitItem)
                }
            }
        }
    }

    private lateinit var binding: FragmentSearchLayoutBinding
    private lateinit var adapter: UserRecyclerDisplayAdapter
    private lateinit var gitAPISelector: GitAPISelector

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
            ObjectViewModel.instance.gitUserData.observe(viewLifecycleOwner, {
                adapter.apiList = it
            })
        }
    }

    fun setAdapter(adapterRepo: UserRecyclerDisplayAdapter){
        this.adapter = adapterRepo
    }

    override fun selectItem(gitRetrofitItemItem: GitRetrofitUser) {
        TODO("Not yet implemented")
    }
}