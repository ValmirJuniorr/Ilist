package br.com.valmirjunior.ilist.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.valmirjunior.ilist.contract.EventContract
import br.com.valmirjunior.ilist.model.Event

@Dao
interface EventDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg events: Event): LongArray

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(events: List<Event>): LongArray

    @Update
    fun update(vararg events: Event): Int

    @Delete
    fun delete(vararg events: Event): Int


    @Query(EventContract.QUERY_ALL)
    fun loadAll(): LiveData<List<Event>>

    @Query(EventContract.QUERY_BY_STATUS)
    fun loadByStatus(status: Int): LiveData<List<Event>>
}