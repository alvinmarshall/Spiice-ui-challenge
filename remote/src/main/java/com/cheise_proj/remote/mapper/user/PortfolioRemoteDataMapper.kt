package com.cheise_proj.remote.mapper.user

import com.cheise_proj.data.model.PortfolioData
import com.cheise_proj.remote.mapper.IRemoteListMapper
import com.cheise_proj.remote.mapper.IRemoteMapper
import com.cheise_proj.remote.model.user.PortfolioRemote

class PortfolioRemoteDataMapper : IRemoteMapper<PortfolioRemote, PortfolioData>,
    IRemoteListMapper<PortfolioRemote, PortfolioData> {
    override fun remoteToData(remote: PortfolioRemote): PortfolioData {
        return PortfolioData(
            screenShotUrl = remote.screenShotUrl
        )
    }

    override fun dataToRemote(data: PortfolioData): PortfolioRemote {
        return PortfolioRemote(
            screenShotUrl = data.screenShotUrl
        )
    }

    override fun remoteListToData(remoteList: List<PortfolioRemote>): List<PortfolioData> {
        val data = arrayListOf<PortfolioData>()
        remoteList.forEach { remote -> data.add(remoteToData(remote)) }
        return data
    }

    override fun dataListToRemote(dataList: List<PortfolioData>): List<PortfolioRemote> {
        val remote = arrayListOf<PortfolioRemote>()
        dataList.forEach { data -> remote.add(dataToRemote(data)) }
        return remote
    }


}