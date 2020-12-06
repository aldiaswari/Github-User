package com.aldi.dicoding.ui

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.aldi.dicoding.R
import com.aldi.dicoding.adapter.UserAdapter
import com.aldi.dicoding.model.User
import com.aldi.dicoding.utils.CheckInternetConnection
import com.facebook.shimmer.ShimmerFrameLayout
import kotlinx.android.synthetic.main.layout_main_content.*
import org.json.JSONException
import org.json.JSONObject
import timber.log.Timber
import java.io.IOException
import java.io.UnsupportedEncodingException
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: UserAdapter
    private lateinit var shimmerViewContainer: ShimmerFrameLayout
    private var users = ArrayList<User>()
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPreferences = getSharedPreferences(ready("R2l0aHVi"), Context.MODE_PRIVATE)
        CheckInternetConnection(this).checkConnection()
        shimmerViewContainer = shimmer_view_container
        rv()
        addItem()
    }

    private fun rv() {
        rv_user.setHasFixedSize(true)
        adapter = UserAdapter()
        adapter.notifyDataSetChanged()
        rv_user.layoutManager = LinearLayoutManager(this)
        rv_user.adapter = adapter

    }

    fun ready(message: String?): String? {
        val data: ByteArray = Base64.decode(message, Base64.DEFAULT)
        try {
            return String(data, charset("UTF-8"))
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
        return null
    }

    private fun addItem() {
        users = ArrayList()
        try {
            val obj = JSONObject(loadJSONFromAsset(this)!!)
            val arr = obj.getJSONArray(ready("dXNlcnM="))
            for (i in 0 until arr.length()) {
                val `object` = arr.getJSONObject(i)
                val username = `object`.getString(ready("dXNlcm5hbWU="))
                val name = `object`.getString(ready("bmFtZQ=="))
                val image = `object`.getString(ready("YXZhdGFy"))
                val company = `object`.getString(ready("Y29tcGFueQ=="))
                val location = `object`.getString(ready("bG9jYXRpb24="))
                val repository = `object`.getString(ready("cmVwb3NpdG9yeQ=="))
                val followers = `object`.getString(ready("Zm9sbG93ZXI="))
                val following = `object`.getString(ready("Zm9sbG93aW5n"))
                val resourceId =resources.getIdentifier(image, ready("ZHJhd2FibGU="), this.getPackageName())
                val user = User()
                user.username = username
                user.name = name
                user.avatar = resourceId
                user.company = company
                user.location = location
                user.repository = repository
                user.followers = followers
                user.following = following
                users.add(user)
                sharedPreferences.edit().putString(ready("dXNlcm5hbWU="), username)
                    .putString(ready("bmFtZQ=="), name)
                    .putString(ready("Y29tcGFueQ=="), location)
                    .putString(ready("Y29tcGFueQ=="), company)
                    .putString(ready("cmVwb3NpdG9yeQ=="), repository)
                    .putString(ready("Zm9sbG93ZXI="), followers)
                    .putString(ready("Zm9sbG93aW5n"), following)
                    .apply()
                Timber.d(ready("PT09bG9hZCBydj09PQ=="))
            }

        } catch (ex: JSONException) {
            ex.printStackTrace()
        }


        shimmerViewContainer.stopShimmerAnimation()
        shimmerViewContainer.visibility = View.GONE
        adapter.setData(users)
        adapter.notifyDataSetChanged()
    }

    fun loadJSONFromAsset(context: Context): String? {
        var json: String? = null

        try {
            val `is` = context.assets.open("githubuser.json")

            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            json = String(buffer, charset("UTF-8"))

        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }

        return json
    }


    public override fun onResume() {
        super.onResume()
        shimmerViewContainer.startShimmerAnimation()
        Timber.d(ready("PT09c2hpbW1lciBydW5uaW5nPT09"))
    }

    public override fun onPause() {
        shimmerViewContainer.stopShimmerAnimation()
        Timber.d(ready("PT09c2hpbW1lciBzdG9wPT09"))
        super.onPause()
    }
}
