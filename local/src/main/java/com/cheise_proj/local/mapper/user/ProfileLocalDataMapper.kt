package com.cheise_proj.local.mapper.user

import com.cheise_proj.data.model.ProfileData
import com.cheise_proj.local.mapper.ILocalMapper
import com.cheise_proj.local.model.ProfileLocal
import com.cheise_proj.local.model.ProfileLocal.Companion.portfolioMapper
import com.cheise_proj.local.model.ProfileLocal.Companion.reviewMapper
import com.cheise_proj.local.model.UserLocal.Companion.userMapper

/**
 * This class maps to ProfileLocal in local or ProfileData in data
 *class implements ILocalMapper
 */
class ProfileLocalDataMapper :
    ILocalMapper<ProfileLocal, ProfileData> {
    override fun localToData(local: ProfileLocal): ProfileData {
        return ProfileData(
            jobTitle = local.jobTitle,
            user = userMapper().localToData(local.user),
            description = local.description,
            reviews = reviewMapper().localListToData(local.reviews),
            portfolio = portfolioMapper().localListToData(local.portfolio)
        )
    }

    override fun dataToLocal(data: ProfileData): ProfileLocal {
        return ProfileLocal(
            jobTitle = data.jobTitle,
            user = userMapper().dataToLocal(data.user),
            description = data.description

        ).apply {
            reviews = reviewMapper().dataListListToLocal(data.reviews)
            portfolio = portfolioMapper().dataListListToLocal(data.portfolio)
        }
    }


}