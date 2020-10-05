import java.io.File
import kotlin.math.log2

class Halstead(file: File) {
    init {
        //Чтение входного файла
        val inputStream = file.bufferedReader()
        val inputString = inputStream.use { it.readText() }

        //Убираем комментарии
        var text: String
        text = inputString.replace(MULTI_LINE_COMMENT, "")
        text = text.replace(SINGLE_LINE_COMMENT, "")

        //Разбиваю текст на строки
        val lineList = mutableListOf<String>()
        val textLinesList = text.split(NEW_LINE)
        lineList.addAll(textLinesList)

        //Инициализирую хэш-мапы, которые буду использовать для получения ответа
        val operators = HashMap<String, Int>()
        val operands = HashMap<String, Int>()

        //Смотрю каждую строку, ищу методы
        lineList.forEach {
            var input = it
            //Пока в строке есть метод
            while (input.contains(METHOD)) {
                //Получаем метод
                METHOD.find(input)?.value?.let { str ->
                    //Получаем его название
                    WORD.find(str)?.value?.let { methodName ->
                        //Если еще не было такого метода, то кол-во будет = 1
                        var newCount = 1
                        //Если такой метод уже был среди операторов, то надо прибавить кол-во вхождений
                        operators[methodName]?.let { oldCount ->
                            newCount += oldCount
                        }
                        operators[methodName] = newCount
                    }
                    //Внутри нашего метода мог быть другой, надо рассмотреть это
                    input = str
                }
                //Обрезаю строку (вызов метода)
                input = input.replaceFirst("$WORD\\(".toRegex(), "")
                input = input.substring(0, input.length - 1)
            }
        }

        //Делим строки по Java операторам
        updateList(JAVA_OPERATORS, lineList, operators, true)

        //Делим строки по зарезервированным словам
        updateList(RESERVED_WORDS, lineList, operators, false)

        //Заполняем хэш-мапу операндов
        lineList.forEach {
            if (!operators.keys.contains(it)) {
                var newCount = 1
                //Если такой операнд уже был, то надо прибавить кол-во вхождений
                operands[it]?.let { oldCount ->
                    newCount += oldCount
                }
                operands[it] = newCount
            }
        }

        val countOperators = operators.values.sum()
        val countOperands = operands.values.sum()
        val countUniqueOperators = operators.size
        val countUniqueOperands = operands.size

        val dictionary = countUniqueOperands + countUniqueOperators
        val length = countOperands + countOperators
        val value = length * log2(dictionary.toDouble())
        val difficulty = (dictionary / 2.0) * (countOperands / countUniqueOperands.toDouble())
        val effort = value * difficulty

        println("Словарь программы: $dictionary")
        println("Длина программы: $length")
        println("Объем программы: $value")
        println("Сложность программы: $difficulty")
        println("Усилия программиста: $effort")

    }

    private fun updateList(
        listSource: List<String>,
        lineList: MutableList<String>,
        operators: HashMap<String, Int>,
        needToAdd: Boolean
    ) {
        //Смотрю все зарезервированные в ЯП операторы
        //И делю по ним текст
        listSource.forEach { operator ->
            //Если не создать временный список,
            //То можно получить ошибку, т.к. javaOperators не потокобезопасный
            val temp = mutableListOf<String>()
            //Смотрю все строки
            lineList.forEach { str ->
                //Если в строке содержится Java оператор
                if (needToAdd && str.contains(operator)) {
                    //Если еще не было такого оператора, то кол-во будет = 1
                    var newCount = 1
                    //Если такой оператор уже был, то надо прибавить кол-во вхождений
                    operators[operator]?.let { oldCount ->
                        newCount += oldCount
                    }
                    operators[operator] = newCount
                }
                //Добавляем во временный список
                //Модифицированные строки
                temp.addAll(
                    //Делим исходную строку по оператору
                    str.split(operator)
                        //Убираем лишние пробелы
                        .map { it.trim() }
                        //Делим строку по пробелу (он мог появиться в середине)
                        .flatMap {
                            return@flatMap it.split(" ")
                        }
                        //Фильтруем пустые строки
                        .filter { it.isNotEmpty() }
                        //Фильтруем те операторы, что уже были
                        .filter { !RESERVED_WORDS.contains(it) }
                )
            }
            //Заменяем наш список на временный
            lineList.clear()
            lineList.addAll(temp)
        }
    }

    companion object {
        //Под оператором мы понимаем стандартные операторы ЯП и методы
        //Ниже - перечень стандартных операторов ЯП Java
        private val JAVA_OPERATORS = listOf(
            "+", "-", "*", "/", "%", "++", "--", "==",
            "!=", ">", "<", ">=", "<=", "&", "|", "^",
            "~", "<<", ">>", ">>>", "&&", "||", "!",
            "=", "+=", "-=", "*=", "/=", "%=", "<<=",
            ">>=", "&=", "^=", "|=", "instanceof", "null",
            "break", "continue", "true", "false", "return",
            "if", "else"
        )

        //Некоторые зарезервированные слова или символы
        //Не являются ни оператором, ни операндом
        //Надо их исключить из добавления в наши множества
        private val RESERVED_WORDS = listOf(
            "abstract", "assert", "boolean", "break",
            "byte", "case", "default", "catch", "char",
            "class", "const", "default", "do", "double",
            "else", "enum", "extends", "final", "finally",
            "float", "for", "goto", "if", "implements",
            "import", "int", "interface", "long", "native",
            "new", "package", "private", "protected", "public",
            "short", "static", "strictfp", "super", "switch",
            "synchronized", "this", "throw", "throws", "try",
            "transient", "void", "volatile", "while", "var", ";",
            "{", "}", "(", ")", ",", "<", ">", "String", "[", "]",
            "\"", "'"
        )

        //Несколько регулярных выражений, с помощью
        //Которых будем искать нужные нам конструкции
        private val WORD = "(\\.?\\w+)*".toRegex()
        private val METHOD = "(?U)([.\\w]+)\\s*\\((.*)\\)".toRegex()
        private val MULTI_LINE_COMMENT = "(?s)/\\*.*?\\*/".toRegex()
        private val IF_STATEMENT = "(?s)if\\s*\\(.*?\\)\\{.*?}".toRegex()
        private val SINGLE_LINE_COMMENT = "(//)(.+?)(?=[\\n\\r]|\\*\\))".toRegex()
        private val NEW_LINE = "\\r?\\n".toRegex()
    }
}