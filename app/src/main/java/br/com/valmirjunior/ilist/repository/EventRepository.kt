package br.com.valmirjunior.ilist.repository

import androidx.lifecycle.LiveData
import br.com.valmirjunior.ilist.controller.App
import br.com.valmirjunior.ilist.model.Event
import br.com.valmirjunior.ilist.model.StatusEventEnum
import br.com.valmirjunior.ilist.model.dao.EventDao
import java.util.concurrent.Executor

object EventRepository {

    private val executor = Executor { command -> Thread(command).start() }
    private val eventDao:EventDao = App.DB.eventDao

    fun loadAllByStatus(statusEventEnum: StatusEventEnum): LiveData<List<Event>> {
        return eventDao.loadByStatus(statusEventEnum.value)
    }

    fun create(event: Event){
        executor.execute { eventDao.insert(event) }
    }

    fun update(event: Event){
        executor.execute { eventDao.update(event) }
    }

}