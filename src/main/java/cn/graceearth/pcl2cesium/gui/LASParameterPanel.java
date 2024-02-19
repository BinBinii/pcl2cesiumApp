package cn.graceearth.pcl2cesium.gui;

import cn.graceearth.liblas.JniUtil;
import cn.graceearth.liblas.LasReader;
import cn.graceearth.pcl2cesum.pcl2cesium.LASPntcGenerator;
import de.tum.gis.tiles3d.database.sqlite.SqliteDBManagerFactory;
import de.tum.gis.tiles3d.generator.PntcConfig;
import de.tum.gis.tiles3d.generator.PntcGenerationException;
import de.tum.gis.tiles3d.gui.StatusDialog;
import de.tum.gis.tiles3d.util.Logger;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.derby.iapi.sql.LanguageProperties;
import org.citydb.util.gui.GuiUtil;
import org.lastools.LASPoint;
import org.lastools.LASReader;

/* loaded from: pcl2cesiumApp.jar:BOOT-INF/classes/cn/graceearth/pcl2cesium/gui/LASParameterPanel.class */
public class LASParameterPanel extends JPanel {

    /* renamed from: BT */
    protected static final int f39BT = 3;
    private Box containerPanel;
    private JPanel inputPanel;
    private JPanel sridPanel;
    private JPanel colorBitSizePanel;
    private JPanel zScaleFactorPanel;
    private JPanel zOffsetPanel;
    private JPanel tileSizePanel;
    private JPanel maxNumOfPointsPerTilePanel;
    private JPanel stepPanel;
    private JPanel outputPanel;
    private JFrame parentFrame;
    private JTextField inputBrowseTextField = new JTextField();
    private JButton inputBrowseButton = new JButton();
    private JTextField sridInputField = new JTextField();
    private JTextField SeparatorCharacterInputField = new JTextField();
    private JTextField colorBitSizeInputField = new JTextField();
    private JTextField zScaleFactorInputField = new JTextField();
    private JTextField zOffsetInputField = new JTextField();
    private JTextField tileSizeInputField = new JTextField();
    private JTextField maxNumOfPointsPerTileInputField = new JTextField();
    private JTextField stepInputField = new JTextField();
    private JTextField outputBrowserTextField = new JTextField();
    private JButton outputBrowseButton = new JButton();
    private JButton runButton = new JButton();

    public LASParameterPanel(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        initGui();
        setLabels();
        addListeners();
        initDefaultSettings();
    }

    private void initGui() {
        this.containerPanel = Box.createVerticalBox();
        this.containerPanel.add(Box.createRigidArea(new Dimension(0, 18)));
        this.inputPanel = new JPanel();
        this.inputPanel.setLayout(new GridBagLayout());
        this.inputPanel.add(this.inputBrowseTextField, GuiUtil.setConstraints(0, 0, 1.0d, 1.0d, 2, 3, 18, 3, 3));
        this.inputBrowseTextField.setColumns(10);
        this.inputPanel.add(this.inputBrowseButton, GuiUtil.setConstraints(3, 0, 0.0d, 0.0d, 0, 3, 3, 3, 3));
        this.containerPanel.add(this.inputPanel);
        this.containerPanel.add(Box.createRigidArea(new Dimension(0, 18)));
        this.sridPanel = new JPanel();
        this.sridPanel.setLayout(new GridBagLayout());
        this.sridPanel.add(this.sridInputField, GuiUtil.setConstraints(0, 0, 1.0d, 1.0d, 2, 3, 18, 3, 3));
        this.sridInputField.setColumns(10);
        this.containerPanel.add(this.sridPanel);
        this.containerPanel.add(Box.createRigidArea(new Dimension(0, 18)));
        this.colorBitSizePanel = new JPanel();
        this.colorBitSizePanel.setLayout(new GridBagLayout());
        this.colorBitSizePanel.add(this.colorBitSizeInputField, GuiUtil.setConstraints(0, 0, 1.0d, 1.0d, 2, 3, 18, 3, 3));
        this.colorBitSizeInputField.setColumns(10);
        this.containerPanel.add(this.colorBitSizePanel);
        this.containerPanel.add(Box.createRigidArea(new Dimension(0, 18)));
        this.zOffsetPanel = new JPanel();
        this.zOffsetPanel.setLayout(new GridBagLayout());
        this.zOffsetPanel.add(this.zOffsetInputField, GuiUtil.setConstraints(0, 0, 1.0d, 1.0d, 2, 3, 18, 3, 3));
        this.zOffsetInputField.setColumns(10);
        this.containerPanel.add(this.zOffsetPanel);
        this.containerPanel.add(Box.createRigidArea(new Dimension(0, 18)));
        this.zScaleFactorPanel = new JPanel();
        this.zScaleFactorPanel.setLayout(new GridBagLayout());
        this.zScaleFactorPanel.add(this.zScaleFactorInputField, GuiUtil.setConstraints(0, 0, 1.0d, 1.0d, 2, 3, 18, 3, 3));
        this.zScaleFactorInputField.setColumns(10);
        this.containerPanel.add(this.zScaleFactorPanel);
        this.containerPanel.add(Box.createRigidArea(new Dimension(0, 18)));
        this.tileSizePanel = new JPanel();
        this.tileSizePanel.setLayout(new GridBagLayout());
        this.tileSizePanel.add(this.tileSizeInputField, GuiUtil.setConstraints(0, 0, 1.0d, 1.0d, 2, 3, 18, 3, 3));
        this.tileSizeInputField.setColumns(10);
        this.containerPanel.add(this.tileSizePanel);
        this.containerPanel.add(Box.createRigidArea(new Dimension(0, 18)));
        this.maxNumOfPointsPerTilePanel = new JPanel();
        this.maxNumOfPointsPerTilePanel.setLayout(new GridBagLayout());
        this.maxNumOfPointsPerTilePanel.add(this.maxNumOfPointsPerTileInputField, GuiUtil.setConstraints(0, 0, 1.0d, 1.0d, 2, 3, 18, 3, 3));
        this.maxNumOfPointsPerTileInputField.setColumns(10);
        this.containerPanel.add(this.maxNumOfPointsPerTilePanel);
        this.containerPanel.add(Box.createRigidArea(new Dimension(0, 18)));
        this.stepPanel = new JPanel();
        this.stepPanel.setLayout(new GridBagLayout());
        this.stepPanel.add(this.stepInputField, GuiUtil.setConstraints(0, 0, 1.0d, 1.0d, 2, 3, 18, 3, 3));
        this.stepInputField.setColumns(10);
        this.containerPanel.add(this.stepPanel);
        this.containerPanel.add(Box.createRigidArea(new Dimension(0, 18)));
        this.outputPanel = new JPanel();
        this.outputPanel.setLayout(new GridBagLayout());
        this.outputPanel.add(this.outputBrowserTextField, GuiUtil.setConstraints(0, 0, 1.0d, 1.0d, 2, 3, 18, 3, 3));
        this.outputBrowserTextField.setColumns(10);
        this.outputPanel.add(this.outputBrowseButton, GuiUtil.setConstraints(3, 0, 0.0d, 0.0d, 0, 3, 3, 3, 3));
        this.containerPanel.add(this.outputPanel);
        this.containerPanel.add(Box.createRigidArea(new Dimension(0, 18)));
        JPanel runButtonPanel = new JPanel();
        runButtonPanel.setLayout(new GridBagLayout());
        runButtonPanel.add(this.runButton, GuiUtil.setConstraints(0, 0, 1.0d, 0.0d, 0, 5, 5, 5, 5));
        this.containerPanel.add(runButtonPanel);
        JPanel dummyPanel = new JPanel(new BorderLayout());
        dummyPanel.add(this.containerPanel, "North");
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
        add(dummyPanel);
    }

    public void setLabels() {
        this.inputPanel.setBorder(BorderFactory.createTitledBorder("输入路径 "));
        this.inputBrowseButton.setText("浏览...");
        this.sridPanel.setBorder(BorderFactory.createTitledBorder("空间参考SRID"));
        this.colorBitSizePanel.setBorder(BorderFactory.createTitledBorder("颜色位大小8或16"));
        this.zScaleFactorPanel.setBorder(BorderFactory.createTitledBorder("高度调整尺度因子 (0.0 - 1.0) (默认1)"));
        this.zOffsetPanel.setBorder(BorderFactory.createTitledBorder("高度调整z偏移 (默认0)"));
        this.tileSizePanel.setBorder(BorderFactory.createTitledBorder("每个瓦片的边长(默认100)"));
        this.maxNumOfPointsPerTilePanel.setBorder(BorderFactory.createTitledBorder("每个瓦片的最大点数（默认10000个点）"));
        this.stepPanel.setBorder(BorderFactory.createTitledBorder("点云读取步长(步长为1读取100%， 步长为10读取10%)"));
        this.outputPanel.setBorder(BorderFactory.createTitledBorder("输出路径"));
        this.outputBrowseButton.setText("选择...");
        this.runButton.setText("运行");
    }

    private void addListeners() {
        this.inputBrowseButton.addActionListener(new ActionListener() { // from class: cn.graceearth.pcl2cesium.gui.LASParameterPanel.1
            public void actionPerformed(ActionEvent e) {
            }
        });
        this.runButton.addActionListener(new ActionListener() { // from class: cn.graceearth.pcl2cesium.gui.LASParameterPanel.2
            public void actionPerformed(ActionEvent e) {
                Thread thread = new Thread() { // from class: cn.graceearth.pcl2cesium.gui.LASParameterPanel.2.1
                    @Override // java.lang.Thread, java.lang.Runnable
                    public void run() {
                        LASParameterPanel.this.doProcess();
                    }
                };
                thread.start();
            }
        });
        this.inputBrowseButton.addActionListener(new ActionListener() { // from class: cn.graceearth.pcl2cesium.gui.LASParameterPanel.3
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setDialogTitle("选择源数据");
                chooser.setFileSelectionMode(0);
                FileNameExtensionFilter filter = new FileNameExtensionFilter("LAS Files", new String[]{"LAS", "laz", "xyzcirn"});
                chooser.setFileFilter(filter);
                chooser.setCurrentDirectory(new File(LASParameterPanel.this.inputBrowseTextField.getText()).getParentFile());
                int result = chooser.showOpenDialog(LASParameterPanel.this.getTopLevelAncestor());
                if (result == 1) {
                    return;
                }
                String browseString = chooser.getSelectedFile().toString();
                LASParameterPanel.this.inputBrowseTextField.setText(browseString);
                if (PntcConfig.PROCESS_VERSION == 2) {
                    JniUtil.loadLibrary();
                    int colorBitSize = LasReader.readColorBit(browseString);
                    LASParameterPanel.this.colorBitSizeInputField.setText(String.valueOf(colorBitSize));
                } else if (PntcConfig.PROCESS_VERSION == 3) {
                    int colorBitSize2 = LASParameterPanel.this.readColorBit(browseString);
                    LASParameterPanel.this.colorBitSizeInputField.setText(String.valueOf(colorBitSize2));
                }
            }
        });
        this.outputBrowseButton.addActionListener(new ActionListener() { // from class: cn.graceearth.pcl2cesium.gui.LASParameterPanel.4
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setDialogTitle("选择输出路径");
                chooser.setFileSelectionMode(1);
                chooser.setCurrentDirectory(new File(LASParameterPanel.this.outputBrowserTextField.getText()).getParentFile());
                int result = chooser.showSaveDialog(LASParameterPanel.this.getTopLevelAncestor());
                if (result == 1) {
                    return;
                }
                String browseString = chooser.getSelectedFile().toString();
                LASParameterPanel.this.outputBrowserTextField.setText(browseString);
            }
        });
    }

    public int readColorBit(String fileName) {
        int colorBit = 8;
        LASReader reader = new LASReader(fileName);
        for (int i = 0; i < 100000; i++) {
            Boolean ok = Boolean.valueOf(reader.readPoint());
            if (!ok.booleanValue()) {
                break;
            }
            LASPoint point = reader.getPoint();
            int r = point.getR();
            int g = point.getG();
            int b = point.getB();
            if (r > 255 || g > 255 || b > 255) {
                colorBit = 16;
                break;
            }
        }
        reader.close();
        return colorBit;
    }

    public String getJarPath() {
        String path = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        if (System.getProperty("os.name").contains("dows")) {
            path = path.substring(1, path.length());
        }
        if (path.contains("jar")) {
            String path2 = path.substring(0, path.lastIndexOf("."));
            return path2.substring(0, path2.lastIndexOf("/"));
        }
        return path;
    }

    private void initDll() {
        String path = getJarPath();
        System.out.println("load libary begin...");
        if (path.contains("target/classes/")) {
            System.loadLibrary("src/main/resources/JniUtils");
            System.loadLibrary("src/main/resources/liblas");
            System.loadLibrary("src/main/resources/liblasJNI");
        } else {
            String path2 = path.replace("ile:/", "") + "/config/";
            System.out.println("dll pathL: " + path2);
            System.load(path2 + "JniUtils.dll");
            System.load(path2 + "liblas.dll");
            System.load(path2 + "liblasJNI.dll");
        }
        System.out.println("load libary end!");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doProcess() {
        String inputPath = this.inputBrowseTextField.getText();
        if (inputPath.trim().equals("")) {
            errorMessage("Incorrect parameter value", "请设置一个有效的文件路径");
            return;
        }
        String srid = this.sridInputField.getText();
        if (srid.trim().equals("")) {
            errorMessage("Incorrect parameter value", "请设置一个有效的SRID值");
            return;
        }
        String separatorCharacter = this.SeparatorCharacterInputField.getText().trim();
        if (separatorCharacter.trim().equals("")) {
        }
        String colorBitSizeText = this.colorBitSizeInputField.getText().trim();
        if (colorBitSizeText.equals("8") || colorBitSizeText.equals(LanguageProperties.BULK_FETCH_DEFAULT)) {
            int colorBitSize = Integer.valueOf(colorBitSizeText).intValue();
            double zScaleFactor = 1.0d;
            if (this.zScaleFactorInputField.getText().trim() != "") {
                try {
                    zScaleFactor = Double.parseDouble(this.zScaleFactorInputField.getText().trim());
                    if (zScaleFactor > 1.0d || zScaleFactor < 0.0d) {
                        errorMessage("Incorrect parameter value", "Invalid Z scale factor value, it must be a value in the range of (0.0 - 1.0)");
                        return;
                    }
                } catch (NumberFormatException e) {
                    errorMessage("Incorrect parameter value", "Invalid scale factor value");
                    return;
                }
            }
            double zOffset = 0.0d;
            if (this.zOffsetInputField.getText().trim() != "") {
                try {
                    zOffset = Double.parseDouble(this.zOffsetInputField.getText().trim());
                } catch (NumberFormatException e2) {
                    errorMessage("Incorrect parameter value", "Invalid zOffset value");
                    return;
                }
            }
            double tileSize = 100.0d;
            if (this.tileSizeInputField.getText().trim() != "") {
                try {
                    tileSize = Double.parseDouble(this.tileSizeInputField.getText().trim());
                    if (tileSize <= 0.0d) {
                        errorMessage("Incorrect parameter value", "tile size value must bigger than 0");
                        return;
                    }
                } catch (NumberFormatException e3) {
                    errorMessage("Incorrect parameter value", "Invalid tile size value");
                    return;
                }
            }
            int maxNumOfPointsPerTile = 10000;
            if (this.maxNumOfPointsPerTileInputField.getText().trim() != "") {
                try {
                    maxNumOfPointsPerTile = Integer.parseInt(this.maxNumOfPointsPerTileInputField.getText().trim());
                    if (maxNumOfPointsPerTile < 0) {
                        errorMessage("Incorrect parameter value", "The value must equal to or bigger than 0");
                        return;
                    }
                } catch (NumberFormatException e4) {
                    errorMessage("Incorrect parameter value", "Invalid geometricError value");
                    return;
                }
            }
            String stepStr = this.stepInputField.getText();
            if (stepStr.trim().equals("")) {
                errorMessage("Incorrect parameter value", "请输入一个整型值");
                return;
            }
            try {
                Integer step = Integer.valueOf(stepStr.trim());
                if (step.intValue() < 1) {
                    errorMessage("Incorrect parameter value", "请输入大于0的整型值");
                    return;
                }
                String outputPath = this.outputBrowserTextField.getText();
                File f = new File(outputPath);
                if (!f.isDirectory()) {
                    errorMessage("Incorrect parameter value", "请设置输出路径");
                    return;
                }
                String suffix = String.valueOf(System.currentTimeMillis() / 1000).substring(6);
                String bb = getInputName(this.inputBrowseTextField.getText()) + "_" + suffix;
                String outputPath2 = outputPath + "\\" + bb;
                Logger.info("输出目录:" + outputPath2);
                File file = new File(outputPath2);
                if (!file.exists()) {
                    file.mkdirs();
                }
                PntcConfig config = new PntcConfig();
                config.setInputPath(inputPath);
                config.setSrid(srid);
                config.setColorBitSize(colorBitSize);
                config.setZScaleFactor(zScaleFactor);
                config.setzOffset(zOffset);
                config.setTileSize(tileSize);
                config.setMaxNumOfPointsPerTile(maxNumOfPointsPerTile);
                config.setOutputFolderPath(outputPath2);
                config.setStep(step.intValue());
                final StatusDialog exportDialog = new StatusDialog(this.parentFrame);
                SwingUtilities.invokeLater(new Runnable() { // from class: cn.graceearth.pcl2cesium.gui.LASParameterPanel.5
                    @Override // java.lang.Runnable
                    public void run() {
                        exportDialog.setLocationRelativeTo(LASParameterPanel.this.parentFrame);
                        exportDialog.setVisible(true);
                    }
                });
                SqliteDBManagerFactory dbManagerFactory = new SqliteDBManagerFactory(config);
                final LASPntcGenerator generator = new LASPntcGenerator(config, dbManagerFactory);
                exportDialog.getCancelButton().addActionListener(new ActionListener() { // from class: cn.graceearth.pcl2cesium.gui.LASParameterPanel.6
                    public void actionPerformed(ActionEvent e5) {
                        SwingUtilities.invokeLater(new Runnable() { // from class: cn.graceearth.pcl2cesium.gui.LASParameterPanel.6.1
                            @Override // java.lang.Runnable
                            public void run() {
                                generator.setShouldRun(false);
                            }
                        });
                    }
                });
                boolean success = false;
                try {
                    long t1 = System.currentTimeMillis();
                    if (PntcConfig.PROCESS_VERSION == 2) {
                        success = generator.doProcess2();
                    } else if (PntcConfig.PROCESS_VERSION == 3) {
                        success = generator.doProcess3();
                    }
                    long t2 = System.currentTimeMillis();
                    Logger.info("数据处理时间： " + (t2 - t1) + "ms");
                } catch (PntcGenerationException e5) {
                    Logger.error(e5.getMessage());
                    Throwable cause = e5.getCause();
                    while (cause != null) {
                        Logger.error("Cause: " + cause.getMessage());
                        cause = cause.getCause();
                        generator.setShouldRun(false);
                    }
                }
                SwingUtilities.invokeLater(new Runnable() { // from class: cn.graceearth.pcl2cesium.gui.LASParameterPanel.7
                    @Override // java.lang.Runnable
                    public void run() {
                        exportDialog.dispose();
                    }
                });
                if (success) {
                    Logger.info("导出数据成功.");
                    return;
                } else {
                    Logger.warn("导出终止.");
                    return;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                errorMessage("Incorrect parameter value", "请输入一个整型值");
                return;
            }
        }
        errorMessage("Incorrect parameter value", "无效的颜色值, 请设置8 或 16");
    }

    private String getInputName(String src) {
        String[] arr = src.split("\\\\");
        String name = arr[arr.length - 1];
        String nn = name.substring(0, name.indexOf("."));
        return nn;
    }

    private void initDefaultSettings() {
        this.tileSizeInputField.setText("100");
        this.zOffsetInputField.setText("0");
        this.sridInputField.setText("32650");
        this.zScaleFactorInputField.setText("1");
        this.SeparatorCharacterInputField.setText(" ");
        this.colorBitSizeInputField.setText("");
        this.maxNumOfPointsPerTileInputField.setText("10000");
        this.inputBrowseTextField.setText("");
        this.outputBrowserTextField.setText("");
        this.stepInputField.setText("10");
    }

    private void errorMessage(String title, String text) {
        JOptionPane.showMessageDialog(this, text, title, 0);
    }
}