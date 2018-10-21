package com.bhuvan.multipanedemo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_detail.*

class MyDetailFragment: Fragment() {

    private var selectedItem: String? = null

    companion object {
        fun newInstance(desc: String): MyDetailFragment {
            return MyDetailFragment().apply {
                selectedItem = desc
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity?.setTitle(selectedItem)

        testViewDescription.text = "$selectedItem description"
    }
}