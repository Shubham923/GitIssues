package com.shubham.gitissues.ui

import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shubham.gitissues.R
import com.shubham.gitissues.app.inflate
import com.shubham.gitissues.model.response.GitRepoResponse
import kotlinx.android.synthetic.main.list_item_gitissue.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.InputStream
import java.lang.Exception
import java.net.URL

class GitRepoAdapter(private val gitRepos : MutableList<GitRepoResponse>)
    : RecyclerView.Adapter<GitRepoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.list_item_gitissue))
    }


    override fun getItemCount() = gitRepos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(gitRepos[position])


    }

    fun updateGitIssues(gitRepos: List<GitRepoResponse>) {
        this.gitRepos.clear()
        this.gitRepos.addAll(gitRepos)
        notifyDataSetChanged()
    }
    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private lateinit var gitRepo: GitRepoResponse

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(x: GitRepoResponse) {
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
        }

        override fun onClick(v: View?) {

            v?.let {
                val context = it.context
                val intent = GitRepoActivity.switchIntent(context, gitRepo.title)
                context.startActivity(intent)
            }
        }
    }

}