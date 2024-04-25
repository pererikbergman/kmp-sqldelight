package core.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.rakangsoftware.sqldelight.data.local.database.Database
import core.initializer.AppContextWrapper

actual class DriverFactory actual constructor() {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(Database.Schema, AppContextWrapper.appContext!!, DB_NAME)
    }
}