package com.github.viewmodel.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.github.viewmodel.R
import com.github.viewmodel.adapter.PhotosAdapter
import com.github.viewmodel.databinding.FragmentHomeBinding
import com.github.viewmodel.network.PhotoService
import com.github.viewmodel.vm.PhotoViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    companion object {
        const val TAG = "HomeFragment"
    }

    private val viewModel by viewModels<PhotoViewModel>()
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )
        binding.apply {
            vm = viewModel
            lifecycleOwner = this@HomeFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        GlobalScope.launch(Dispatchers.Main) {
//            withContext(Dispatchers.IO) {
//                doNetWork()
//            }
//        }
        viewModel.doNetWork()
        val photoAdapter = PhotosAdapter()
        with(binding.photoRecyclerView) {
            layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
            adapter = photoAdapter
        }
        viewModel.photos.observe(viewLifecycleOwner) { list ->
            photoAdapter.submitList(list)
        }
        to_fragment2.setOnClickListener {
            val action =
                HomeFragmentDirections.actionBlankFragmentToBlankFragment2()
                    .apply { name = "hello nav" }
            view.findNavController().navigate(action)
            Log.e("TAG", "onViewCreated: Kotlin")
        }

    }

    private suspend fun doNetWork() {
        val apiService = PhotoService()
        try {
            val s = apiService.getPhotos(q = "car", page = 1)
            Log.e(TAG, "doNetWork: " + s.hits)
        } catch (e: Exception) {
            Log.e(TAG, "doNetWork: " + e.message)
        }
    }


}