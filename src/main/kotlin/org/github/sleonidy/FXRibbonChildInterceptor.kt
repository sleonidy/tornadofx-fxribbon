package org.github.sleonidy

import com.pixelduke.control.Ribbon
import com.pixelduke.control.ribbon.QuickAccessBar
import com.pixelduke.control.ribbon.RibbonGroup
import com.pixelduke.control.ribbon.RibbonItem
import com.pixelduke.control.ribbon.RibbonTab
import javafx.event.EventTarget
import javafx.scene.Node
import javafx.scene.control.Button
import tornadofx.ChildInterceptor

class FXRibbonChildInterceptor : ChildInterceptor {
    override fun invoke(parent: EventTarget, node: Node, index: Int?): Boolean {
        return when (parent) {
            is Ribbon -> if (node is RibbonTab) parent.tabs.add(node) else false
            is RibbonTab -> if (node is RibbonGroup) parent.ribbonGroups.add(node) else false
            is RibbonGroup -> if (index == null) parent.nodes.add(node) else {
                parent.nodes.add(index, node); true
            }
            is RibbonItem -> parent.add(node)
            is QuickAccessBar -> parent.add(node)
            else -> false
        }
    }

    private fun RibbonItem.add(node: Node): Boolean {
        if (item != null)
            throw IllegalArgumentException("Ribbon Item $this already has an item.")
        item = node
        return true
    }

    private fun QuickAccessBar.add(node: Node): Boolean {
        if (node !is Button)
            return false
        return quickAccessBuilderTarget!!.invoke(this).add(node)
    }
}