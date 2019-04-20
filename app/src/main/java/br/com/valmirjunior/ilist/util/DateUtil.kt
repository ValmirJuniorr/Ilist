package br.com.valmirjunior.ilist.util

import br.com.valmirjunior.ilist.controller.App
import java.text.SimpleDateFormat

object DateUtil {
    private val app = App.INSTANCE
    val sdfDateHuman = SimpleDateFormat("dd/MM/yyyy", app.currentLocale)
    val sdfDateTimeHuman = SimpleDateFormat("dd/MM/yyyy HH:mm:ss ", app.currentLocale)
}