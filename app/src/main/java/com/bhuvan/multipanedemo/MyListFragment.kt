package com.bhuvan.multipanedemo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_list.*

class MyListFragment: Fragment() {

    public lateinit var itemSelected: (String)->Unit

    companion object {
        fun newInstance() = MyListFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity?.setTitle("My List")

        val data = arrayOf("item1", "item2","item3", "item4", "item5", "item6", "item7", "item8", "item9", "item10")
        val arrayAdapter = ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1, android.R.id.text1, data)
        my_list.adapter = arrayAdapter

        my_list.setOnItemClickListener { _, _, position, _ ->
            itemSelected(data[position])
        }
    }
}