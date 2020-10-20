package com.github.viewmodel.ui

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.github.viewmodel.R
import com.github.viewmodel.adapter.PhotosAdapter
import com.github.viewmodel.databinding.FragmentHomeBinding
import com.github.viewmodel.vm.PhotoViewModel
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.fragment_home.*
import kotlin.math.absoluteValue

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
        viewModel.loadPhotos()
        val photoAdapter = PhotosAdapter()
        val sLayoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
        with(binding.photoRecyclerView) {
            layoutManager = sLayoutManager
            adapter = photoAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    sLayoutManager.invalidateSpanAssignments()
                }
            })
        }
        viewModel.photos.observe(viewLifecycleOwner) { list ->
            photoAdapter.submitList(list)
        }

        collapsingToolbar.title = "Hello Material"
//        toolBar.setNavigationIcon(R.drawable.ic_action_name)
//        toolBar.setNavigationOnClickListener {
//            Toast.makeText(context, "back???", Toast.LENGTH_SHORT).show()
//        }
//        to_fragment2.setOnClickListener {
//            val action =
//                HomeFragmentDirections.actionBlankFragmentToBlankFragment2()
//                    .apply { name = "hello nav" }
//            view.findNavController().navigate(action)
//            Log.e("TAG", "onViewCreated: Kotlin")
//        }
        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            Log.e(
                "Offset",
                "offset = $verticalOffset , top = ${expand_title.top} , calc = ${60f.dp2px()} , total = ${appBarLayout.totalScrollRange}"
            )
            val offsetAbs = verticalOffset.absoluteValue
            val scrollRange = appBarLayout.totalScrollRange
            when {
                offsetAbs == scrollRange -> {
                    collapse_title.visibility = View.VISIBLE
                    collapse_title.alpha = 1f
                }
                offsetAbs >= scrollRange / 4 * 3 -> {
                    collapse_title.alpha =
                        0.8f * (offsetAbs - scrollRange / 2) / (scrollRange / 2)
                    collapse_title.visibility = View.VISIBLE
                }
                else -> {
                    collapse_title.visibility = View.GONE
                }
            }
            expand_title.translationY = verticalOffset.toFloat() / 2
            val alpha = 1f - offsetAbs.toFloat() / scrollRange.toFloat()
            Log.e("Offset", "alpha = $alpha")
            expand_title.alpha = alpha
        })


    }

    fun Float.dp2px(): Int {
        val scale: Float = Resources.getSystem().displayMetrics.density
        return (this * scale + 0.5f).toInt()
    }

}