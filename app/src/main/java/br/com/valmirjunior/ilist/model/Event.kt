package br.com.valmirjunior.ilist.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.valmirjunior.ilist.contract.EventContract
import java.io.Serializable
import java.util.*

@Entity(tableName = EventContract.TABLE_NAME)
data class Event(
        @PrimaryKey
        val id: Long?,
        val parentItemListId: Long?,
        var title: String,
        var description: String,
        var status: Int,
        val createdAt: Date,
        var updatedAt: Date
) : Serializable