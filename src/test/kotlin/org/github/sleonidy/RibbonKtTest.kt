package org.github.sleonidy

import tornadofx.*


internal class RibbonTestApp : App(RibbonView::class)

internal class RibbonView : View() {
    override val root = borderpane {
        top {
            ribbon {
                quickAccessBar {
                    buttons {
                        quickAccessBarButton("dasdas")
                    }
                    rightButtons {
                        quickAccessBarButton("dasdas")
                    }
                }
                ribbonTab("Blala") {
                    ribbonGroup("sdadasd") {

                    }
                    ribbonGroup("sdadasd") {
                        //                        ribbonItem("sdadas")
                    }
                }
                ribbonTab("Tab 2") {
                    ribbonGroup("sdadasd") {
                        ribbonItem("sdadas") {
                            button("DASDasdasd") {
                                action {
                                    print("dasdas")
                                }
                            }
                        }
                    }
                    ribbonGroup("sdadasd") {
                        ribbonItem("sdadas") {
                            button {

                            }
                        }
                    }
                }
            }
        }
    }

}