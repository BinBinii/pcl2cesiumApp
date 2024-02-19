package cn.graceearth.pcl2cesium;

import cn.graceearth.liblas.JniUtil;
import cn.graceearth.pcl2cesium.gui.LASMainGui;
import de.tum.gis.tiles3d.generator.PntcConfig;
import de.tum.gis.tiles3d.util.Logger;

import java.io.IOException;
import java.lang.reflect.Field;
import javax.swing.UIManager;

import org.lastools.LASlibJNI;

public class LASMain {
    public static void main(String[] args) throws Exception {
        (new cn.graceearth.pcl2cesium.LASMain()).startUp(args);
    }

    static {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startUp(String[] args) throws IOException {
        LASMainGui mainView = new LASMainGui();
        mainView.initGui();
        if (PntcConfig.PROCESS_VERSION == 2)
            JniUtil.loadLibrary();
        if (PntcConfig.PROCESS_VERSION == 3)
            try {
                LASlibJNI.initialize();
                Logger.info("JNI.initialize完成.");
            } catch (Exception e) {
                Logger.error("JNI.initialize失败.");
                e.printStackTrace();
            }
    }

    public String getJarPath() {
        String path = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        if (System.getProperty("os.name").contains("dows"))
            path = path.substring(1, path.length());
        if (path.contains("jar")) {
            path = path.substring(0, path.lastIndexOf("."));
            return path.substring(0, path.lastIndexOf("/"));
        }
        return path;
    }

    public static void addLibraryDir(String libraryPath) throws IOException {
        try {
            Field field = ClassLoader.class.getDeclaredField("usr_paths");
            field.setAccessible(true);
            String[] paths = (String[]) field.get(null);
            for (int i = 0; i < paths.length; i++) {
                if (libraryPath.equals(paths[i]))
                    return;
            }
            String[] tmp = new String[paths.length + 1];
            System.arraycopy(paths, 0, tmp, 0, paths.length);
            tmp[paths.length] = libraryPath;
            field.set(null, tmp);
        } catch (IllegalAccessException e) {
            throw new IOException("Failedto get permissions to set library path");
        } catch (NoSuchFieldException e) {
            throw new IOException("Failedto get field handle to set library path");
        }
    }
}
