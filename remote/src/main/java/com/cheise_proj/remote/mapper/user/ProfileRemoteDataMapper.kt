package com.cheise_proj.remote.mapper.user

import com.cheise_proj.data.model.ProfileData
import com.cheise_proj.remote.mapper.IRemoteMapper
import com.cheise_proj.remote.model.user.PortfolioRemote.Companion.portfolioMapper
import com.cheise_proj.remote.model.user.ProfileRemote
import com.cheise_proj.remote.model.user.ReviewRemote.Companion.reviewMapper
import com.cheise_proj.remote.model.user.UserRemote.Companion.userMapper

/**
 * This class maps to ProfileRemote in remote or ProfileData in data
 *class implements IRemoteMapper
 */
class ProfileRemoteDataMapper :
    IRemoteMapper<ProfileRemote, ProfileData> {
    override fun remoteToData(remote: ProfileRemote): ProfileData {
        return ProfileData(
            jobTitle = remote.jobTitle,
            user = userMapper().remoteToData(remote.user),
            description = remote.description,
            reviews = reviewMapper().remoteListToData(remote.reviews),
            portfolio = portfolioMapper().remoteListToData(remote.portfolio)
        )
    }

    override fun dataToRemote(data: ProfileData): ProfileRemote {
        return ProfileRemote(
            jobTitle = data.jobTitle,
            user = userMapper().dataToRemote(data.user),
            description = data.description,
            reviews = reviewMapper().dataListToRemote(data.reviews),
            portfolio = portfolioMapper().dataListToRemote(data.portfolio)
        )
    }

}