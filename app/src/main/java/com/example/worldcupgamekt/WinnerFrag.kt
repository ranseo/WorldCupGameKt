package com.example.worldcupgamekt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment

class WinnerFrag(val image1: Int) : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
     : View? {

        val view = inflater.inflate(R.layout.winner_frag, container, false)
        val winnerImage = view.findViewById<ImageView>(R.id.iv_winnerImage)

        winnerImage.setImageResource(image1)


        return view
    }
}