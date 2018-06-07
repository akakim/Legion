package com.akakim.legion.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import com.akakim.legion.R
import kotlinx.android.synthetic.main.activity_player.*

class PlayerActivity : AppCompatActivity(),SeekBar.OnSeekBarChangeListener,View.OnClickListener {
    override fun onClick(v: View?) {


        when (v?.id){
            R.id.btnPrevSeek->{

            }
            R.id.btnPlay ->{

            }
            R.id.btnPause->{

            }
            R.id.btnNextSeek->{

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContentView(R.layout.activity_player)


        seekBar.setOnSeekBarChangeListener( this )

//        seekBar.setOnHoverListener()

        btnPrevSeek.setOnClickListener( this )
        btnPlay.setOnClickListener( this )
        btnPause.setOnClickListener( this )
        btnNextSeek.setOnClickListener( this )



    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
