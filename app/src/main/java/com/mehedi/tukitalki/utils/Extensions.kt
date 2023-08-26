package com.mehedi.tukitalki.utils


import com.google.firebase.database.FirebaseDatabase

fun FirebaseDatabase.instance(): FirebaseDatabase {
    return FirebaseDatabase.getInstance()

}