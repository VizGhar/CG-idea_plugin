package xyz.kandrac.codingame.wizard.sdk

import com.intellij.ide.JavaUiBundle
import com.intellij.ide.projectWizard.NewProjectWizardCollector.BuildSystem.logSdkChanged
import com.intellij.ide.projectWizard.NewProjectWizardCollector.BuildSystem.logSdkFinished
import com.intellij.ide.projectWizard.generators.IntelliJJavaNewProjectWizardData.Companion.sdk
import com.intellij.ide.wizard.AbstractNewProjectWizardStep
import com.intellij.ide.wizard.NewProjectWizardStep
import com.intellij.openapi.module.StdModuleTypes
import com.intellij.openapi.observable.properties.GraphProperty
import com.intellij.openapi.projectRoots.JavaSdkType
import com.intellij.openapi.projectRoots.Sdk
import com.intellij.openapi.projectRoots.SdkTypeId
import com.intellij.openapi.projectRoots.impl.DependentSdkType
import com.intellij.openapi.roots.ui.configuration.sdkComboBox
import com.intellij.openapi.util.Key
import com.intellij.ui.dsl.builder.*
import xyz.kandrac.codingame.wizard.projectSdk
import javax.swing.JLabel

class CgSdkWizardStep(parent: NewProjectWizardStep) : AbstractNewProjectWizardStep(parent), SdkWizardData {

    override val sdkProperty = propertyGraph.property<Sdk?>(null)
    override var sdk by sdkProperty

    override fun setupUI(builder: Panel) {
        builder.row(label = JLabel("It is recommended to use JDK < 18")) {}
        builder.row(JavaUiBundle.message("label.project.wizard.new.project.jdk")) {
            val sdkTypeFilter = { it: SdkTypeId -> it is JavaSdkType && it !is DependentSdkType }
            sdkComboBox(context, sdkProperty, StdModuleTypes.JAVA.id, sdkTypeFilter)
                .columns(COLUMNS_MEDIUM)
                .whenItemSelectedFromUi {
                    projectSdk = sdk
                    logSdkChanged(sdk)
                }
                .onApply { logSdkFinished(sdk) }
        }.bottomGap(BottomGap.SMALL)
    }
}

interface SdkWizardData {

    val sdkProperty: GraphProperty<Sdk?>

    var sdk: Sdk?
}
