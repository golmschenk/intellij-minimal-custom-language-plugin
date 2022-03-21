package com.olmschenk.minilang.services

import com.intellij.openapi.project.Project
import com.olmschenk.minilang.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
