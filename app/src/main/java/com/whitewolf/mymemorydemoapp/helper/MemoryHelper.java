package com.whitewolf.mymemorydemoapp.helper;

import android.os.Build;
import android.os.Environment;
import android.os.StatFs;

import java.io.File;
import java.text.DecimalFormat;

/**
 * Created by smartens on 7/21/2017.
 */

public class MemoryHelper {
    // this is needed for all memory method
    public static StatFs statFs;
    public static final int MEMORY_UNIT_B = 0;
    public static final int MEMORY_UNIT_KB = 1;
    public static final int MEMORY_UNIT_MB = 2;
    public static final int MEMORY_UNIT_GB = 3;
    public static final int MEMORY_UNIT_TB = 4;
    public static final int MEMORY_UNIT_BLOCK = 5;
    public static final int MEMORY_CONSTANT = 1024;

    public static final int PATH_STORAGE_EXTERNAL = 1;
    public static final int PATH_STORAGE_INTERNAL = 2;

    public static File getPath(int pathStorageType) {
        if (pathStorageType == PATH_STORAGE_EXTERNAL)
            return new File(Environment.getExternalStorageDirectory().getPath());
         else
            return new File(Environment.getDataDirectory().getPath());
    }

    /*
       This method returns the number of bytes are in a single block.  This is use in the calc
       to retrieve the number of bytes in a group of blocks.  There are a number of methods that
       return blocks instead of bytes.

       There is a getPath method that accepts as a parameter a directory from the INTERNAL and
       EXTERNAL storage so that memory values can be returned from either with a single function.
     */
    public static long getBlockSize(File pathStorageType) {
        long bytesPerBlock = 0;
        //File path = Environment.getDataDirectory();
        statFs = new StatFs(pathStorageType.getPath());

        // check version
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) {
            bytesPerBlock = statFs.getBlockSize();
        } else {
            bytesPerBlock = statFs.getBlockSizeLong();
        }
        return bytesPerBlock;
    }

    /*
       Getting the bytes for the block count requires using the getBlockSize(). Then multiply
       the getBlockSize() results and the block count itself.  This will yield the number of
       bytes in the block count.
       total bytes = block count * byte size

       There is a getPath method that accepts as a parameter a directory from the INTERNAL and
       EXTERNAL storage so that memory values can be returned from either with a single function.
     */
    public static long calculateBytesPerBlocks(File path, long blocks) {
        long bytesPerBlock = 0;
        //File path = Environment.getDataDirectory();
        statFs = new StatFs(path.getPath());

        bytesPerBlock = getBlockSize(path);

        return (blocks * bytesPerBlock);
    }

    /*
       get number of blocks that are "free and available" to applications

       There is a getPath method that accepts as a parameter a directory from the INTERNAL and
       EXTERNAL storage so that memory values can be returned from either with a single function.
     */
    public static long getAvailableBlocks(File pathStorageType) {
        //File path = Environment.getDataDirectory();
        statFs = new StatFs(pathStorageType.getPath());
        long blocks = 0;
        //long bytesPerBlock = 0;

        // check version
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) {
            blocks = statFs.getAvailableBlocks();
        } else {
            blocks = statFs.getAvailableBlocksLong();
        }
        return blocks;
    }

    /*
       get number of bytes that are "free and available" to applications

       There is a getPath method that accepts as a parameter a directory from the INTERNAL and
       EXTERNAL storage so that memory values can be returned from either with a single function.
     */
    public static long getAvailableBytes(File pathStorageType) {
        //File path = Environment.getDataDirectory();
        statFs = new StatFs(pathStorageType.getPath());
        long bytes = statFs.getAvailableBytes();
        return bytes;
    }

    /*
       get total number of blocks on the system

       There is a getPath method that accepts as a parameter a directory from the INTERNAL and
       EXTERNAL storage so that memory values can be returned from either with a single function.
     */
    public static long getTotalSystemBlocks(File pathStorageType) {
        //File path = Environment.getDataDirectory();
        statFs = new StatFs(pathStorageType.getPath());
        long blocks = 0;

        // check version
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) {
            blocks = statFs.getBlockCount();
        } else {
            blocks = statFs.getBlockCountLong();
        }
        return blocks;
    }

    /*
       Total number of bytes supported by the file system

       There is a getPath method that accepts as a parameter a directory from the INTERNAL and
       EXTERNAL storage so that memory values can be returned from either with a single function.
     */
    public static long getTotalSupportedBytes(File pathStorageType) {
        //File path = Environment.getDataDirectory();
        statFs = new StatFs(pathStorageType.getPath());
        long bytes = statFs.getTotalBytes();
        return bytes;
    }

    /*
       get total number of blocks that are free on the file system, including
       reserved blocks (that are not avaiable to normal applications)

       There is a getPath method that accepts as a parameter a directory from the INTERNAL and
       EXTERNAL storage so that memory values can be returned from either with a single function.
     */
    public static long getFreeBlocksPlus(File pathStorageType) {
        //File path = Environment.getDataDirectory();
        statFs = new StatFs(pathStorageType.getPath());
        long blocks = 0;

        // check version
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) {
            blocks = statFs.getFreeBlocks();
        } else {
            blocks = statFs.getFreeBlocksLong();
        }
        return blocks;
    }

    /*
       get total number of bytes that are free on the file system, including
       reserved blocks (that are not avaiable to normal applications)

       There is a getPath method that accepts as a parameter a directory from the INTERNAL and
       EXTERNAL storage so that memory values can be returned from either with a single function.
     */
    public static long getFreeBytesPlus(File pathStorageType) {
        //File path = Environment.getDataDirectory();
        statFs = new StatFs(pathStorageType.getPath());
        long bytes = statFs.getFreeBytes();
        return bytes;
    }

    /*
       This is the formatting method for the size (in bytes) of the methods above.  Those that
       return blocks will need to be converted using the getBlockSize() prior to passing the value
       to this method.

       There are memoryUnitTypes listed at the top of this class that are needed to determine the
       type of bytes to return (i.e. B, KB, MB, GB, or TB).

       NOTE: there is a single value BLOCK that will return the block value in the memory methods
       above that return blocks.   It simply adds the verbiage " BLOCKS" to the end of the value.
     */
    public static String getAvailableMemoryFormatted(long size, int memoryUnitType, String pattern) {
        String memoryFormatted = "";
        DecimalFormat dec;

        switch (memoryUnitType) {
            case MEMORY_UNIT_KB:
                dec = new DecimalFormat(pattern);
                memoryFormatted = dec.format((size /
                        Math.pow(MEMORY_CONSTANT, MEMORY_UNIT_KB))).concat(" KB");
                break;
            case MEMORY_UNIT_MB:
                dec = new DecimalFormat(pattern);
                memoryFormatted = dec.format((size /
                        Math.pow(MEMORY_CONSTANT, MEMORY_UNIT_MB))).concat(" MB");
                break;
            case MEMORY_UNIT_GB:
                dec = new DecimalFormat(pattern);
                memoryFormatted = dec.format((size /
                        Math.pow(MEMORY_CONSTANT, MEMORY_UNIT_GB))).concat(" GB");
                break;
            case MEMORY_UNIT_TB:
                dec = new DecimalFormat(pattern);
                memoryFormatted = dec.format((size /
                        Math.pow(MEMORY_CONSTANT, MEMORY_UNIT_TB))).concat(" TB");
                break;
            case MEMORY_UNIT_BLOCK:
                dec = new DecimalFormat(pattern);
                memoryFormatted = dec.format(size).concat(" BLOCKS");
                break;
            default:
                dec = new DecimalFormat("0");
                memoryFormatted = dec.format(size).concat(" B");
                break;
        }
        return memoryFormatted;
    }

}
