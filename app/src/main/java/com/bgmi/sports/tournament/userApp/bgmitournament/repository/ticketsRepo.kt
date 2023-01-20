package com.bgmi.sports.tournament.userApp.bgmitournament.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.bgmi.sports.tournament.userApp.bgmitournament.ViewModel.ticketViewModel
import com.bgmi.sports.tournament.userApp.bgmitournament.model.matchTicketsModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ticketsRepo {

    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("Matches")


    @Volatile private var INSTANCE:ticketsRepo?=null

    fun getInstance():ticketsRepo{
        return INSTANCE?: synchronized(this){
            val instance=ticketsRepo()
            INSTANCE=instance
            instance
        }
    }

    fun loadMorningMatches(morningmatchList: MutableLiveData<List<matchTicketsModel>>){

        databaseReference.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                try{

                    var _matchList:List<matchTicketsModel> = snapshot.child("Morning").children.map { dataSnapshot ->
                        dataSnapshot.getValue(matchTicketsModel::class.java)!!

                    }



                    morningmatchList.postValue(_matchList)


                }catch(e:Exception){
                    Log.d("ERROR",e.printStackTrace().toString())
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("ERROR","SomeThing Went Wrong")
            }

        })

    }
}