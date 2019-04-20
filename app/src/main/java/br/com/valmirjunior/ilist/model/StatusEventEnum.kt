package br.com.valmirjunior.ilist.model

import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import br.com.valmirjunior.ilist.R
import br.com.valmirjunior.ilist.controller.App

enum class StatusEventEnum(val value: Int) {
    OPEN(1), CLOSED(2);

    val label: String
        get()  {
            return when (this){
                OPEN -> getString(R.string.status_open)
                CLOSED -> getString(R.string.status_closed)
            }
        }

    val icon: Drawable?
        get()  {
            return when (this){
                OPEN -> getIcon(R.drawable.ic_status_open,R.color.green)
                CLOSED -> getIcon(R.drawable.ic_status_closed, R.color.red)
            }
        }


    private fun getString(idREs: Int): String {
        return App.INSTANCE.getString(idREs)
    }

    private fun getIcon(idDrawable: Int, idColor: Int?): Drawable? {
        var context = App.INSTANCE
        val icon = ContextCompat.getDrawable(context,idDrawable)
        idColor?.let {
            icon?.let{
                DrawableCompat.setTint(icon,ContextCompat.getColor(context, idColor))
            }
        }
        return icon
    }


    companion object {
        fun getConstByValue(value: Int): StatusEventEnum? {
            for (attendanceEnum : StatusEventEnum in values()){
                if(attendanceEnum.value == value)
                    return attendanceEnum
            }
            return null
        }

        fun getNextStatus(statusEventEnumActual: StatusEventEnum): StatusEventEnum {
            val values = StatusEventEnum.values()
            return if (statusEventEnumActual.ordinal < values.size - 1) {
                values[statusEventEnumActual.ordinal + 1]
            } else {
                values[0]
            }
        }

        fun first(): StatusEventEnum {
            return values()[0]
        }
    }
}