package com.example.android.homework4

import kotlinx.coroutines.flow.Flow

class DreamRepository (private val dreamDao: DreamDAO) {

    // get all dreams
    val allDreams: Flow<List<Dream>> = dreamDao.getAllDreams()

    // suspend -> room runs all suspend functions/queries
    // so we just call it and embed in a method that we can use later

    suspend fun insert(dream:Dream){
        dreamDao.insert(dream)
    }

    suspend fun deleteById(id:Int){
        dreamDao.delete(id)
    }

    fun getDream(id:Int): Flow<Dream>{
        return dreamDao.getDream(id)
    }

    suspend fun update(id:Int, title:String, content:String, reflection:String, emotion:String){
        return dreamDao.update(title, content, reflection, emotion, id)
    }
}