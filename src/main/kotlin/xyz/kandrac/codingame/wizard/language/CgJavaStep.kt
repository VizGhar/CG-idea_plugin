package xyz.kandrac.codingame.wizard.language

import com.intellij.ide.wizard.NewProjectWizardStep
import com.intellij.ide.wizard.util.CommentNewProjectWizardStep
import com.intellij.openapi.project.Project
import xyz.kandrac.codingame.CodingameWizardBundle
import xyz.kandrac.codingame.wizard.GENERATOR_LANGUAGE_JAVA
import xyz.kandrac.codingame.wizard.GeneratorContext

class CgJavaStep(parent: NewProjectWizardStep) : CommentNewProjectWizardStep(parent) {
    override val comment: String = CodingameWizardBundle.getMessage("codingame.new.project.wizard.build.tool.comment", "Java")

    override fun setupProject(project: Project) {
        GeneratorContext.language = GENERATOR_LANGUAGE_JAVA
    }
}
