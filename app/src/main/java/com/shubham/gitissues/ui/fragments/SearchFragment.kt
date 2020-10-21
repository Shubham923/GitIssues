package com.shubham.gitissues.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.shubham.gitissues.R
import com.shubham.gitissues.app.App
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_search.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_search, container, false)

        view.search_button.setOnClickListener(View.OnClickListener {
            App.userName = searchByName.text.toString()

            //write intent here..

            val transaction = fragmentManager?.beginTransaction()?.replace(R.id.root_layout, GitRepoFragment.newInstance(), "GitRepoFrag")
                ?.addToBackStack("GitRepoFrag")?.commit()

        })
        return view
    }

    companion object {
        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }
}