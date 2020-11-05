data class Project(
        var ProjectID: Int = 0,
        var Commissioner: String = "Name",
        var ProjectMng: String = "Name",
        var NumOfAssignedTasks: Int = 0,
        var AssignedTeamsID: Int = 0) {
    //
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

    fun loadProjects(): List<Project> {
        for (i in project.indices) {
            project.add(project[i])
        }
        return project
    }
}