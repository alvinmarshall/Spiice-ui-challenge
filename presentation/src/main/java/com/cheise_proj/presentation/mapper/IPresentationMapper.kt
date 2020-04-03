package com.cheise_proj.presentation.mapper

/**
 * IPresentationMapper maps domain and presentation model of T
 *
 * @param P Presentation model T
 * @param E Domain Entity model T
 */
interface IPresentationMapper<P, E> {
    /**
     * PresentationToEntity
     *
     * @param presentation  T
     * @return E Domain Entity T
     */
    fun presentationToEntity(presentation: P): E

    /**
     * EntityToPresentation
     *
     * @param entity Domain Entity T
     * @return P Presentation model T
     */
    fun entityToPresentation(entity: E): P
}

/**
 * IPresentationListMapper maps domain list and presentation model list of T
 *
 * @param P Presentation model T
 * @param E Domain Entity model T
 */
interface IPresentationListMapper<P, E> {
    /**
     * PresentationListToEntity
     *
     * @param presentationList List<T>
     * @return E Domain Entity List<T>
     */
    fun presentationListToEntity(presentationList: List<P>): List<E>

    /**
     * EntityListToPresentation
     *
     * @param entityList Domain Entity List<T>
     * @return P Presentation model List<T>
     */
    fun entityListToPresentation(entityList: List<E>): List<P>

}