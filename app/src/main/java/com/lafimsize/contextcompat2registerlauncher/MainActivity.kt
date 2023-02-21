package com.lafimsize.contextcompat2registerlauncher

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.lafimsize.contextcompat2registerlauncher.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




    }

    fun selectImage(view: View) {

        //izin kontrol
        //izin yoksa iste
        //varsa galeriye git

        if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)==
                PackageManager.PERMISSION_GRANTED){//izin verildi galeriye git.

            val intentToGallery= Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            //action pick ile aksiyonu yakala dedik ve images klasörü urisini aldık





        }else{//izin verilmedi ise izin iste

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)){
                //rasyoneli göstereyim mi? yani snackbarı
                Snackbar.make(binding.root,"İzin vermeniz gerekli!",Snackbar.LENGTH_INDEFINITE).setAction("İzin ver"){
                    //izin iste

                }.show()


            }else{
                //direk izin iste

            }


        }



    }
}