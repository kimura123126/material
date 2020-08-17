package com.example.dell1.materialtest

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

/**
 * Created by DELL1 on 2019/1/31.
 */
class FruitAdapter(private val mFruitList: List<Fruit>) :
    RecyclerView.Adapter<FruitAdapter.ViewHolder?>() {
    private var mContext: Context? = null

   inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var cardView: CardView
        var fruitImage: ImageView
        var fruitName: TextView

        init {
            cardView = view as CardView
            fruitImage = view.findViewById(R.id.fruit_image) as ImageView
            fruitName = view.findViewById(R.id.fruit_name) as TextView
        }
    }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (mContext == null) {
            mContext = parent.context
        }
        val view: View = LayoutInflater.from(mContext).inflate(R.layout.fruit_item, parent, false)
        val holder = ViewHolder(view)

        holder.cardView.setOnClickListener {//给fruit_item.xml最外层布局注册了一个事件点击事件监听器
            val position: Int = holder.adapterPosition
            val fruit = mFruitList[position]
            val intent = Intent(mContext, FruitActivity::class.java)
            intent.putExtra(FruitActivity.FRUIT_NAME, fruit.name)
            intent.putExtra(FruitActivity.FRUIT_IMAGE_ID, fruit.imageId)
            mContext?.startActivity(intent)
        }
       return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruit = mFruitList[position]
        holder.fruitName.text = fruit.name //设置文字
        //使用Glide加载图片,包括图片压缩，直接显示原图可能内存溢出
        //load(参数)可以是URL地址，本地路径，或资源id
        mContext?.let { Glide.with(it).load(fruit.imageId).into(holder.fruitImage) }
    }


    override fun getItemCount()= mFruitList.size

}