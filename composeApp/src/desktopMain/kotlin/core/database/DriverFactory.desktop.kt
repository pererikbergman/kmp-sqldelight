package core.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.rakangsoftware.sqldelight.data.local.database.Database
import java.io.File
import java.lang.Exception
import java.sql.DriverManager

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        val dbFilePath: String = getPath(isDebug = false)
        val driver: SqlDriver = JdbcSqliteDriver("jdbc:sqlite:${dbFilePath}")

        if (!File(dbFilePath).exists()) {
            Database.Schema.create(driver)
        }

        val currentVersion = getSQLiteSchemaVersion(dbFilePath)
        val latestVersion = Database.Schema.version
        println("currentVersion: $currentVersion -> latestVersion:$latestVersion")
        if (currentVersion < latestVersion) {
            Database.Schema.migrate(driver, currentVersion, latestVersion)
        }

        return driver
    }

    private fun getSQLiteSchemaVersion(dbPath: String): Long {
        val connection = DriverManager.getConnection("jdbc:sqlite:$dbPath")
        try {
            val statement = connection.createStatement()
            val resultSet = statement.executeQuery("PRAGMA user_version;")
            if (resultSet.next()) {
                return resultSet.getLong(1)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            connection.close()
        }

        return 0
    }

    private fun getPath(isDebug: Boolean): String {
        val propertyKey = if (isDebug) "java.io.tmpdir" else "user.home"
        val parentFolderPath = System.getProperty(propertyKey) + "/SqlDelightDemo"
        val parentFolder = File(parentFolderPath)
        if (!parentFolder.exists()) {
            parentFolder.mkdirs()
        }

        val databasePath = File(parentFolderPath, DB_NAME)
        return databasePath.absolutePath
    }
}