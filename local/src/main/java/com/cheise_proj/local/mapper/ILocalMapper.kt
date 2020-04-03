package com.cheise_proj.local.mapper

/**
 * ILocalMapper maps local and data model of T
 *
 * @param L Local model T
 * @param D Data model T
 */
interface ILocalMapper<L, D> {
    /**
     * LocalToData
     *
     * @param local Data T
     * @return D Data T
     */
    fun localToData(local: L): D

    /**
     * DataToLocal
     *
     * @param data Data T
     * @return L Local T
     */
    fun dataToLocal(data: D): L
}

/**
 * ILocalListMapper maps local list and data model list of T
 *
 * @param L Local model T
 * @param D Data model T
 */
interface ILocalListMapper<L, D> {
    /**
     * LocalListToData
     *
     * @param localList Data List<T>
     * @return D Data List<T>
     */
    fun localListToData(localList: List<L>): List<D>

    /**
     * DataListListToLocal
     *
     * @param dataToLocalList Data List<T>
     * @return L Local List<T>
     */
    fun dataListListToLocal(dataToLocalList: List<D>): List<L>

}