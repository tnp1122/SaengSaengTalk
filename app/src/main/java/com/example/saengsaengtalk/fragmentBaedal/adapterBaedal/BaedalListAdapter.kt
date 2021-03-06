package com.example.saengsaengtalk.fragmentBaedal.adapterBaedal

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.saengsaengtalk.R
import java.text.DecimalFormat
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList


class BaedalListAdapter(val baedalList: ArrayList<BaedalList>) : RecyclerView.Adapter<BaedalListAdapter.CustomViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lyt_baedal_list, parent, false)
        return CustomViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val arg = baedalList.get(position)
        val dec = DecimalFormat("#,###")
        val text = "%s\n".format(arg.title) + arg.baedaltime.format(DateTimeFormatter.ofPattern("MM/dd(E) HH:mm").withLocale(Locale.forLanguageTag("ko"))) +
                "\n%s\n%d팀\n예상 배달비 %s원".format(arg.shop, arg.member, dec.format(arg.fee/arg.member))
        if (arg.likeUserList.contains("주넝이"))
            holder.iv_like.setImageResource(R.drawable.heart_red)
        else
            holder.iv_like.setImageResource(R.drawable.heart)
        holder.tv_like.text = arg.likeUserList.size.toString()
        holder.tv_viewed.text = arg.viewed.toString()
        holder.tv_content.text = text

        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    private lateinit var itemClickListener : OnItemClickListener

    override fun getItemCount(): Int {
        return baedalList.size
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iv_baedal_list = itemView.findViewById<ImageView>(R.id.img_baedal_list)
        val iv_like = itemView.findViewById<ImageView>(R.id.img_baedal_list_like)
        val tv_like = itemView.findViewById<TextView>(R.id.tv_baedal_list_like)
        val tv_viewed = itemView.findViewById<TextView>(R.id.tv_baedal_list_viewed)
        val tv_content = itemView.findViewById<TextView>(R.id.tv_baedal_list_content)
    }
}