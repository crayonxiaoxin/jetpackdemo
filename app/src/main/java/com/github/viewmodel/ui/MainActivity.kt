package com.github.viewmodel.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.github.viewmodel.R

class MainActivity : AppCompatActivity() {
//        lateinit var helloViewModel: HelloViewModel
//    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupActionBarWithNavController(this, findNavController(R.id.nav_host_fragment))

//        helloViewModel = ViewModelProvider(
//            this,
//            ViewModelProvider.NewInstanceFactory()
//        ).get(HelloViewModel::class.java)
//
//        binding = (DataBindingUtil.setContentView(
//            this,
//            R.layout.activity_main
//        ) as ActivityMainBinding).apply {
//            vm = helloViewModel
//            lifecycleOwner = this@MainActivity
//        }

    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp()
    }

}