package com.lafimsize.contextcompat2registerlauncher

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.lafimsize.contextcompat2registerlauncher.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    private lateinit var activityLauncher: ActivityResultLauncher<Intent>
    private lateinit var permissionLauncher:ActivityResultLauncher<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerLaunchers()



    }

    private fun registerLaunchers(){//sonuç döndüren launcherları initialization etme işlemi

        activityLauncher=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            //StartActivityForResult galeriye gidip bir sonuç alacağız kontrol et

            if (it.resultCode== RESULT_OK){
                val intentFromResult=it.data//it.data intent döndürüyor

                intentFromResult?.let {
                    val imageData=intentFromResult.data//bir yol uri döndürüyor
                    binding.imageView.setImageURI(imageData)
                    binding.tvSecim.text=imageData?.toString()
                }
            }
        }



        permissionLauncher=registerForActivityResult(ActivityResultContracts.RequestPermission()){

            if (it){//izin verilmiş ise galeriye gidelim

                val intentForGallery=Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityLauncher.launch(intentForGallery)

            }
        }

    }







    fun selectImage(view: View) {

        //izin kontrol
        //izin yoksa iste
        //varsa galeriye git

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.TIRAMISU){//33 OR HIGHER

            if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_MEDIA_IMAGES)==
                PackageManager.PERMISSION_GRANTED){//izin verildi galeriye git.

                val intentToGallery= Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                //action pick ile aksiyonu yakala dedik ve images klasörü urisini aldık
                activityLauncher.launch(intentToGallery)





            }else{//izin verilmedi ise

                if (ActivityCompat.shouldShowRequestPermissionRationale(this,android.Manifest.permission.READ_MEDIA_IMAGES)){
                    //rasyoneli göstereyim mi? yani snackbarı
                    Snackbar.make(binding.root,"İzin vermeniz gerekli!",Snackbar.LENGTH_INDEFINITE).setAction("İzin ver"){
                        //izin iste

                        permissionLauncher.launch(android.Manifest.permission.READ_MEDIA_IMAGES)

                    }.show()


                }else{
                    //direk izin iste

                    permissionLauncher.launch(android.Manifest.permission.READ_MEDIA_IMAGES)

                }


            }

        }else{//32 den düşük sdkler
            if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)==
                PackageManager.PERMISSION_GRANTED){//izin verildi galeriye git.

                val intentToGallery= Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                //action pick ile aksiyonu yakala dedik ve images klasörü urisini aldık
                activityLauncher.launch(intentToGallery)





            }else{//izin verilmedi ise

                if (ActivityCompat.shouldShowRequestPermissionRationale(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)){
                    //rasyoneli göstereyim mi? yani snackbarı
                    Snackbar.make(binding.root,"İzin vermeniz gerekli!",Snackbar.LENGTH_INDEFINITE).setAction("İzin ver"){
                        //izin iste

                        permissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)

                    }.show()


                }else{
                    //direk izin iste

                    permissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)

                }


            }
        }






    }
}