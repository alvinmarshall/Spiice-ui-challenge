package com.cheise_proj.presentation.mapper.user

import com.cheise_proj.domain.entities.user.PortfolioEntity
import com.cheise_proj.presentation.mapper.IPresentationListMapper
import com.cheise_proj.presentation.mapper.IPresentationMapper
import com.cheise_proj.presentation.model.Portfolio

class PortfolioEntityMapper : IPresentationMapper<Portfolio, PortfolioEntity>,
    IPresentationListMapper<Portfolio, PortfolioEntity> {
    override fun presentationToEntity(presentation: Portfolio): PortfolioEntity {
        return PortfolioEntity(
            screenShotUrl = presentation.screenShotUrl
        )
    }

    override fun entityToPresentation(entity: PortfolioEntity): Portfolio {
        return Portfolio(
            screenShotUrl = entity.screenShotUrl
        )
    }

    override fun presentationListToEntity(presentationList: List<Portfolio>): List<PortfolioEntity> {
        val entity = arrayListOf<PortfolioEntity>()
        presentationList.forEach { presentation -> entity.add(presentationToEntity(presentation)) }
        return entity
    }

    override fun entityListToPresentation(entityList: List<PortfolioEntity>): List<Portfolio> {
        val presentation = arrayListOf<Portfolio>()
        entityList.forEach { entity -> presentation.add(entityToPresentation(entity)) }
        return presentation
    }

}