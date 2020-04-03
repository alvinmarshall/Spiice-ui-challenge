package com.cheise_proj.presentation.mapper.user


import com.cheise_proj.domain.entities.user.ProfileEntity
import com.cheise_proj.presentation.mapper.IPresentationMapper
import com.cheise_proj.presentation.model.Profile
import com.cheise_proj.presentation.model.Profile.Companion.portfolioMapper
import com.cheise_proj.presentation.model.Profile.Companion.reviewMapper
import com.cheise_proj.presentation.model.User.Companion.userMapper

/**
 * This class maps to ProfileEntity in domain or Profile in presentation
 *class implements IPresentationMapper
 */
class ProfileEntityMapper :
    IPresentationMapper<Profile, ProfileEntity> {
    override fun presentationToEntity(presentation: Profile): ProfileEntity {
        return ProfileEntity(
            jobTitle = presentation.jobTitle,
            user = userMapper().presentationToEntity(presentation.user),
            description = presentation.description,
            portfolio = portfolioMapper().presentationListToEntity(presentation.portfolio),
            reviews = reviewMapper().presentationListToEntity(presentation.reviews)
        )
    }

    override fun entityToPresentation(entity: ProfileEntity): Profile {
        return Profile(
            jobTitle = entity.jobTitle,
            user = userMapper().entityToPresentation(entity.user),
            description = entity.description,
            portfolio = portfolioMapper().entityListToPresentation(entity.portfolio),
            reviews = reviewMapper().entityListToPresentation(entity.reviews)
        )
    }

}