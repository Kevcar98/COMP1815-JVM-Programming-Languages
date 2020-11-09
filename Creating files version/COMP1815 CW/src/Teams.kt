import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.FileReader
import java.io.IOException
import java.util.*

data class Teams(var TeamID: Int=0,
                 var TeamLeader: String = "Name",
                 var TeamMembers: String = "Name",
                 var TeamLoc: String = "Name",
                 var AssignedTasksID: Int=0,
                 var ProjectID: Int=0){

}


class TeamHandler() {
    val team = mutableListOf<Teams>()

    fun createTeam(TeamID: String, TeamLeader: String, TeamMembers: String, TeamLoc: String, AssignedTasksID: String, ProjectID: String): List<Teams> {
        team.add(Teams(
                TeamID = if (TeamID.isEmpty()) 0 else TeamID.toInt(),
                TeamLeader = if (TeamLeader.isEmpty()) "Unknown Team Leader" else TeamLeader,
                TeamMembers = if (TeamMembers.isEmpty()) "Unknown Team Members" else TeamMembers,
                TeamLoc = if (TeamLoc.isEmpty()) "Unknown Team Location" else TeamLoc,
                AssignedTasksID = if (AssignedTasksID.isEmpty()) 0 else AssignedTasksID.toInt(),
                ProjectID = if (ProjectID.isEmpty()) 0 else ProjectID.toInt(),

        ))
        return team
    }

    fun save(team: List<Teams>?) {
        CreateFile.createFileTask() save team.toString()
    }

    fun loadTeams(): List<Teams>? {
        try {
            val fr = FileReader("Teams.txt")
            val br = BufferedReader(fr)
            var fileLines: String
            while (br.ready()) {
                fileLines = br.readLine()
                fileLines = fileLines.replace("Teams(", "") // Formatting the read input from Teams.txt to parse data into Arrays
                fileLines = fileLines.replace("TeamID=", "")
                fileLines = fileLines.replace(" TeamLeader=", "")
                fileLines = fileLines.replace(" TeamMembers=", "")
                fileLines = fileLines.replace(" TeamLoc=", "")
                fileLines = fileLines.replace(" AssignedTasksID=", "")
                fileLines = fileLines.replace(" ProjectID=", "")
                fileLines = fileLines.replace(")", "")
                val parts: Array<String> = fileLines.substring(1, fileLines.length - 1).split("\\]\\[".toRegex()).toTypedArray() // Creates Array of Teams via split()
                val allParts = Array<Array<String>>(parts.size) { Array<String>(6) {""} } // Make 3D Array with dimensions: Teams vs. Teams Parameters (ID, etc)
                for (i in parts.indices) {
                    allParts[i] = parts[i].split(",".toRegex()).toTypedArray() // For each Team, input their respective Team Parameters into Array via split()
                }
                for (i in parts.indices) {
                    createTeam(allParts[i][0], allParts[i][1], allParts[i][2], allParts[i][3], allParts[i][4], allParts[i][5]) // Creates an object of type Teams for each, using Parameter data
                }
            }
            return team // Returns the list "task" as the createTask function already added the Tasks to it
        } catch (e: FileNotFoundException) {
            println("Error: File Not Found")
        } catch (e: IOException) {
            println("Error: IO Exception")
        }
        return null
    }
}
