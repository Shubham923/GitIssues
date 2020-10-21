package com.shubham.gitissues.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.shubham.gitissues.R
import com.shubham.gitissues.app.App
import com.shubham.gitissues.model.response.GitRepoResponse
import com.shubham.gitissues.ui.GitRepoAdapter
import com.shubham.gitissues.viewmodel.AllGitReposViewModel
import kotlinx.android.synthetic.main.content_all_gitissues.*
import kotlinx.android.synthetic.main.content_all_gitissues.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GitRepoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GitRepoFragment : Fragment(), GitRepoAdapter.ContentListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var viewModel: AllGitReposViewModel
    private lateinit var adapter : GitRepoAdapter

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


        val view : View = inflater.inflate(R.layout.fragment_git_repo, container, false)

       // adapter = GitRepoAdapter { viewModel.retry() }
        adapter = GitRepoAdapter(this)
        //val repoName: String? = intent.getStringExtra("REPOSITORY_NAME")
        viewModel = ViewModelProviders.of(this).get(AllGitReposViewModel::class.java)
        view.gitIssuesRecyclerView.layoutManager = LinearLayoutManager(this.context)
        view.gitIssuesRecyclerView.adapter = adapter
        viewModel.repoList.observe(this, Observer {
            //adapter.updateGitIssues(gitIssues)
            // Log.d("List", it[0]?.title.toString())
            adapter.submitList(it)
        })
        return view
    }

    companion object {

        fun newInstance() : GitRepoFragment {

            return GitRepoFragment()
        }
    }

    override fun onItemClicked(gitRepoResponse: GitRepoResponse) {

        App.setRepositoryName(gitRepoResponse.title.toString())
        val transaction = fragmentManager?.beginTransaction()?.replace(R.id.root_layout, GitIssueFragment.newInstance(),"GitIssueFrag")
            ?.addToBackStack("GitIssueFrag")?.commit()
        //Toast.makeText(this.context, "Hey i am called", Toast.LENGTH_LONG).show()
    }
}