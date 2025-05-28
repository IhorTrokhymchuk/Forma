package alyona.forma.config

import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator
import org.springframework.jdbc.datasource.init.UncategorizedScriptException
import javax.script.ScriptException
import javax.sql.DataSource

@Configuration
class DatabaseInit(
    private val dataSource: DataSource
) {

    @PostConstruct
    fun initializeDatabase() {
        val script = ClassPathResource("postgresql-init.sql")
        try {
            val databasePopulator = ResourceDatabasePopulator(script)
            databasePopulator.execute(dataSource)
        } catch (e: ScriptException) {
            throw RuntimeException("Помилка виконання SQL-скрипта: ${e.message}", e)
        } catch (e: UncategorizedScriptException) {
            throw RuntimeException("Невідома помилка при ініціалізації бази даних: ${e.message}", e)
        }
    }
}