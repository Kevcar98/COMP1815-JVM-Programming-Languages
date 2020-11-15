import java.io.FileNotFoundException
import java.io.IOException
import java.io.BufferedReader
import java.io.FileReader


data class Project(
        var ProjectID: Int = 0,
        var Commissioner: String = "Name",
        var ProjectMng: String = "Name",
        var NumOfAssignedTasks: Int = 0,
        var AssignedTeamsID: Int = 0) {
}

class ProjectHandler() {
    val project = mutableListOf<Project>()

    fun createProject(ProjectID: String, Commissioner: String, ProjectMng: String, NumOfAssignedTasks: String, AssignedTeamsID: String): List<Project> {
        project.add(Project(
                ProjectID = if (ProjectID.isEmpty()) 0 else ProjectID.toInt(),
                Commissioner = if (Commissioner.isEmpty()) "Unknown Commissioner" else Commissioner,
                ProjectMng = if (ProjectMng.isEmpty()) "Unknown Project Manager" else ProjectMng,
                NumOfAssignedTasks = if (NumOfAssignedTasks.isEmpty()) 0 else NumOfAssignedTasks.toInt(),
                AssignedTeamsID = if (AssignedTeamsID.isEmpty()) 0 else AssignedTeamsID.toInt()
        ))
        return project
    }

    fun save(project: List<Project>?) {
        CreateFile.createFileProject() save project.toString()
    }

    fun loadProjects(): List<Project>? {
        try {
            val fr = FileReader("Projects.txt")
            val br = BufferedReader(fr)
            var fileLines: String
            while (br.ready()) {
                fileLines = br.readLine()
                fileLines = fileLines.replace("Project(", "") // Formatting the read input from Projects.txt to parse data into Arrays
                fileLines = fileLines.replace("ProjectID=", "")
                fileLines = fileLines.replace(" Commissioner=", "")
                fileLines = fileLines.replace(" ProjectMng=", "")
                fileLines = fileLines.replace(" NumOfAssignedTasks=", "")
                fileLines = fileLines.replace(" AssignedTeamsID=", "")
                fileLines = fileLines.replace(")", "")
                val parts: Array<String> = fileLines.substring(1, fileLines.length - 1).split("\\]\\[".toRegex()).toTypedArray() // Creates Array of Projects via split()
                val allParts = Array<Array<String>>(parts.size) { Array<String>(5) { "" } } // Make 3D Array with dimensions: Projects vs. Project Parameters (ID, etc)
                for (i in parts.indices) {
                    allParts[i] = parts[i].split(",".toRegex()).toTypedArray() // For each Project, input their respective Project Parameters into Array via split()
                }
                for (i in parts.indices) {
                    createProject(allParts[i][0], allParts[i][1], allParts[i][2], allParts[i][3], allParts[i][4]) // Creates an object of type Project for each, using Parameter data
                }
            }
            return project // Returns the list "project" as the createProject function already added the Projects to it
        } catch (e: FileNotFoundException) {
            println("Error: File Not Found")
        } catch (e: IOException) {
            println("Error: IO Exception")
        }
        return null
    }
}
