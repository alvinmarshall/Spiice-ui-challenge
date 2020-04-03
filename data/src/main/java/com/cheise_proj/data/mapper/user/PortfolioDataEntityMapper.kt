package com.cheise_proj.data.mapper.user

import com.cheise_proj.data.mapper.IDataListMapper
import com.cheise_proj.data.mapper.IDataMapper
import com.cheise_proj.data.model.PortfolioData
import com.cheise_proj.domain.entities.user.PortfolioEntity

class PortfolioDataEntityMapper : IDataMapper<PortfolioData, PortfolioEntity>,
    IDataListMapper<PortfolioData, PortfolioEntity> {
    override fun dataToEntity(data: PortfolioData): PortfolioEntity {
        return PortfolioEntity(
            screenShotUrl = data.screenShotUrl
        )
    }

    override fun entityToData(entity: PortfolioEntity): PortfolioData {
        return PortfolioData(
            screenShotUrl = entity.screenShotUrl
        )
    }

    override fun dataListToEntity(dataList: List<PortfolioData>): List<PortfolioEntity> {
        val entity = arrayListOf<PortfolioEntity>()
        dataList.forEach { data -> entity.add(dataToEntity(data)) }
        return entity
    }

    override fun entityListToData(entityList: List<PortfolioEntity>): List<PortfolioData> {
        val data = arrayListOf<PortfolioData>()
        entityList.forEach { entity -> data.add(entityToData(entity)) }
        return data
    }

}