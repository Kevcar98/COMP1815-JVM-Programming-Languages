
import java.io.File
/*
fun main(args: Array<String>) {

    val fileNameP = "Projects.txt"
    val fileNameTm = "Teams.txt"
    val fileNameTk = "Tasks.txt"

    var fileP = File(fileNameP)
    var fileTm = File(fileNameTm)
    var fileTk = File(fileNameTk)

}*/

abstract class CreateFile {
    val fileNameP = "Projects.txt"
    val fileNameTm = "Teams.txt"
    val fileNameTk = "Tasks.txt"

    var fileP = File(fileNameP)
    var fileTm = File(fileNameTm)
    var fileTk = File(fileNameTk)
    //file.createNewFile()
    companion object {
        fun createDBProject() = DBProject()
        fun createDBTask() = DBTask()
        fun createDBTeam() = DBTeam()

        fun createFileProject() = FileProject()

        fun createFileTask() = FileTask()

        fun createFileTeam() = FileTeam()
    }

    abstract infix fun save(data: String)
}

class DBProject(): CreateFile() {
    override fun save(data: String) {
        println("Saved $data to a file")
        File(fileNameP).writeText(data)
        println(data)
    }
}


class DBTask(): CreateFile() {
    override fun save(data: String) {
        println("Saved $data to a file")
        File(fileNameTk).writeText(data)
        println(data)
    }
}


class DBTeam(): CreateFile() {
    override fun save(data: String) {
        println("Saved $data to a file")
        File(fileNameTm).writeText(data)
        println(data)
    }
}




class FileProject(): CreateFile() {
    override fun save(data: String) {
        println("Saved $data to file")
        println(data)
    }
}

class FileTask(): CreateFile() {
    override fun save(data: String) {
        println("Saved $data to file")
        println(data)
    }
}

class FileTeam(): CreateFile() {
    override fun save(data: String) {
        println("Saved $data to file")
        println(data)
    }
}

