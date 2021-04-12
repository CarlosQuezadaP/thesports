package com.condor.thesports.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.condor.core.ResultWrapper
import com.condor.domain.models.EventDomain
import com.condor.domain.models.TeamDomain
import com.condor.usecases.IGetAllEventsUseCase
import com.condor.usecases.IGetTeamUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class TeamDetailViewModel(
    private val iGetTeamUseCase: IGetTeamUseCase,
    private val iGetAllEventsUseCase: IGetAllEventsUseCase
) : ViewModel() {

    private var _teamLiveData: MutableLiveData<ResultWrapper<TeamDomain>> = MutableLiveData()
    var teamLiveData: LiveData<ResultWrapper<TeamDomain>> = _teamLiveData

    private var _eventsLiveData: MutableLiveData<ResultWrapper<List<EventDomain>>> =
        MutableLiveData()
    var eventsLiveData: LiveData<ResultWrapper<List<EventDomain>>> = _eventsLiveData

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> = _loading

    fun getTeam(idTeam: String) {
        viewModelScope.launch {
            iGetTeamUseCase.invoke(idTeam).collect {
                _teamLiveData.value = it
            }
        }
    }

    fun getEventsByTeamId(teamId: String) {
        viewModelScope.launch {
            iGetAllEventsUseCase.invoke(teamId).collect {
                _eventsLiveData.value = it
            }
        }
    }

}