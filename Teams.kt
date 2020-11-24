import java.io.*


data class Teams(
        var TeamID: Int = 0,
        var TeamLeader: String = "",
        var TeamMembers: String = "",
        var TeamLoc: String = "") {
}

class TeamHandler() {
    val team = mutableListOf<Teams>()

    fun createTeam(
            TeamID: String,
            TeamLeader: String,
            TeamMembers: String,
            TeamLoc: String,
    ): List<Teams> {
        team.add(
                Teams(
                        TeamID = if (TeamID.isEmpty()) 0 else TeamID.toInt(),
                        TeamLeader = if (TeamLeader.isEmpty()) "Unknown Team Leader" else TeamLeader,
                        TeamMembers = if (TeamMembers.isEmpty()) "Unknown Team Members" else TeamMembers,
                        TeamLoc = if (TeamLoc.isEmpty()) "Unknown TeamLoc" else TeamLoc,
                )
        )
        return team
    }
    
    fun save(team: List<Teams>?) {
        CreateFile.createFileTeam() save team.toString()
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
                fileLines = fileLines.replace(")", "")
                val parts: Array<String> = fileLines.substring(1, fileLines.length - 1).split("\\]\\[".toRegex()).toTypedArray() // Creates Array of Teams via split()
                val allParts = Array<Array<String>>(parts.size) { Array<String>(4) { "" } } // Make 3D Array with dimensions: Teams vs. Teams Parameters (ID, etc)
                for (i in parts.indices) {
                    allParts[i] = parts[i].split(",".toRegex()).toTypedArray() // For each Team, input their respective Team Parameters into Array via split()
                }
                for (i in parts.indices) {
                    createTeam(
                            allParts[i][0],
                            allParts[i][1],
                            allParts[i][2],
                            allParts[i][3]
                    ) // Creates an object of type Teams for each, using Parameter data
                }
            }
            br.close()
            return team // Returns the list "team" as the createTeam function already added the Teams to it
        } catch (e: FileNotFoundException) {
            println("Error: File Not Found")
        } catch (e: IOException) {
            println("Error: IO Exception")
        }
        return null
    }

    fun uniqueIDCheck(ID: String): Boolean {
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
                fileLines = fileLines.replace(")", "")
                val parts: Array<String> = fileLines.substring(1, fileLines.length - 1).split("\\]\\[".toRegex()).toTypedArray() // Creates Array of Teams via split()
                val allParts = Array<Array<String>>(parts.size) { Array<String>(4) { "" } } // Make 3D Array with dimensions: Teams vs. Teams Parameters (ID, etc)
                for (i in parts.indices) {
                    allParts[i] = parts[i].split(",".toRegex()).toTypedArray() // For each Team, input their respective Team Parameters into Array via split()
                }

                // Loads Teams from File, then this block checks if inputted Team ID is existing
                var emptyInput = false
                if(ID == "") emptyInput = true
                for (i in parts.indices) {
                    for (j in allParts[i].indices) {
                        if (allParts[i][0] == ID || (emptyInput && allParts[i][0] == "0")) { // if TeamIDF text field is empty, TeamID is 0 so check if it exists
                            println("Error: ID is not unique!")
                            br.close()
                            return false
                        }
                    }
                }
            }
            br.close()
            return true
        } catch (e: FileNotFoundException) {
            println("Error: File Not Found")
        } catch (e: IOException) {
            println("Error: IO Exception")
        }
        return false
    }
}