package xyz.kandrac.codingame.wizard

import com.intellij.ide.fileTemplates.FileTemplateManager
import com.intellij.ide.projectWizard.generators.IntelliJJavaNewProjectWizardData.Companion.sdk
import com.intellij.ide.util.projectWizard.WizardContext
import com.intellij.ide.wizard.*
import com.intellij.ide.starters.local.generator.AssetsProcessor
import com.intellij.ide.wizard.util.CommentNewProjectWizardStep
import com.intellij.openapi.project.Project
import xyz.kandrac.codingame.MyIcons
import xyz.kandrac.codingame.wizard.gametype.CgGameTypeStep
import xyz.kandrac.codingame.wizard.language.CgLanguageStep
import com.intellij.ide.starters.local.GeneratorTemplateFile
import com.intellij.ide.wizard.NewProjectWizardBaseData.Companion.name
import com.intellij.ide.wizard.NewProjectWizardBaseData.Companion.path
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.projectRoots.Sdk
import xyz.kandrac.codingame.wizard.sdk.CgSdkWizardStep
import java.nio.file.Path
import kotlin.io.path.absolutePathString

// TODO: not correct solution, but hey! it's working
private var wizardAdapter : CodingameNewProjectWizardAdapter? = null
var projectSdk: Sdk? = null

class CodingameNewProjectWizardAdapter : GeneratorNewProjectWizardBuilderAdapter(CodingameNewProjectWizard()) {

    init {
        wizardAdapter = this
    }

    override fun getWeight() = JVM_WEIGHT - 10

}

class CodingameNewProjectWizard : GeneratorNewProjectWizard {

    override val icon = MyIcons.NewCgProjectLogo

    override val id = "codingame_new_project"

    override val name = "Codingame"

    init {
        GeneratorContext.reset()
    }

    override fun createStep(context: WizardContext) =
        RootNewProjectWizardStep(context)
            .chain(::NewProjectWizardBaseStep)
            .chain(::CgSdkComment)
            .chain(::CgSdkWizardStep)
            .chain(::CgLanguageStep)
            .chain(::CgGameTypeStep)
            .chain(::CgAssetsStep)

    class CgSdkComment(parent: NewProjectWizardStep) : CommentNewProjectWizardStep(parent) {

        override val comment = CodingameWizardBundle.getMessage("codingame.new.project.wizard.jdk.title")

    }

    class CgAssetsStep(parent: NewProjectWizardStep) : AbstractNewProjectWizardStep(parent) {

        override fun setupProject(project: Project) {
            wizardAdapter?.moduleJdk = projectSdk
            val ftManager = FileTemplateManager.getInstance(ProjectManager.getInstance().defaultProject)

            val sources = when (GeneratorContext.language) {
                GENERATOR_LANGUAGE_JAVA ->
                    listOf(
                        // test sources
                        GeneratorTemplateFile("src/test/java/Agent.java", ftManager.getJ2eeTemplate("Agent.java")),
                        GeneratorTemplateFile("src/test/java/SkeletonMain.java", ftManager.getJ2eeTemplate("SkeletonMain.java")),

                        // sources
                        GeneratorTemplateFile("src/main/java/com/codingame/game/Player.java", ftManager.getJ2eeTemplate("Player.java")),
                        GeneratorTemplateFile("src/main/java/com/codingame/game/Referee.java", ftManager.getJ2eeTemplate("Referee.java"))
                    )
                GENERATOR_LANGUAGE_KOTLIN ->
                    listOf(
                        // test sources
                        GeneratorTemplateFile("src/test/java/Agent.kt", ftManager.getJ2eeTemplate("Agent.kt")),
                        GeneratorTemplateFile("src/test/java/SkeletonMain.kt", ftManager.getJ2eeTemplate("SkeletonMain.kt")),

                        // sources
                        GeneratorTemplateFile("src/main/java/com/codingame/game/Player.kt", ftManager.getJ2eeTemplate("Player.kt")),
                        GeneratorTemplateFile("src/main/java/com/codingame/game/Referee.kt", ftManager.getJ2eeTemplate("Referee.kt"))
                    )
                else -> throw IllegalStateException("Either $GENERATOR_LANGUAGE_JAVA or $GENERATOR_LANGUAGE_KOTLIN can be selected as `GeneratorContext.language`")
            }

            val buildSystem = when (GeneratorContext.buildSystem) {
                GENERATOR_BUILD_SYSTEM_MAVEN -> listOf(
                    GeneratorTemplateFile("pom.xml", ftManager.getJ2eeTemplate("pom.xml")),
                )
                GENERATOR_BUILD_SYSTEM_GRADLE -> listOf(
                    GeneratorTemplateFile("build.gradle", ftManager.getJ2eeTemplate("build.gradle")),
                    GeneratorTemplateFile("gradle.properties", ftManager.getJ2eeTemplate("gradle.properties")),
                )
                else -> throw IllegalStateException("Either $GENERATOR_BUILD_SYSTEM_MAVEN or $GENERATOR_BUILD_SYSTEM_GRADLE can be selected as `GeneratorContext.buildSystem`")
            }

            val testCases = when (GeneratorContext.gameType) {
                GENERATOR_TYPE_SOLO -> listOf(
                    GeneratorTemplateFile("config/test1.json", ftManager.getJ2eeTemplate("test1.json")),
                )
                GENERATOR_TYPE_OPTIM -> listOf(
                    GeneratorTemplateFile("config/test1.json", ftManager.getJ2eeTemplate("test1.json")),
                )
                else -> emptyList()
            }

            ApplicationManager.getApplication().runWriteAction{
                @Suppress("UnstableApiUsage")
                AssetsProcessor().generateSources(
                    Path.of(path, name).absolutePathString(),
                    // list of all generated sources
                    // - first argument - export path
                    // - second argument - src/main/resources/fileTemplates/j2ee template
                    listOf(
                        // root
                        GeneratorTemplateFile(".gitignore", ftManager.getJ2eeTemplate(".gitignore")),

                        // config
                        GeneratorTemplateFile("config/Boss.py3", ftManager.getJ2eeTemplate("Boss.py3")),
                        GeneratorTemplateFile("config/config.ini", ftManager.getJ2eeTemplate("config.ini")),
                        GeneratorTemplateFile("config/statement_en.html", ftManager.getJ2eeTemplate("statement_en.html")),

                        // test
                        GeneratorTemplateFile("src/test/resources/log4j2.properties", ftManager.getJ2eeTemplate("log4j2.properties")),

                        // view resources
                        GeneratorTemplateFile("src/main/resources/view/config.js", ftManager.getJ2eeTemplate("config.js")),
                        GeneratorTemplateFile("src/main/resources/view/demo.js", ftManager.getJ2eeTemplate("demo.js")),
                    ) + sources + buildSystem + testCases,

                    // variables passed to fileTemplates
                    mapOf(
                        "context" to GeneratorContext
                    )
                )
            }
            wizardAdapter = null
        }
    }
}
