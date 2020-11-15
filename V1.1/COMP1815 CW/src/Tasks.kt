import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.FileReader
import java.io.IOException

data class Tasks(
    var TaskID: Int = 0,
    var ProjectID: Int = 0,
    var Commissioner: String = "Name",
    var ProjectMng: String = "Name",
    var Duration: Int = 0,
    var AssignedTeamsID: Int = 0,
    var Progress: Int = 0
) {

}


class TaskHandler() {
    val task = mutableListOf<Tasks>()

    fun createTask(
        TaskID: String,
        ProjectID: String,
        Commissioner: String,
        ProjectMng: String,
        Duration: String,
        AssignedTeamsID: String,
        Progress: String
    ): List<Tasks> {
        task.add(
            Tasks(
                TaskID = if (TaskID.isEmpty()) 0 else TaskID.toInt(),
                ProjectID = if (ProjectID.isEmpty()) 0 else ProjectID.toInt(),
                Commissioner = if (Commissioner.isEmpty()) "Unknown Commissioner" else Commissioner,
                ProjectMng = if (ProjectMng.isEmpty()) "Unknown Project Manager" else ProjectMng,
                Duration = if (Duration.isEmpty()) 0 else Duration.toInt(),
                AssignedTeamsID = if (AssignedTeamsID.isEmpty()) 0 else AssignedTeamsID.toInt(),
                Progress = if (Progress.isEmpty()) 0 else Progress.toInt(),

                )
        )
        return task
    }

    fun save(task: List<Tasks>?) {
        CreateFile.createFileTask() save task.toString()
    }

    fun loadTasks(): List<Tasks>? {
        try {
            val fr = FileReader("Tasks.txt")
            val br = BufferedReader(fr)
            var fileLines: String
            while (br.ready()) {
                fileLines = br.readLine()
                fileLines = fileLines.replace("Tasks(", "") // Formatting the read input from Projects.txt to parse data into Arrays
                fileLines = fileLines.replace("TaskID=", "")
                fileLines = fileLines.replace(" ProjectID=", "")
                fileLines = fileLines.replace(" Commissioner=", "")
                fileLines = fileLines.replace(" ProjectMng=", "")
                fileLines = fileLines.replace(" Duration=", "")
                fileLines = fileLines.replace(" AssignedTeamsID=", "")
                fileLines = fileLines.replace(" Progress=", "")
                fileLines = fileLines.replace(")", "")
                val parts: Array<String> = fileLines.substring(1, fileLines.length - 1).split("\\]\\[".toRegex()).toTypedArray() // Creates Array of Tasks via split()
                val allParts = Array<Array<String>>(parts.size) { Array<String>(7) {""} } // Make 3D Array with dimensions: Tasks vs. Tasks Parameters (ID, etc)
                for (i in parts.indices) {
                    allParts[i] = parts[i].split(",".toRegex()).toTypedArray() // For each Task, input their respective Task Parameters into Array via split()
                }
                for (i in parts.indices) {
                    //allParts[i][1]= allParts[i][1].strip()
                    createTask(
                        allParts[i][0],
                        allParts[i][1],
                        allParts[i][2],
                        allParts[i][3],
                        allParts[i][4],
                        allParts[i][5],
                        allParts[i][6]
                    ) // Creates an object of type Tasks for each, using Parameter data
                }
            }
            return task // Returns the list "task" as the createTask function already added the Tasks to it
        } catch (e: FileNotFoundException) {
            println("Error: File Not Found")
        } catch (e: IOException) {
            println("Error: IO Exception")
        }
        return null
    }

    fun updateTasks(ID: String, Progress: String): List<Tasks>? {
        try {
            val fr = FileReader("Tasks.txt")
            val br = BufferedReader(fr)
            var fileLines: String
            while (br.ready()) {
                fileLines = br.readLine()
                fileLines = fileLines.replace("Tasks(", "") // Formatting the read input from Projects.txt to parse data into Arrays
                fileLines = fileLines.replace("TaskID=", "")
                fileLines = fileLines.replace(" ProjectID=", "")
                fileLines = fileLines.replace(" Commissioner=", "")
                fileLines = fileLines.replace(" ProjectMng=", "")
                fileLines = fileLines.replace(" Duration=", "")
                fileLines = fileLines.replace(" AssignedTeamsID=", "")
                fileLines = fileLines.replace(" Progress=", "")
                fileLines = fileLines.replace(")", "")
                val parts: Array<String> = fileLines.substring(1, fileLines.length - 1).split("\\]\\[".toRegex()).toTypedArray() // Creates Array of Tasks via split()
                val allParts = Array<Array<String>>(parts.size) { Array<String>(7) {""} } // Make 3D Array with dimensions: Tasks vs. Tasks Parameters (ID, etc)
                for (i in parts.indices) {
                    allParts[i] = parts[i].split(",".toRegex()).toTypedArray() // For each Task, input their respective Task Parameters into Array via split()
                }



                allParts[ID.toInt()][6]= Progress
                println(allParts[ID.toInt()][0])
                println(allParts[ID.toInt()][1])
                println(allParts[ID.toInt()][2])
                println(allParts[ID.toInt()][3])
                println(allParts[ID.toInt()][4])
                println(allParts[ID.toInt()][5])
                println(allParts[ID.toInt()][6])

                CreateFile.DeleteTFile()
                for (i in parts.indices) {
                    //allParts[i][1]= allParts[i][1].strip()
                    createTask(
                        allParts[i][0],
                        allParts[i][1],
                        allParts[i][2],
                        allParts[i][3],
                        allParts[i][4],
                        allParts[i][5],
                        allParts[i][6]
                    ) // Creates an object of type Tasks for each, using Parameter data
                    save(task)
                }



            }
            return task // Returns the list "task" as the createTask function already added the Tasks to it
        } catch (e: FileNotFoundException) {
            println("Error: File Not Found")
        } catch (e: IOException) {
            println("Error: IO Exception")
        }




        return null

    }









}





