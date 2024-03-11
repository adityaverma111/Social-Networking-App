package com.example.krishna

import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.app.Instrumentation
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.*
import com.example.krishna.databinding.FragmentPostBinding
import com.google.firebase.database.FirebaseDatabase
import java.text.DateFormat
import java.util.Calendar


@Suppress("UNREACHABLE_CODE")
class PostFragment : Fragment() {



    private var uploadTitle: EditText? = null
    private var uploadDesc: EditText? = null
    private var uploadPriority: EditText? = null
    private var uploadImage: EditText? = null
    private var saveButton: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_post, container, false) // Make sure to replace `your_layout_name` with the name of your XML layout file.

        // Initialize the EditTexts and Button with view.findViewById
        uploadTitle = view.findViewById(R.id.uploadTitle)
        uploadDesc = view.findViewById(R.id.uploadDesc)
        uploadPriority = view.findViewById(R.id.uploadPriority)
        uploadImage = view.findViewById(R.id.uploadImage)
        saveButton = view.findViewById(R.id.saveButton)

        saveButton?.setOnClickListener {
            saveDataToFirebase()
        }

        return view
    }

    private fun saveDataToFirebase() {
        val CompanyName = uploadTitle?.text.toString().trim()
        val PositionName = uploadDesc?.text.toString().trim()
        val Course = uploadPriority?.text.toString().trim()
        val ApplyLink = uploadImage?.text.toString().trim()

        if (CompanyName.isNotEmpty() &&  PositionName.isNotEmpty() && Course.isNotEmpty() && ApplyLink.isNotEmpty()) {
            // Reference to your Firebase Realtime Database
            val databaseReference = FirebaseDatabase.getInstance().getReference("Posts")
            val postId = databaseReference.push().key

            val post = Post(postId!!, CompanyName,  PositionName, Course, ApplyLink)
            postId?.let {
                databaseReference.child(it).setValue(post).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Handle success
                    } else {
                        // Handle failure
                    }
                }
            }
        } else {
            // Prompt user to enter all details
        }
    }

    // Define your Post data model
    data class Post(
        val id: String,
        val CompanyName: String,
        val  PositionName: String,
        val Course: String,
        val ApplyLink: String
    )
}