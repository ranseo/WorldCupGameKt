package com.example.worldcupgamekt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment

class ChoiceFragment(val image: Int) : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
     : View? {

        val view = inflater.inflate(R.layout.choice_frag, container, false)
        val choice = view.findViewById<ImageView>(R.id.iv_image)

        choice.setImageResource(image)


        return view
    }

}