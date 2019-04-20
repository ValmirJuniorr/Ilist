package br.com.valmirjunior.ilist.contract

object EventContract {

    const val TABLE_NAME = "Events"
    const val COLLUMN_ID = "id"
    const val COLLUMN_PARENT_ID = "parent_id"
    const val COLLUMN_TITLE = "title"
    const val COLLUMN_DESCRIPTION = "description"
    const val COLLUMN_STATUS = "status"
    const val COLLUMN_CREATED_AT = "created_at"
    const val COLLUMN_UPDATED_AT = "updated_at"

    const val QUERY_ALL= " SELECT * FROM $TABLE_NAME "
    const val QUERY_BY_STATUS = " $QUERY_ALL WHERE $COLLUMN_STATUS = :status"
}