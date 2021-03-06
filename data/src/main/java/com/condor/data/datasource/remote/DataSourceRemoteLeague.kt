package com.condor.data.datasource.remote

import com.condor.data.converters.IConverter
import com.condor.data.network.SportApi
import com.condor.domain.models.LeagueDomain

class DataSourceRemoteLeague(val sportApi: SportApi, private val iConverter: IConverter) :
    IDataSourceRemoteLeague {
    override suspend fun getAllLeagues(): List<LeagueDomain> {
        return sportApi.getAllLeagues().leagues.map {
            iConverter.convertLeagueDtoToDomain(it)
        }
    }
}