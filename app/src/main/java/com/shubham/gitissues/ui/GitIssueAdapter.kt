package com.shubham.gitissues.ui

import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.shubham.gitissues.R
import com.shubham.gitissues.app.inflate
import com.shubham.gitissues.model.GitIssues.GitIssue
import com.shubham.gitissues.model.State
import com.shubham.gitissues.model.response.GitIssueResponse
import kotlinx.android.synthetic.main.list_item_gitissue.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.InputStream
import java.lang.Exception
import java.net.URL


class GitIssueAdapter(private val retry: ()-> Unit)
    : PagedListAdapter<GitIssueResponse,RecyclerView.ViewHolder>(NewsDiffCallback){

    private var state = State.LOADING

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.list_item_gitissue))
    }

    override fun getItemCount() : Int {
        return super.getItemCount()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //holder.bind(gitIssues[position])
        getItem(position)?.let { (holder as ViewHolder).bind(it) }
    }

    /*fun updateGitIssues(gitIssues: List<GitIssueResponse>) {
        this.gitIssues.clear()
        this.gitIssues.addAll(gitIssues)
        notifyDataSetChanged()
    }*/

    companion object {
        val NewsDiffCallback = object : DiffUtil.ItemCallback<GitIssueResponse>() {
            override fun areItemsTheSame(oldItem: GitIssueResponse, newItem: GitIssueResponse): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: GitIssueResponse, newItem: GitIssueResponse): Boolean {
                return oldItem == newItem
            }
        }
    }

    fun setState(state : State) {
        this.state = state
        notifyItemChanged(super.getItemCount())
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private lateinit var gitIssue: GitIssueResponse

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(x: GitIssueResponse) {
            gitIssue = x
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
        }

        override fun onClick(v: View?) {

            v?.let {
                val context = it.context
                val intent = GitIssueActivity.switchIntent(context,
                    GitIssue(
                        gitIssue.title,
                        gitIssue.description,
                        gitIssue.user?.avatar_url
                    )
                )
                context.startActivity(intent)
            }
        }
    }

}