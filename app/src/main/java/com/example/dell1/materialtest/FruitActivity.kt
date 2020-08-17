package com.example.dell1.materialtest

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.google.android.material.appbar.CollapsingToolbarLayout
import kotlinx.android.synthetic.main.activity_fruit.*

class FruitActivity : AppCompatActivity() {
    companion object {
        const val FRUIT_NAME = "fruit_name"
        const val FRUIT_IMAGE_ID = "fruit_image_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruit)


        val fruitName: String = intent.getStringExtra(FRUIT_NAME) ?: ""
        val fruitImageId: Int = intent.getIntExtra(FRUIT_IMAGE_ID, 0)

       // val toolbar: Toolbar = findViewById(R.id.toolbar) as Toolbar
        val collapsingToolbar: CollapsingToolbarLayout =
            findViewById<CollapsingToolbarLayout>(R.id.collapsing_toolbar)

        val fruitImageView: ImageView = findViewById<ImageView>(R.id.fruit_image_view)
        val fruitContentText: TextView = findViewById<TextView>(R.id.fruit_content_text)

        setSupportActionBar(toolbar)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)


        collapsingToolbar.title = fruitName  //设置界面标题
        Glide.with(this).load(fruitImageId).into(fruitImageView)


        fruitContentText.text = generateFruitContent(fruitName)
    }

    private fun generateFruitContent(fruitName: String): String {
        val fruitContent = StringBuilder()
        for (i in 0..499) {
            fruitContent.append(fruitName)
        }
        return fruitContent.toString()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {//home按钮点击事件
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


}