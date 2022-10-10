package com.meishe.patchupdate;

/**
 * @Author: lpf
 * @CreateDate: 2022/10/8 上午11:02
 * @Description:
 */
public class MSPatchUtils {

    static {
        System.loadLibrary("patchupdate");
    }

    /**
     * 执行patch合并
     * @param oldApkPath 旧apk 路径
     * @param newApkPath 新apk路径
     * @param patchPath 补丁路径
     * @return
     */
    public static native int doPatch(String oldApkPath,String newApkPath,String patchPath);
}
