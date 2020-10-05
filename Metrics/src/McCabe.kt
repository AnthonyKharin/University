import java.io.File

class McCabe(file: File) {
    init {
        //Чтение входного файла
        val inputStream = file.bufferedReader()
        var inputString = inputStream.use { it.readText() }

        //Удаление комментариев
        inputString = inputString.replace(SINGLE_LINE_COMMENT, "")
        inputString = inputString.replace(MULTI_LINE_COMMENT, "")

        //Кол-во объявленных методов (узлов)
        val i = METHOD_DECLARATION.findAll(inputString).count()

        //Кол-во упровляющих операторов (ребер)
        val j =
            (IF_STATEMENT.findAll(inputString) +
                    ELSE_STATEMENT.findAll(inputString) +
                    CASE_STATEMENT.findAll((inputString))).count()

        println("${j - i + 2}")
    }

    companion object {
        //Несколько регулярных выражений, с помощью
        //Которых будем искать нужные нам конструкции
        private val MULTI_LINE_COMMENT = "(?s)/\\*.*?\\*/".toRegex()
        private val IF_STATEMENT = "\\s+if(\\s|\\()".toRegex()
        private val ELSE_STATEMENT = "(\\s|})?else(\\s|\\{)".toRegex()
        private val CASE_STATEMENT = "\\s+case(:|\\s)".toRegex()
        private val SINGLE_LINE_COMMENT = "(//)(.+?)(?=[\\n\\r]|\\*\\))".toRegex()
        private val METHOD_DECLARATION =
            "(public|protected|private|static|\\s) +[\\w\\<\\>\\[\\]]+\\s+(\\w+) *\\([^\\)]*\\) *(\\{?|[^;])".toRegex()
    }
}