package xyz.kandrac.codingame.wizard.buildsystem

import com.intellij.ide.wizard.AbstractNewProjectWizardStep
import com.intellij.ide.wizard.NewProjectWizardStep
import com.intellij.openapi.project.Project
import xyz.kandrac.codingame.wizard.GENERATOR_BUILD_SYSTEM_GRADLE
import xyz.kandrac.codingame.wizard.GeneratorContext

class CgBuildSystemGradleStep(parentStep: NewProjectWizardStep) : AbstractNewProjectWizardStep(parentStep) {

    override fun setupProject(project: Project) {
        GeneratorContext.buildSystem = GENERATOR_BUILD_SYSTEM_GRADLE
    }
}