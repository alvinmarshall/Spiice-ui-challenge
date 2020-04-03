package com.cheise_proj.local.mapper.user

import com.cheise_proj.data.model.PortfolioData
import com.cheise_proj.local.mapper.ILocalListMapper
import com.cheise_proj.local.mapper.ILocalMapper
import com.cheise_proj.local.model.PortfolioLocal


class PortfolioLocalDataMapper : ILocalMapper<PortfolioLocal, PortfolioData>,
    ILocalListMapper<PortfolioLocal, PortfolioData> {
    override fun localToData(local: PortfolioLocal): PortfolioData {
        return PortfolioData(
            screenShotUrl = local.screenShotUrl
        )
    }

    override fun dataToLocal(data: PortfolioData): PortfolioLocal {
        return PortfolioLocal(
            screenShotUrl = data.screenShotUrl
        )
    }

    override fun localListToData(localList: List<PortfolioLocal>): List<PortfolioData> {
        val data = arrayListOf<PortfolioData>()
        localList.forEach { local -> data.add(localToData(local)) }
        return data
    }

    override fun dataListListToLocal(dataToLocalList: List<PortfolioData>): List<PortfolioLocal> {
        val remote = arrayListOf<PortfolioLocal>()
        dataToLocalList.forEach { data -> remote.add(dataToLocal(data)) }
        return remote
    }


}