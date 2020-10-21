import java.util.*

data class Project(var ProjectID: Int=0, var Commissioner: String="Name", var ProjectMng: String="Name", var NumOfAssignedTasks: Int=0, var AssignedTeamsID: Int=0){

}

class ProjectHandler() {
    fun createProject(ProjectID: String, Commissioner: String, ProjectMng: String, NumOfAssignedTasks: String, AssignedTeamsID: String): Project {
        return Project()

    }
    fun Save(project:Project, saveToDB: Boolean) {
        if (saveToDB)
            CreateFile.createDBProject() save project.toString()
        else
            CreateFile.createFileProject().save(project.toString())
    }


    }





