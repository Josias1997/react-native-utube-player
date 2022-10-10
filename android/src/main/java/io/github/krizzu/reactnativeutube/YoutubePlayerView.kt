package io.github.krizzu.reactnativeutube

import android.view.ViewGroup
import android.widget.LinearLayout
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.annotations.ReactProp
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class YoutubePlayerView : SimpleViewManager<LinearLayout>() {

    override fun getName() = "YoutubePlayerView"

    private var ytPlayerView: YouTubePlayer? = null
    private var videoId: String? = null

    @ReactProp(name = "videoId")
    fun setVideoId(view: LinearLayout, newId: String?) {
        if (newId == null || newId == videoId) return
        videoId = newId
        ytPlayerView?.loadVideo(newId, 0f)
    }

    override fun createViewInstance(reactContext: ThemedReactContext): LinearLayout {
        val player = YouTubePlayerView(reactContext)
        val layoutParams: ViewGroup.LayoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        val linearLayout = LinearLayout(reactContext)
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.addView(player, layoutParams)
        player.initialize(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                ytPlayerView = youTubePlayer
            }
        })
        return linearLayout
    }
}
