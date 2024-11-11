package org.github.sleonidy

import tornadofx.*


class RibbonWorkspaceTestApp : App(MyRibbonWorkspace::class){
}

class MyRibbonWorkspace:FXRibbonWorkspace(){

    override fun onDock() {
        super.onDock()
        dock<RibbonWorkspaceTestAppView1>()
        dock<RibbonWorkspaceTestAppView2>()
    }
}

class RibbonWorkspaceTestAppView1 : FXRibbonView("RibbonWorkspaceTestAppView1") {
    override val root = borderpane {
        center{
            label(title)
        }
    }

    init {
        ribbonTabs {
            ribbonTab("One")
            ribbonTab("Two")
        }
        ribbonGroups {
            ribbonGroup("A") {
                button("A")

            }
            ribbonGroup("B") {
                button("B")

            }
        }
    }
}

class RibbonWorkspaceTestAppView2 : FXRibbonView("RibbonWorkspaceTestAppView2") {
    override val root = borderpane {
        center{
            label(title)
        }
    }

    init {
        ribbonTabs {
            ribbonTab("Three")
            ribbonTab("Four")
        }
        ribbonGroups {
            ribbonGroup("C") {
                button("D")

            }
            ribbonGroup("E") {
                button("F")

            }
        }
    }
}