package org.github.sleonidy

import com.pixelduke.control.Ribbon
import com.pixelduke.control.ribbon.RibbonGroup
import com.pixelduke.control.ribbon.RibbonTab
import javafx.beans.property.ListProperty
import javafx.collections.FXCollections
import javafx.scene.Node
import tornadofx.View
import tornadofx.getValue
import tornadofx.listProperty

abstract class FXRibbonView(title: String? = null, icon: Node? = null) : View(title, icon) {
    val ribbonTabsProperty: ListProperty<RibbonTab> = listProperty(FXCollections.observableArrayList<RibbonTab>())
    val ribbonTabs by ribbonTabsProperty
    val ribbonGroupsProperty: ListProperty<RibbonGroup> = listProperty(FXCollections.observableArrayList<RibbonGroup>())
    val ribbonGroups by ribbonGroupsProperty
    fun ribbonTabs(op: Ribbon.() -> Unit) {
        val ribbon = Ribbon().apply {
            op()
        }
        ribbonTabs.addAll(ribbon.tabs)
    }

    fun ribbonGroups(op: RibbonTab.() -> Unit) {
        val ribbonTab = RibbonTab().apply {
            op()
        }
        ribbonGroups.addAll(ribbonTab.ribbonGroups)

    }
}
