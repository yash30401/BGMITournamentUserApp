package com.bgmi.sports.tournament.userApp.bgmitournament.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bgmi.sports.tournament.userApp.bgmitournament.model.matchTicketsModel
import com.bgmi.sports.tournament.userApp.bgmitournament.repository.authRepository
import com.bgmi.sports.tournament.userApp.bgmitournament.repository.ticketsRepo
import com.google.firebase.auth.FirebaseAuth

class ticketViewModel : ViewModel() {

    private val repository: ticketsRepo

    private val _morningMatchData = MutableLiveData<List<matchTicketsModel>>()

    val morningallMatch: LiveData<List<matchTicketsModel>> = _morningMatchData

    init {
        repository = ticketsRepo().getInstance()
        repository.loadMorningMatches(_morningMatchData)
    }


}