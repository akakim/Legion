package com.akakim.legion.common

/**
 * Created by RyoRyeong Kim on 2018-04-19.
 *
 */

open interface OnDatabaseChangedListener{

    fun onNewDatabaseEntryAdded()
    fun onDatabaseEntryRenamed(affectRow : Int )
    fun onDatabaseEntryRemoved()
}