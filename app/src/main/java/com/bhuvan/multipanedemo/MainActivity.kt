package com.bhuvan.multipanedemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentManager

class MainActivity : AppCompatActivity() {

    private var fm: FragmentManager? = null
    private var selectedItem: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fm = supportFragmentManager


        if (resources.getBoolean(R.bool.twoPaneMode)) {
            replaceListFragment(R.id.fragment_container_left)
            savedInstanceState?.let {
                replaceDetailFragment(R.id.fragment_container_right, savedInstanceState.getString("savedItem"))
            } ?: run {
                replaceDetailFragment(R.id.fragment_container_right)
            }
        } else {
            savedInstanceState?.let {
                replaceDetailFragment(R.id.fragment_container, savedInstanceState.getString("savedItem"))
            } ?: run {
                replaceListFragment(R.id.fragment_container)
            }
        }
        System.out.println("log: ${fm?.backStackEntryCount}")
    }

    fun replaceListFragment(containerId: Int) {
        val ft = fm?.beginTransaction()

        val fragment = MyListFragment.newInstance()
        fragment.itemSelected = ::onItemSelected

        ft?.replace(containerId, fragment)
        ft?.commit()
    }

    fun replaceDetailFragment(containerId: Int, previousSavedItem: String = "Item1") {
        val ft = fm?.beginTransaction()

        val fragment = MyDetailFragment.newInstance(previousSavedItem)
        ft?.replace(containerId, fragment)
        ft?.commit()
    }

    fun onItemSelected(item: String) {
        selectedItem = item

        if (resources.getBoolean(R.bool.twoPaneMode)) {
            replaceDetailFragment(R.id.fragment_container_right, item)
        } else {
            replaceDetailFragment(R.id.fragment_container, item)
        }
        System.out.println("log: ${fm?.backStackEntryCount}")
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putString("savedItem", selectedItem ?: "Item1")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        selectedItem = savedInstanceState?.getString("savedItem")
    }
}
