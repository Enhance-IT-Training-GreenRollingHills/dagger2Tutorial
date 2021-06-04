/*
 * Copyright (c) 2017 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.cc.diraytutorial.ui.homepage

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.Html
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.text.HtmlCompat
import androidx.core.text.parseAsHtml
import com.cc.diraytutorial.R
import com.cc.diraytutorial.application.WikiApplication
import com.cc.diraytutorial.databinding.ActivityHomepageBinding
import com.cc.diraytutorial.model.WikiHomepage
import com.cc.diraytutorial.ui.search.SearchActivity
import com.cc.diraytutorial.util.LogToConsole
import com.raywenderlich.android.droidwiki.utils.errorDialog
import com.raywenderlich.android.droidwiki.utils.start
import org.xml.sax.XMLReader
import javax.inject.Inject

class HomepageActivity : AppCompatActivity(), HomepageView {

  @Inject
  lateinit var presenter: HomepagePresenter

  private lateinit var binding : ActivityHomepageBinding




  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityHomepageBinding.inflate(layoutInflater)
    setContentView(binding.root)

    //setContentView(R.layout.activity_homepage)

    (application as WikiApplication).wikiComponent.inject(this)

    presenter.setView(this)
    presenter.loadHomepage()

  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.homepage, menu)

    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem) =
      when (item.itemId) {
        R.id.search -> {
          SearchActivity::class.start(this)
          true
        }

        else -> {
          super.onOptionsItemSelected(item)
        }
      }

  // Implementation of HomepageView

  override fun displayLoading() {
    /*wait_progress_bar.post {
      wait_progress_bar.visibility = View.VISIBLE
      homepage_sv.visibility = View.GONE
    }*/

    binding.waitProgressBar.post {
      binding.waitProgressBar.visibility = View.VISIBLE
      binding.homepageSv.visibility = View.GONE
    }

  }

  override fun dismissLoading() {
    /*wait_progress_bar.post {
      wait_progress_bar.visibility = View.GONE
      homepage_sv.visibility = View.VISIBLE
    }*/

    binding.waitProgressBar.post {
      binding.waitProgressBar.visibility = View.GONE
      binding.homepageSv.visibility = View.VISIBLE
    }
  }

  @RequiresApi(Build.VERSION_CODES.N)
  override fun displayHomepage(result: WikiHomepage) {
    /*homepage_tv.post {
      homepage_tv.text = result.htmlContent.parseHtml()
    }*/

    binding.homepageTv.post {

      binding.homepageTv.text = result.htmlContent.parseAsHtml()
      //binding.homepageTv.text = Html.fromHtml(result.htmlContent, Html.FROM_HTML_MODE_COMPACT)

    }
  }

  override fun displayError(error: String?) {
    /*Log.e("ERROR", error)
    runOnUiThread {
      R.string.error.errorDialog(this)
    }*/

    error?.let {
      runOnUiThread {
        R.string.error.errorDialog(this)
      }
    }
  }
}
