package driver.chao.com.qtan

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.onClick

class ImageActivity : AppCompatActivity() {

    private val selectImageButton by lazy {
        findViewById<Button>(R.id.selectImageButton)
    }
    private val imageView by lazy {
        findViewById<ImageView>(R.id.imageView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        initView()
    }

    private fun initView() {
        selectImageButton.onClick {
            //调用相册
            val intent = Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        /*if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
            val selectedImage = data.data
            val filePathColumns = arrayOf(MediaStore.Images.Media.DATA)
            val c = contentResolver.query(selectedImage!!, filePathColumns, null, null, null)
            c.moveToFirst()
            val columnIndex = c.getColumnIndex(filePathColumns[0])
            val imagePath = c.getString(columnIndex)
            showImage(imagePath)
            c.close()
        }*/
    }

    //加载图片
    private fun showImage(imagePath: String) {
        val bm = BitmapFactory.decodeFile(imagePath)
        imageView.setImageBitmap(bm)
    }
}
