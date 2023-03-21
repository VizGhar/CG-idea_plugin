package xyz.kandrac.codingame.wizard.language

import com.intellij.ide.wizard.AbstractNewProjectWizardStep
import com.intellij.ide.wizard.NewProjectWizardStep
import com.intellij.openapi.project.Project
import xyz.kandrac.codingame.wizard.GENERATOR_LANGUAGE_JAVA
import xyz.kandrac.codingame.wizard.GeneratorContext

class CgJavaStep(parent: NewProjectWizardStep) : AbstractNewProjectWizardStep(parent) {

    override fun setupProject(project: Project) {
        GeneratorContext.language = GENERATOR_LANGUAGE_JAVA
    }
}
