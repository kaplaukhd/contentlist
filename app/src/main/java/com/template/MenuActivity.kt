package com.template

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.method.ScrollingMovementMethod
import android.view.View
import androidx.browser.customtabs.CustomTabsIntent
import com.template.databinding.ActivityMenuBinding
import com.template.model.HtmlContainer

lateinit var binding: ActivityMenuBinding
private var currentPosition: Int = 1

class MenuActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.menuToolbar)
        supportActionBar?.isHideOnContentScrollEnabled
        currentPosition = 1
        getContent(currentPosition)



        with(binding) {
            menuBackButton.setOnClickListener(this@MenuActivity)
            menuForwardButton.setOnClickListener(this@MenuActivity)
            menuPlayBtn.setOnClickListener(this@MenuActivity)
            menuBackButton.visibility = View.GONE
            menuContainerHtml.movementMethod = ScrollingMovementMethod()
        }

    }


    private fun getContent(page: Int) {
        if (currentPosition == 1) {
            binding.menuBackButton.visibility = View.GONE
        } else {
            binding.menuBackButton.visibility = View.VISIBLE
        }
        val content = HtmlContainer.getHtml()
        binding.menuContainerHtml.text = Html.fromHtml(content[page - 1].content).toString()
        supportActionBar?.title = "$currentPosition/${HtmlContainer.getHtml().size}"
    }

    private fun getWeb() {
        val url = "https://www.gamezop.com/?id=xyz"
        val customTabsIntent: CustomTabsIntent = CustomTabsIntent.Builder().build()
        customTabsIntent.launchUrl(this, Uri.parse(url))
    }

    override fun onClick(p0: View?) {
        val size = HtmlContainer.getHtml().size

        when (p0?.id) {
            R.id.menuBackButton -> {
                currentPosition--
                getContent(currentPosition)
            }
            R.id.menuForwardButton -> {
                if (currentPosition == size) {
                    currentPosition = 1
                    getContent(currentPosition)
                } else {
                    currentPosition++
                    getContent(currentPosition)
                }
            }
            R.id.menuPlayBtn -> {
                getWeb()
            }
        }
    }

    override fun onBackPressed() {

    }
}