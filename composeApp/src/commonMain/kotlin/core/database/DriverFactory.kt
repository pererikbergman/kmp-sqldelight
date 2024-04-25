package core.database

import app.cash.sqldelight.db.SqlDriver
import com.rakangsoftware.sqldelight.data.local.database.Database

val DB_NAME = "sqldelight.db"

expect class DriverFactory() {
    fun createDriver(): SqlDriver
}

fun createDatabase(driverFactory: DriverFactory): Database {
    return Database(driverFactory.createDriver())
}