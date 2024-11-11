package org.github.sleonidy

import com.pixelduke.control.Ribbon
import com.pixelduke.control.ribbon.RibbonTab
import javafx.collections.ListChangeListener
import javafx.scene.Node
import javafx.scene.control.Tab
import javafx.scene.control.TabPane
import tornadofx.*
import kotlin.reflect.KClass

open class FXRibbonWorkspace(title: String? = null, icon: Node? = null) : View(title, icon) {
    val ribbonMainTabTitleProperty = stringProperty(title)
    var ribbonMainTabTitle by ribbonMainTabTitleProperty
    var ribbon by singleAssign<Ribbon>()
    var ribbonMainTab by singleAssign<RibbonTab?>()
    var tabPane by singleAssign<TabPane>()
    var addMainTab: Boolean = true
    override val root = borderpane {
        top {
            ribbon = ribbon {
                if (addMainTab)
                    ribbonMainTab = ribbonTab(ribbonMainTabTitleProperty)
            }
        }
        center {
            tabPane = tabpane()
        }
    }
    val tabPaneListener = ListChangeListener<Tab> { change ->
        while (change.next()) {

        }
    }
    val currentTabListener = ChangeListener<Tab> { _, oldValue: Tab?, newValue: Tab? ->
        oldValue?.let { tab ->
            tab.content.uiComponent<FXRibbonView>()?.run {
                ribbonMainTab?.ribbonGroups?.removeAll(ribbonGroups)
                ribbon.tabs.removeAll(ribbonTabs)
            }
        }
        newValue?.let { tab ->
            tab.content.uiComponent<FXRibbonView>()?.run {
                ribbonMainTab?.ribbonGroups?.addAll(ribbonGroups)
                ribbon.tabs.addAll(ribbonTabs)
            }
        }
    }

    inline fun <reified T : UIComponent> dock(noinline op: Tab.() -> Unit = {}) {
        tabPane
            .tab<T>()
            .also {
                it.op()
            }
    }

    fun <T : UIComponent> dock(componentClass: KClass<T>, op: Tab.() -> Unit = {}) {
        tabPane.tab(componentClass).also {
            it.op()
        }
    }

    fun <T : UIComponent> dock(component: T, op: Tab.() -> Unit = {}) {
        (tabPane.tabs
            .firstOrNull { it.content.properties[UI_COMPONENT_PROPERTY] as? T == component }
            ?: tabPane.tab(component)).also {
            it.op()
        }
    }

    inline fun <reified T : UIComponent> dock(index: Int, noinline op: Tab.() -> Unit = {}) {
        tabPane.tab<T>().also {
            it.op()
        }
    }

    fun <T : UIComponent> dock(index: Int, componentClass: KClass<T>, op: Tab.() -> Unit = {}) {
        tabPane.tab(componentClass).also {
            it.op()
        }
    }

    fun <T : UIComponent> dock(index: Int, component: T, op: Tab.() -> Unit = {}) {
        tabPane.tab(component).also {
            it.op()
        }
    }

    override fun onDock() {
        super.onDock()
        tabPane.selectionModel.selectedItemProperty().addListener(currentTabListener)
    }

    override fun onUndock() {
        super.onUndock()
        tabPane.selectionModel.selectedItemProperty().removeListener(currentTabListener)
    }
}

