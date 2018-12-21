package com.simplemobiletools.gallery.pro.models

import androidx.room.*
import com.simplemobiletools.commons.extensions.formatDate
import com.simplemobiletools.commons.extensions.formatSize
import com.simplemobiletools.commons.helpers.SORT_BY_DATE_MODIFIED
import com.simplemobiletools.commons.helpers.SORT_BY_NAME
import com.simplemobiletools.commons.helpers.SORT_BY_PATH
import com.simplemobiletools.commons.helpers.SORT_BY_SIZE
import com.simplemobiletools.gallery.pro.helpers.FAVORITES
import com.simplemobiletools.gallery.pro.helpers.RECYCLE_BIN

@Entity(tableName = "directories", indices = [Index(value = ["path"], unique = true)])
data class Directory(
        @PrimaryKey(autoGenerate = true) var id: Long?,
        @ColumnInfo(name = "path") var path: String,
        @ColumnInfo(name = "thumbnail") var tmb: String,
        @ColumnInfo(name = "filename") var name: String,
        @ColumnInfo(name = "media_count") var mediaCnt: Int,
        @ColumnInfo(name = "last_modified") var modified: Long,
        @ColumnInfo(name = "date_taken") var taken: Long,
        @ColumnInfo(name = "size") var size: Long,
        @ColumnInfo(name = "location") var location: Int,
        @ColumnInfo(name = "media_types") var types: Int,

        // used with "Group direct subfolders" enabled
        @Ignore var subfoldersCount: Int = 0,
        @Ignore var subfoldersMediaCount: Int = 0) {

    constructor() : this(null, "", "", "", 0, 0L, 0L, 0L, 0, 0, 0, 0)

    fun getBubbleText(sorting: Int) = when {
        sorting and SORT_BY_NAME != 0 -> name
        sorting and SORT_BY_PATH != 0 -> path
        sorting and SORT_BY_SIZE != 0 -> size.formatSize()
        sorting and SORT_BY_DATE_MODIFIED != 0 -> modified.formatDate()
        else -> taken.formatDate()
    }

    fun areFavorites() = path == FAVORITES

    fun isRecycleBin() = path == RECYCLE_BIN
}
