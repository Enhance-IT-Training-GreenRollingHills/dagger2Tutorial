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

import com.cc.diraytutorial.model.WikiHomepage
import com.cc.diraytutorial.network.Repository
import com.cc.diraytutorial.util.LogToConsole
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

//class HomepagePresenterImpl @Inject constructor(private val homepage:Homepage) : HomepagePresenter, CoroutineScope {
class HomepagePresenterImpl @Inject constructor(private val repo:Repository) : HomepagePresenter, CoroutineScope {

  private lateinit var homepageView: HomepageView

  //private val client: OkHttpClient = OkHttpClient()
  //private val api: WikiApi = WikiApi(client)
  //private val homepage: Homepage = Homepage(api)

  private val job = Job()
  override val coroutineContext: CoroutineContext
    get() = job + Dispatchers.IO

  //private val repo = Repository()

  override fun setView(homepageView: HomepageView) {
    this.homepageView = homepageView
  }

  override fun loadHomepage() {

    homepageView.displayLoading()

    launch (Dispatchers.IO) {
      LogToConsole.log("about to launch homepage")

      val response = repo.getHomepage()

      LogToConsole.log("response : $response")

      homepageView.dismissLoading()

      val wikiHomePage = WikiHomepage(response.parse.text.star)
      homepageView.displayHomepage(wikiHomePage)


    }



    /*homepageView.displayLoading()
    homepage.get().enqueue(object : Callback {
      override fun onResponse(call: Call?, response: Response?) {
        homepageView.dismissLoading()
        if (response?.isSuccessful == true) {
          response.let {
            HomepageResult(it).homepage?.let {
              homepageView.displayHomepage(it)
            } ?: run {
              homepageView.displayError(response.message())
            }
          }
        } else {
          homepageView.displayError(response?.message())
        }
      }

      override fun onFailure(call: Call?, t: IOException?) {
        homepageView.displayError(t?.message)
        t?.printStackTrace()
      }
    })*/
  }

  override fun cleanup() {
    job.cancel()
  }
}