package com.example.githuapiwithrxjavathreading.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.githuapiwithrxjavathreading.databinding.FragmentDisplayItemBinding
import com.example.githuapiwithrxjavathreading.model.data.github.GitRetrofitItem

class DisplayItemFragment : Fragment() {
    companion object {
        lateinit var displayItemFragment: DisplayItemFragment
        const val RESULT_KEY = "RESULT_KEY"
        fun getInstance(gitAPIRetrofitItem: GitRetrofitItem): DisplayItemFragment{
            if(!this::displayItemFragment.isInitialized)// checking if lateinit property has been initialized
                displayItemFragment = DisplayItemFragment()

            return displayItemFragment.also {
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

        arguments?.getParcelable<GitRetrofitItem>(RESULT_KEY)?.let{
            binding.gitRepoNameTextView.text = it.name
            binding.gitRepoUrlTextView.text = it.html_url
        }
    }
}
