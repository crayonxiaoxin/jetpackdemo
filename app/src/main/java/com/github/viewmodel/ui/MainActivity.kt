package com.github.viewmodel.ui

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.github.viewmodel.R
import kotlinx.android.synthetic.main.fragment_home.*

class MainActivity : AppCompatActivity() {
    //        lateinit var helloViewModel: HelloViewModel
//    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initImmersiveStatusBar()
        setContentView(R.layout.activity_main)
//        setSupportActionBar(toolBar)
//        setupActionBarWithNavController(this, findNavController(R.id.nav_host_fragment))

        getSharedPreferences("user", 0).edit {

        }

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

//    override fun onSupportNavigateUp(): Boolean {
//        return findNavController(R.id.nav_host_fragment).navigateUp()
//    }

    protected fun initImmersiveStatusBar() {
        with(window) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0之上
                addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                statusBarColor = getColor(R.color.transparent)
            }
        }
    }
}