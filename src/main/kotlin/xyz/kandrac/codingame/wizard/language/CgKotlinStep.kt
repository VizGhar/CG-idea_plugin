package xyz.kandrac.codingame.wizard.language

import com.intellij.ide.wizard.NewProjectWizardStep
import com.intellij.ide.wizard.util.CommentNewProjectWizardStep
import com.intellij.openapi.project.Project
import xyz.kandrac.codingame.wizard.CodingameWizardBundle
import xyz.kandrac.codingame.wizard.GENERATOR_LANGUAGE_KOTLIN
import xyz.kandrac.codingame.wizard.GeneratorContext

class CgKotlinStep(parent: NewProjectWizardStep) : CommentNewProjectWizardStep(parent) {
    override val comment: String = CodingameWizardBundle.getMessage("codingame.new.project.wizard.build.tool.comment", "Kotlin")

    override fun setupProject(project: Project) {
        GeneratorContext.language = GENERATOR_LANGUAGE_KOTLIN
    }
}
