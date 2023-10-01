package com.example.mad_pr6_21012021036

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MyService : Service() {

    lateinit var player: MediaPlayer
    companion object{
        val PLAYERKEY = "service"
        val PLAYERVALUE = "play/pause"
    }



    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if(!this::player.isInitialized)
        {
            player = MediaPlayer.create(this,R.raw.dil)
        }

        if(intent!=null)
        {
            val dataValue = intent.getStringExtra(PLAYERKEY)
            if(dataValue == PLAYERVALUE)
            {
                if(!player.isPlaying)
                {
                    player.start()
                }
                else
                {
                    player.pause()
                }
            }
        }
        else
        {
            player.stop()
        }

        return START_STICKY;
    }
    override fun onDestroy() {
        player.stop()
        super.onDestroy()
    }
}