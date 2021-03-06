package com.shubham.gitissues.ui

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.shubham.gitissues.R
import com.shubham.gitissues.app.inflate
import com.shubham.gitissues.model.GitIssues.GitIssue
import com.shubham.gitissues.model.GitRepos.GitRepo
import com.shubham.gitissues.model.State
import com.shubham.gitissues.model.response.GitIssueResponse
import com.shubham.gitissues.model.response.GitRepoResponse
import kotlinx.android.synthetic.main.list_item_gitissue.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.InputStream
import java.lang.Exception
import java.net.URL

class GitRepoAdapter(/*private val retry: ()-> Unit*/val listener: ContentListener)
    : PagedListAdapter<GitRepoResponse, RecyclerView.ViewHolder>(NewsDiffCallback){

    private var state = State.LOADING
    //lateinit var listener: ContentListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.list_item_gitissue))
    }

    override fun getItemCount() : Int {
        return super.getItemCount()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //holder.bind(gitIssues[position])
        getItem(position)?.let { (holder as ViewHolder).bind(it,listener) }
    }

    /*fun updateGitIssues(gitIssues: List<GitIssueResponse>) {
        this.gitIssues.clear()
        this.gitIssues.addAll(gitIssues)
        notifyDataSetChanged()
    }*/

    companion object {
        val NewsDiffCallback = object : DiffUtil.ItemCallback<GitRepoResponse>() {
            override fun areItemsTheSame(oldItem: GitRepoResponse, newItem: GitRepoResponse): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: GitRepoResponse, newItem: GitRepoResponse): Boolean {
                return oldItem == newItem
            }
        }
    }

    fun setState(state : State) {
        this.state = state
        notifyItemChanged(super.getItemCount())
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var gitRepo: GitRepoResponse

//        init {
//            itemView.setOnClickListener(this)
//        }

        fun bind(x: GitRepoResponse, listener : ContentListener) {
            gitRepo = x
            itemView.title.text = x.title
            GlobalScope.launch(context = Dispatchers.IO) {
                try {

                    val iss: InputStream =
                        URL(x.user?.avatar_url.toString()).getContent() as InputStream
                    val d = Drawable.createFromStream(iss, "src name")
                    launch(Dispatchers.Main) {
                        itemView.avatarListItem.setImageDrawable(d)
                    }
                } catch (e : Exception) {
                    itemView.avatarListItem.setImageResource(R.drawable.list_icons)
                }
            }

            itemView.setOnClickListener {
                listener.onItemClicked(x)
                Log.d("This Exe", "Exe")
            }
        }

        /*override fun onClick(v: View?) {

            v?.let {
                val context = it.context
                val intent = GitRepoActivity.switchIntent(context,
                    gitRepo.title
                )
                context.startActivity(intent)
            }
        }*/
    }

    public interface ContentListener {
        fun onItemClicked(x: GitRepoResponse)
    }

}