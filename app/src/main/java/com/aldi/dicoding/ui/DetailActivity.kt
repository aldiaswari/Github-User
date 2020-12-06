package com.aldi.dicoding.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.view.View

import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.aldi.dicoding.R

import com.aldi.dicoding.model.User
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.makeramen.roundedimageview.RoundedImageView
import com.valdesekamdem.library.mdtoast.MDToast
import de.hdodenhof.circleimageview.CircleImageView
import java.io.UnsupportedEncodingException

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USER = "extra_user"
    }

    lateinit var mUser: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val avatarNih: CircleImageView = findViewById(R.id.img_avatar_recieved)
        val usernameNih: TextView = findViewById(R.id.tv_username_recieved)
        val nameNih: TextView = findViewById(R.id.tv_name_recieved)
        val locationNih: TextView = findViewById(R.id.tv_location_recieved)
        val companyNih: TextView = findViewById(R.id.tv_company_recieved)
        val repositoryNih: TextView = findViewById(R.id.tv_repository_recieved)
        val followersNih: TextView = findViewById(R.id.tv_followers_recieved)
        val followingNih: TextView = findViewById(R.id.tv_following_recieved)
        val headerAvatar: RoundedImageView = findViewById(R.id.header_avatar)
        val user = intent.getParcelableExtra<User>(EXTRA_USER)
        usernameNih.text = user?.username
        nameNih.text = user?.name
        locationNih.text = user?.location
        companyNih.text = user?.company
        repositoryNih.text = user?.repository
        followersNih.text = user?.followers
        followingNih.text = user?.following
        Glide.with(this)
            .load(user?.avatar)
            .apply(RequestOptions())
            .into(avatarNih)
        Glide.with(this)
            .load(user?.avatar)
            .apply(RequestOptions())
            .into(headerAvatar)

        val imgBack: ImageView = findViewById(R.id.btn_back)
        imgBack.setOnClickListener {
            onBackPressed()
        }
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

    fun shareDong(view: View) {
        val user= mUser
        try {
            val text = """
                    I'm using the GitHub User Application and i found :
                    
                    ${user.username} 
                    work at ${user.company} 
                    and located at ${user.location}.
                    
                    Install the GitHub App!
                """.trimIndent()
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("http://api.whatsapp.com/send?phone=&text=$text")
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
            MDToast.makeText(this,ready("T29vb3Bwc3NzISBQbGVhc2UgZG93bmxvYWQgV2hhdHNBcHAgQXBwbGljYXRpb24="),MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR).show()
        }
    }


}