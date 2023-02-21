
# ContextCompat2registerLauncher

    //api 33 için android.permission.READ_MEDIA_IMAGE
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

