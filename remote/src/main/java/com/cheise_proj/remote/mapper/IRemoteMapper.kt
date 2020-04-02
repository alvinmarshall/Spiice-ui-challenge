package com.cheise_proj.remote.mapper

/**
 * IDataMapper maps data and remote DTO of R
 *
 * @param R Remote  T
 * @param D Data  T
 */
interface IRemoteMapper<R, D> {
    /**
     * RemoteToData
     *
     * @param remote Remote T
     * @return D Data T
     */
    fun remoteToData(remote: R): D

    /**
     * DataToRemote
     *
     * @param data Data T
     * @return R Remote  T
     */
    fun dataToRemote(data: D): R
}

/**
 * IDataListMapper maps data list and remote list of T
 *
 * @param R Remote  T
 * @param D Data  T
 */
interface IRemoteListMapper<R, D> {
    /**
     * RemoteListToData
     *
     * @param remoteList Remote List<R>
     * @return D Data List<D>
     */
    fun remoteListToData(remoteList: List<R>): List<D>

    /**
     * DataListToRemote
     *
     * @param dataList Data  List<D>
     * @return R Remote  List<R>
     */
    fun dataListToRemote(dataList: List<D>): List<R>

}