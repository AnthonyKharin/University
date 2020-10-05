import java.io.File

fun main() {
    //Путь к файлу, который мы рассматриваем
    //Когда-то это был Java файл, но на этапе
    //Компиляции IDE ругается на наличие импортов,
    //Которые она не может подтянуть
    //Поэтому я поменял расширение
    val filePathHalstead = "src\\FileViewPresenter.txt"
    val filePathMcCabe = "src\\MainPresenter.txt"
    val file = File(filePathMcCabe)
    McCabe(file)
    //Halstead(file)
}