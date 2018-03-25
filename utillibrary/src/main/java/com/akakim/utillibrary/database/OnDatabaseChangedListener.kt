package com.akakim.utillibrary.database

/**
 * @author KIM
 * @version 0.0.1
 * @since 0.0.1
 * @date 2018-03-11
 */

interface OnDatabaseChangedListener{
    fun onNewDatabaseEntryAdded()
    fun onDatabaseEntryRenamed()
}