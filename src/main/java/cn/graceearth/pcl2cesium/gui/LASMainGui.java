package cn.graceearth.pcl2cesium.gui;

import de.tum.gis.tiles3d.gui.ConsolePanel;

import java.awt.GridBagLayout;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;

import org.citydb.util.gui.GuiUtil;

public final class LASMainGui extends JFrame {
    private JSplitPane mainPanel;
    private LASParameterPanel inputPanel;
    private ConsolePanel consolePanel;

    public void initGui() {
        this.inputPanel = new LASParameterPanel(this);
        this.consolePanel = new ConsolePanel(this);
        setDefaultCloseOperation(2);
        setLayout(new GridBagLayout());
        setTitle("pcl2cesiumApp(支持las、laz、xyzcirn)V0.7");
        this.mainPanel = new JSplitPane(1);
        this.mainPanel.setContinuousLayout(true);
        this.mainPanel.setBorder(BorderFactory.createEmptyBorder());
        this.mainPanel.setOpaque(false);
        this.mainPanel.setUI(new BasicSplitPaneUI() { // from class: cn.graceearth.pcl2cesium.gui.LASMainGui.1
            public BasicSplitPaneDivider createDefaultDivider() {
                return new BasicSplitPaneDivider(this) { // from class: cn.graceearth.pcl2cesium.gui.LASMainGui.1.1
                    public void setBorder(Border b) {
                    }
                };
            }
        });
        this.mainPanel.setLeftComponent(this.inputPanel);
        this.mainPanel.setRightComponent(this.consolePanel);
        add(this.mainPanel, GuiUtil.setConstraints(0, 0, 1.0d, 1.0d, 1, 0, 0, 0, 0));
        showWindow();
    }

    private void showWindow() {
        Toolkit t = Toolkit.getDefaultToolkit();
        Integer width = 1280;
        Integer height = 760;
        Integer x = Integer.valueOf((t.getScreenSize().width - width.intValue()) / 2);
        Integer y = Integer.valueOf((t.getScreenSize().height - height.intValue()) / 2);
        setLocation(x.intValue(), y.intValue());
        setSize(width.intValue(), height.intValue());
        setVisible(true);
        Integer dividerLocation = Integer.valueOf((int) (width.intValue() * 0.5d));
        this.mainPanel.setDividerLocation(dividerLocation.intValue());
    }
}