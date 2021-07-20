package com.example.githuapiwithrxjavathreading.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.githuapiwithrxjavathreading.databinding.FragmentDisplayItemBinding
import com.example.githuapiwithrxjavathreading.model.data.github.repo.GitRetrofitUserRepoItem

class DeprecatedDisplayItemFragment : Fragment() {
    companion object {
        lateinit var deprecatedDisplayItemFragment: DeprecatedDisplayItemFragment
        const val RESULT_KEY = "RESULT_KEY"
        fun getInstance(gitAPIRetrofitItem: GitRetrofitUserRepoItem): DeprecatedDisplayItemFragment{
            if(!this::deprecatedDisplayItemFragment.isInitialized)// checking if lateinit property has been initialized
                deprecatedDisplayItemFragment = DeprecatedDisplayItemFragment()

            return deprecatedDisplayItemFragment.also {
                it.arguments = Bundle().also { bnd ->
                    bnd.putParcelable(RESULT_KEY, gitAPIRetrofitItem)
                }
            }
        }
    }

    private lateinit var binding: FragmentDisplayItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDisplayItemBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        arguments?.getParcelable<GitRetrofitUserRepoItem>(RESULT_KEY)?.let{
            binding.gitRepoNameTextView.text = it.name
            binding.gitRepoUrlTextView.text = it.html_url
        }
    }
}
