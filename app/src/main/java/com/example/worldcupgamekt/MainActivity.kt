package com.example.worldcupgamekt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.worldcupgamekt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var mBinding : ActivityMainBinding? = null
    val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            val intent = Intent(this, WorldCupActivity::class.java)
            startActivity(intent)
        }


    }
}