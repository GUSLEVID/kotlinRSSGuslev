package com.example.kotlinrssguslev

import android.app.ProgressDialog
import android.os.AsyncTask
import android.os.Bundle
import android.text.PrecomputedText
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinrssguslev.Adapter.FeedAdapter
import com.example.kotlinrssguslev.Common.HTTPDataHandler
import com.example.kotlinrssguslev.model.RSSObject
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    private val RSS_Link = "http://inetinf.ru/news/rss/"
    private val RSS_to_JSON_API = "https://api.rss2json.com/v1/api.json?rss_url="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.title ="NEWS"
        setSupportActionBar(toolbar)

        val linearLayoutManager = LinearLayoutManager(baseContext,LinearLayoutManager.VERTICAL,false)
        recyclerView.layoutManager = linearLayoutManager

        loadRSS()
    }

    private fun loadRSS() {
        val loadRSSAsync = object:AsyncTask<String,String,String>(){
            internal val mDialog = ProgressDialog(this@MainActivity)

            override fun onPreExecute() {
                mDialog.setMessage("Please wait...")
                mDialog.show()
            }

            override fun onPostExecute(result: String?) {
                mDialog.dismiss()
                var rssObject:RSSObject
                rssObject = Gson().fromJson<RSSObject>(result,RSSObject::class.java!!)
                val adapter = FeedAdapter(rssObject,baseContext)
                recyclerView.adapter = adapter
                adapter.notifyDataSetChanget()
            }

            override fun doInBackground(vararg p0: String?): String {
                val result:String
                val http = HTTPDataHandler()
                val params = null
                result = http.GetHHTPDataHandler(params?.get(0))
                return result
            }

        }
        val url_get_data = StringBuilder(RSS_to_JSON_API)
        url_get_data.append(RSS_Link)
        loadRSSAsync.execute(url_get_data.toString())



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_refresh)
            loadRSS()
        return true
    }


}




