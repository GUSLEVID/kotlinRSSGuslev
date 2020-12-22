package com.example.kotlinrssguslev.Adapter

import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.net.MailTo.parse
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinrssguslev.Interface.ItemClickListener
import com.example.kotlinrssguslev.R
import com.example.kotlinrssguslev.model.RSSObject
import java.net.HttpCookie.parse
import java.net.URL
import java.util.logging.Level.parse


class FeedViewHolder(itemView: View):RecyclerView.ViewHolder(itemView),View.OnClickListener,View.OnLongClickListener
{
    var txtTitle:TextView
    var txtPubdate:TextView
    var txtContent:TextView

   private var itemClickListener : ItemClickListener?=null

    init {
        txtTitle = itemView.findViewById(R.id.txtTitle) as TextView
        txtPubdate = itemView.findViewById(R.id.txtPubdate) as TextView
        txtContent = itemView.findViewById(R.id.txtContent) as TextView

        itemView.setOnClickListener(this)
        itemView.setOnLongClickListener(this)
    }
    fun setItemClickListener(itemClickListener: ItemClickListener)
    {
        this.itemClickListener = itemClickListener
    }
    override fun onClick(p0: View?) {
      itemClickListener!!.onClick(p0,adapterPosition,false)
    }

    override fun onLongClick(p0: View?): Boolean {
        itemClickListener!!.onClick(p0,adapterPosition,true)
        return true
    }

}

//class FeedAdapter(private val rssObject: RSSObject, private val mContext:Context):RecyclerView.Adapter<FeedViewHolder>()
class FeedAdapter(private val rssObject: RSSObject,private val mContext:Context):RecyclerView.Adapter<FeedViewHolder>()
{

    private val inflater:LayoutInflater

    init {
        inflater = LayoutInflater.from(mContext)
    }


    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {

        holder.txtTitle.text = rssObject.items[position].htmlText
        holder.txtContent.text = rssObject.items[position].htmlText
        holder.txtPubdate.text = rssObject.items[position].htmlText

        holder.setItemClickListener(ItemClickListener{view, position,isLongList ->
            if(!isLongList)
            {
                val browserIntent = Intent(Intent.ACTION_VIEW,Uri.parse(rssObject.items[position].htmlText)) // разобраться с кодом линк титле и т.д
                mContext.startActivity(browserIntent)
            }
        })

    }

    override fun getItemCount(): Int {
     return rssObject.items.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val itemView = inflater.inflate(R.layout.row,parent,false)
        return FeedViewHolder(itemView)
    }

    fun notifyDataSetChanget() {
        TODO("Not yet implemented")
    }

}