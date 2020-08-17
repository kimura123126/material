package com.example.dell1.materialtest

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList
/*
* java转kotlin
* 1.xml布局文件记得修改
*
*
*
*
* */
class MainActivity : AppCompatActivity() {
   // private var mDrawerLayout: DrawerLayout? = null
   // private var swipeRefresh: SwipeRefreshLayout? = null
    private val fruits = arrayOf(
        Fruit("dahu", R.drawable.dahu),
        Fruit("dahu01", R.drawable.dahu01),
        Fruit("dahu02", R.drawable.dahu02),
        Fruit("dahu03", R.drawable.dahu03),
        Fruit("dao", R.drawable.dao),
        Fruit("dao01", R.drawable.dao01),
        Fruit("dao02", R.drawable.dao02),
        Fruit("dao03", R.drawable.dao03),
        Fruit("dao001", R.drawable.dao001),
        Fruit("tutu", R.drawable.tutu),
        Fruit("tutu01", R.drawable.tutu01),
        Fruit("tutu02", R.drawable.tutu02),
        Fruit("a0130", R.drawable.a0130),
        Fruit("a0131", R.drawable.a0131),
        Fruit("a0132", R.drawable.a0132),
        Fruit("dahu1", R.drawable.dahu1),
        Fruit("dahu011", R.drawable.dahu011),
        Fruit("dahu021", R.drawable.dahu021),
        Fruit("dahu031", R.drawable.dahu031),
        Fruit("dao1", R.drawable.dao1),
        Fruit("dao011", R.drawable.dao011),
        Fruit("dao021", R.drawable.dao021),
        Fruit("dao031", R.drawable.dao031),
        Fruit("dao0011", R.drawable.dao0011),
        Fruit("tutu1", R.drawable.tutu1),
        Fruit("tutu011", R.drawable.tutu011),
        Fruit("tutu021", R.drawable.tutu021),
        Fruit("a01301", R.drawable.a01301),
        Fruit("a01311", R.drawable.a01311),
        Fruit("a01321", R.drawable.a01321))

    private val fruitList = ArrayList<Fruit>()
    private var adapter: FruitAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       // val toolbar: Toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

       // swipeRefresh = findViewById<SwipeRefreshLayout>(R.id.swipe_refresh)
        swipe_refresh.setColorSchemeResources(R.color.colorPrimary)
        swipe_refresh.setOnRefreshListener { refreshFruits() }

        initFruits()

       // val recyclerView: RecyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val layoutManager = GridLayoutManager(this, 2)//两列
        recycler_view.layoutManager = layoutManager
        adapter = FruitAdapter(fruitList)
        recycler_view.adapter = adapter


       // val fab: FloatingActionButton = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Data deleted", Snackbar.LENGTH_SHORT)
                .setAction("Undo") {
                    Toast.makeText(this@MainActivity, "Data restoted", Toast.LENGTH_SHORT)
                        .show()
                }.show()
        }

       // mDrawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
       // val navView: NavigationView = findViewById<NavigationView>(R.id.nav_view)

        val actionBar: ActionBar? = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu)
        }

        nav_view.setCheckedItem(R.id.nav_call)
        nav_view.setNavigationItemSelectedListener{
           drawer_layout.closeDrawers()
            true
        }
    }//onCreate

    private fun refreshFruits() {
        Thread(Runnable {
            try {
                Thread.sleep(2000) //InterruptedException漏掉字母n 这一行也会显示红色
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            runOnUiThread(Runnable {
                initFruits()
                adapter?.notifyDataSetChanged()
                swipe_refresh.isRefreshing = false//隐藏进度条
            })
        }).start()
    }

    private fun initFruits() {
        fruitList.clear()
        for (i in 0..49) {
            val random = Random()
            val index: Int = random.nextInt(fruits.size)
            fruitList.add(fruits[index])
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)//加载菜单文件 toolbar.xml
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {//处理菜单按钮点击事件
        when (item.itemId) {
            android.R.id.home -> drawer_layout.openDrawer(GravityCompat.START)//START左侧划出与XML布局文件的一致，Home按钮id永远是android.R.id.home
            R.id.backup -> Toast.makeText(this, "You clicked Backup", Toast.LENGTH_SHORT).show()
            R.id.delete -> Toast.makeText(this, "You clicked Delete", Toast.LENGTH_SHORT).show()
            R.id.settings -> Toast.makeText(this, "You clicked Settings", Toast.LENGTH_SHORT).show()
            else -> {
            }
        }
        return true
    }
}