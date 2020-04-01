package com.cheise_proj.data.mapper.user

import com.cheise_proj.data.mapper.IDataMapper
import com.cheise_proj.data.model.ProfileData
import com.cheise_proj.data.model.ProfileData.Companion.portfolioMapper
import com.cheise_proj.data.model.ProfileData.Companion.reviewMapper
import com.cheise_proj.data.model.UserData.Companion.userMapper
import com.cheise_proj.domain.entities.user.ProfileEntity

/**
 * This class maps to ProfileEntity in domain or ProfileData in data
 *class implements IDataMapper
 */
class ProfileDataEntityMapper :
    IDataMapper<ProfileData, ProfileEntity> {
    override fun dataToEntity(data: ProfileData): ProfileEntity {
        return ProfileEntity(
            jobTitle = data.jobTitle,
            user = userMapper().dataToEntity(data.user),
            description = data.description,
            portfolio = portfolioMapper().dataListToEntity(data.portfolio),
            reviews = reviewMapper().dataListToEntity(data.reviews)
        )
    }

    override fun entityToData(entity: ProfileEntity): ProfileData {
        return ProfileData(
            jobTitle = entity.jobTitle,
            user = userMapper().entityToData(entity.user),
            description = entity.description,
            portfolio = portfolioMapper().entityListToData(entity.portfolio),
            reviews = reviewMapper().entityListToData(entity.reviews)
        )
    }
}