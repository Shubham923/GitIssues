package com.shubham.gitissues.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.shubham.gitissues.R
import com.shubham.gitissues.app.App
import com.shubham.gitissues.model.GitIssues.GitIssue
import com.shubham.gitissues.model.State
import com.shubham.gitissues.model.response.GitIssueResponse
import com.shubham.gitissues.ui.GitIssueAdapter
import com.shubham.gitissues.viewmodel.AllGitIssuesViewModel
import kotlinx.android.synthetic.main.content_all_gitissues.*
import kotlinx.android.synthetic.main.content_all_gitissues.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GitIssueFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GitIssueFragment : Fragment(), GitIssueAdapter.ContentListenerForIssues {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var viewModel : AllGitIssuesViewModel
    private lateinit var adapter : GitIssueAdapter

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

        val view : View = inflater.inflate(R.layout.fragment_git_issue, container, false)

        Log.d("EXE","EXE")
        // GitIssueStore.fetchGitIssues(applicationContext)
        adapter = GitIssueAdapter(this)
        val repoName : String? = /*intent.getStringExtra("REPOSITORY_NAME")*/ App.getRepositoryName()
        viewModel = ViewModelProviders.of(this).get(AllGitIssuesViewModel :: class.java)
        view.gitIssuesRecyclerView.layoutManager = LinearLayoutManager(this.context)
        view.gitIssuesRecyclerView.adapter = adapter
        if (repoName != null) {
            viewModel.issueList.observe(this, Observer {
                //adapter.updateGitIssues(gitIssues)
                // Log.d("List", it[0]?.title.toString())
                adapter.submitList(it)

            })
        }

        adapter.setState(State.DONE)

        return view

    }

    companion object {

        fun newInstance() : GitIssueFragment {
            return GitIssueFragment()
        }
    }

    override fun onItemClicked(gitIssueResponse: GitIssueResponse) {

        val transaction = fragmentManager?.beginTransaction()?.replace(R.id.root_layout, IssueDescFragment.newInstance(),"GitIssueDesc")
            ?.addToBackStack("GitIssueDesc")?.commit()
        val bundle = Bundle()
        bundle.putParcelable("GIT_ISSUE_DESC",
            GitIssue(gitIssueResponse.title,
            gitIssueResponse.description,
            gitIssueResponse.user?.avatar_url))
        Log.d("IssueDesc1", gitIssueResponse?.title.toString())
        bundle.putString("test_string", "Shubham Shinde")

        App.gitIssueDescription = GitIssue(gitIssueResponse.title,
            gitIssueResponse.description,
            gitIssueResponse.user?.avatar_url)

        IssueDescFragment().arguments = bundle
    }
}
