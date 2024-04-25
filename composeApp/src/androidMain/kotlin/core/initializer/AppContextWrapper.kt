package core.initializer

import android.content.Context
import androidx.startup.Initializer

object AppContextWrapper {
    var appContext: Context? = null
}

internal class ContextInitializer : Initializer<Context> {

    override fun create(context: Context): Context =
        context.applicationContext.also {
            AppContextWrapper.appContext = it
        }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()

}