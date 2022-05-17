package com.pranav.androidjetpackcourse.view.activities

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.pranav.androidjetpackcourse.R
import com.pranav.androidjetpackcourse.databinding.ActivityAddUpdateDishBinding
import com.pranav.androidjetpackcourse.databinding.DialogCustomImageSelectionBinding

class AddUpdateDishActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mBinding:ActivityAddUpdateDishBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityAddUpdateDishBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setUpActionBar()
        mBinding.ivAddDishImage.setOnClickListener(this)
    }


    private fun setUpActionBar(){
        setSupportActionBar(mBinding.toolbarAddDishActivity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) //allow us to have back button
        mBinding.toolbarAddDishActivity.setNavigationOnClickListener{
            onBackPressed()
        }
    }



    private fun customImageSelectionDialog(){
        val dialog = Dialog(this)
        val binding:DialogCustomImageSelectionBinding = DialogCustomImageSelectionBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)
        binding.tvCamera.setOnClickListener {
            Dexter.withContext(this).withPermissions(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA).withListener(
                object: MultiplePermissionsListener{
                    override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                        report?.let {
                            if(report.areAllPermissionsGranted()){
                                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                                cameraResultLauncher.launch(cameraIntent)
                            }
                            else{
                                showRationalDialogForPermissions()
                            }
                        }

                    }

                    override fun onPermissionRationaleShouldBeShown(permissions: MutableList<PermissionRequest>?, token:
                    PermissionToken?) {

                        showRationalDialogForPermissions()

                    }

                }
                                                        ).onSameThread().check()

            dialog.dismiss()
        }
        binding.tvGallery.setOnClickListener {
            Dexter.withContext(this).withPermissions(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
                                                    ).withListener(object :MultiplePermissionsListener{
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    report?.let {
                        if(report.areAllPermissionsGranted()){
                            val galleryIntent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                            galleryResultLauncher.launch(galleryIntent)
                        }
                        else{
                            showRationalDialogForPermissions()
                        }
                    }
                }

                override fun onPermissionRationaleShouldBeShown(p0: MutableList<PermissionRequest>?, p1: PermissionToken?) {
                    showRationalDialogForPermissions()
                }

            }).onSameThread().check()
            dialog.dismiss()
        }
        dialog.show()
    }


    var cameraResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val data: Intent? = result.data
            data?.let {
                val thumbnail:Bitmap = data.extras?.get("data") as Bitmap
                mBinding.ivDishImage.setImageBitmap(thumbnail)
                mBinding.ivAddDishImage.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.ic_vector_edit))
            }

        }
        else if(result.resultCode==Activity.RESULT_CANCELED){
            Log.e("cancelled","user cancelled image selection")
        }
    }


    var galleryResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val data: Intent? = result.data
            data?.let {
                val selectedPhotoUri = data.data
                mBinding.ivDishImage.setImageURI(selectedPhotoUri)
                mBinding.ivAddDishImage.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.ic_vector_edit))
            }

        }
        else if(result.resultCode==Activity.RESULT_CANCELED){
            Log.e("cancelled","user cancelled image selection")
        }
    }

    private fun showRationalDialogForPermissions(){
        AlertDialog.Builder(this).setMessage("It looks like you have turned off permissions. It can be enabled under " +
                                                     "application settings").setPositiveButton("GO TO SETTINGS")
        {_,_ ->
            try{
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                val uri =  Uri.fromParts("package",packageName,null)
                intent.data=uri
                startActivity(intent)
            } catch (e:ActivityNotFoundException){
                e.printStackTrace()
            }

        }.setNegativeButton("Cancel"){
            dialog,_ ->
            dialog.dismiss()
        }.show()

    }

    override fun onClick(v: View?) {
        if(v!=null){
            when(v.id){
                R.id.iv_add_dish_image -> {
                    customImageSelectionDialog()
                    return
                }
            }
        }
    }


}