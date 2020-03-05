package com.tech.developer

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_dynamic.view.*

class DynamicFragment(var color: String?, var number:Int) : Fragment() {



    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_dynamic,container,false)

        view.txt_frag.text = "$number"
        view.setBackgroundColor(Color.parseColor(color))

        return view
    }



}