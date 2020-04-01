package com.cheise_proj.data.mapper

/**
 * IDataMapper maps domain and data model of T
 *
 * @param D Data model T
 * @param E Domain Entity model T
 */
interface IDataMapper<D, E> {
    /**
     * DataToEntity
     *
     * @param data Data T
     * @return E Domain Entity T
     */
    fun dataToEntity(data: D): E

    /**
     * EntityToData
     *
     * @param entity Domain Entity T
     * @return D Data model T
     */
    fun entityToData(entity: E): D
}

/**
 * IDataListMapper maps domain list and data model list of T
 *
 * @param D Data model T
 * @param E Domain Entity model T
 */
interface IDataListMapper<D, E> {
    /**
     * DataListToEntity
     *
     * @param dataList Data List<T>
     * @return E Domain Entity List<T>
     */
    fun dataListToEntity(dataList: List<D>): List<E>

    /**
     * EntityListToData
     *
     * @param entityList Domain Entity List<T>
     * @return D Data model List<T>
     */
    fun entityListToData(entityList: List<E>): List<D>

}