package com.yyw.pictureselector

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.luck.picture.lib.basic.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.SelectMimeType

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btn_selector).setOnClickListener {
            show()
        }
    }

    fun show() {
        val systemGalleryMode =
            PictureSelector.create(this).openGallery(SelectMimeType.ofImage()).setImageEngine(GlideEngine.createGlideEngine())
                .forResult(PictureConfig.CHOOSE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == PictureConfig.CHOOSE_REQUEST || requestCode == PictureConfig.REQUEST_CAMERA) {
                val result = PictureSelector.obtainSelectorList(data)
                Log.d("wyy", "size:${result.size}")
            }
        } else if (resultCode == RESULT_CANCELED) {
            Log.i("wyy", "onActivityResult PictureSelector Cancel")
        }
    }
}