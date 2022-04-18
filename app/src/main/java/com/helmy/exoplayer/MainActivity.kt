package com.helmy.exoplayer

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.extractor.ExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelector
import com.google.android.exoplayer2.ui.SimpleExoPlayerView
import com.google.android.exoplayer2.upstream.BandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory

class MainActivity : AppCompatActivity() {

    lateinit var exoPlayerView: SimpleExoPlayerView

    // creating a variable for exoplayer
     lateinit var exoPlayer: SimpleExoPlayer

    // url of video which we are loading.
    private var videoURL ="https://firebasestorage.googleapis.com/v0/b/courses-app-5c3b2.appspot.com/o/Videos%2FScreenrecorder-2022-04-18-06-06-09-890.mp4?alt=media&token=25939530-aa0d-412b-85f2-e038e5a6538b"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        exoPlayerView = findViewById(R.id.idExoPlayerVIew)
        try {
            val bandwidthMeter: BandwidthMeter =  DefaultBandwidthMeter()
            val trackSelector: TrackSelector =  DefaultTrackSelector( AdaptiveTrackSelection.Factory(bandwidthMeter));
            exoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector)
            val videouri: Uri = Uri.parse(videoURL)
            val dataSourceFactory =  DefaultHttpDataSourceFactory("exoplayer_video")
            val extractorsFactory: ExtractorsFactory =  DefaultExtractorsFactory()
            val mediaSource: MediaSource =  ExtractorMediaSource(videouri, dataSourceFactory, extractorsFactory, null, null);
            exoPlayerView.setPlayer(exoPlayer)
            exoPlayer.prepare(mediaSource)
            exoPlayer.setPlayWhenReady(true)

        } catch (e:Exception) {
            // below line is used for
            // handling our errors.
            Log.e("TAG", "Error : $e")
        }
    }

}