package com.example.taurus.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.util.StrUtil;
import com.example.taurus.model.dto.BackupDto;
import com.example.taurus.model.enums.CommonParamsEnum;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TaurusUtils {
    public static List<BackupDto> getBackUps(String dir) {
        final StrBuilder srcPathStr = new StrBuilder(System.getProperties().getProperty("user.home"));
        srcPathStr.append("/taurus/backup/");
        srcPathStr.append(dir);
        final File srcPath = new File(srcPathStr.toString());
        final File[] files = srcPath.listFiles();
        final List<BackupDto> backupDtos = new ArrayList<>();
        BackupDto backupDto = null;
        //遍历文件
        if (null != files) {
            for (File file : files) {
                if (file.isFile()) {
                    if (StrUtil.equals(file.getName(), ".DS_Store")) {
                        continue;
                    }
                    backupDto = new BackupDto();
                    backupDto.setFileName(file.getName());
                    backupDto.setCreateAt(getCreateTime(file.getAbsolutePath()));
                    backupDto.setFileType(FileUtil.getType(file));
                    backupDto.setFileSize(parseSize(file.length()));
                    backupDtos.add(backupDto);
                }
            }
        }
        return backupDtos;
    }

    /**
     * 转换文件大小
     *
     * @param size size
     * @return String
     */
    public static String parseSize(long size) {
        if (size < CommonParamsEnum.BYTE.getValue()) {
            return size + "B";
        } else {
            size = size / 1024;
        }
        if (size < CommonParamsEnum.BYTE.getValue()) {
            return size + "KB";
        } else {
            size = size / 1024;
        }
        if (size < CommonParamsEnum.BYTE.getValue()) {
            size = size * 100;
            return size / 100 + "." + size % 100 + "MB";
        } else {
            size = size * 100 / 1024;
            return size / 100 + "." + size % 100 + "GB";
        }
    }

    /**
     * 获取文件创建时间
     *
     * @param srcPath 文件绝对路径
     * @return 时间
     */
    public static Date getCreateTime(String srcPath) {
        final Path path = Paths.get(srcPath);
        final BasicFileAttributeView basicview = Files.getFileAttributeView(path, BasicFileAttributeView.class, LinkOption.NOFOLLOW_LINKS);
        BasicFileAttributes attr;
        try {
            attr = basicview.readAttributes();
            final Date createDate = new Date(attr.creationTime().toMillis());
            return createDate;
        } catch (Exception e) {
            e.printStackTrace();
        }
        final Calendar cal = Calendar.getInstance();
        cal.set(1970, 0, 1, 0, 0, 0);
        return cal.getTime();
    }

    /**
     * 获取文件长和宽
     *
     * @param file file
     * @return String
     */
    public static String getImageWh(File file) {
        try {
            final BufferedImage image = ImageIO.read(new FileInputStream(file));
            return image.getWidth() + "x" + image.getHeight();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
