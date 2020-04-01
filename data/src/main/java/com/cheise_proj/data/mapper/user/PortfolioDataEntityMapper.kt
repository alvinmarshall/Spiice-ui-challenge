package com.cheise_proj.data.mapper.user

import com.cheise_proj.data.mapper.IDataListMapper
import com.cheise_proj.data.mapper.IDataMapper
import com.cheise_proj.data.model.PortfolioData
import com.cheise_proj.domain.entities.user.Portfolio

class PortfolioDataEntityMapper : IDataMapper<PortfolioData, Portfolio>,
    IDataListMapper<PortfolioData, Portfolio> {
    override fun dataToEntity(data: PortfolioData): Portfolio {
        return Portfolio(
            screenShotUrl = data.screenShotUrl
        )
    }

    override fun entityToData(entity: Portfolio): PortfolioData {
        return PortfolioData(
            screenShotUrl = entity.screenShotUrl
        )
    }

    override fun dataListToEntity(dataList: List<PortfolioData>): List<Portfolio> {
        val entity = arrayListOf<Portfolio>()
        dataList.forEach { data -> entity.add(dataToEntity(data)) }
        return entity
    }

    override fun entityListToData(entityList: List<Portfolio>): List<PortfolioData> {
        val data = arrayListOf<PortfolioData>()
        entityList.forEach { entity -> data.add(entityToData(entity)) }
        return data
    }

}