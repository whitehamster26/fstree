package ru.dsemenko;

import java.io.File;
import java.util.*;

/**
 * PathScanner uses java.io.File to search for files and directories in the specified path.
 *
 * @since 1.0
 */

public class PathScanner {
    private final File path;
    private int layer = 0;

    /**
     * Constructor takes path from CLI arguments and checks if it directory.
     * If not, it throws IllegalArgumentException.
     *
     * @param path - file path from CLI
     * @throws IllegalArgumentException
     */
    PathScanner(String path) throws IllegalArgumentException {
        this.path = new File(path);
        if (!this.path.isDirectory()) {
            throw new IllegalArgumentException("The argument is not a directory.");
        }
    }

    /**
     * Private constructor for recursive calls
     *
     * @param path - file path from CLI
     * @param layer - nesting level of the directory
     * @throws IllegalArgumentException
     * @see PathScanner#scan()
     */
    private PathScanner(String path, int layer) throws IllegalArgumentException {
        this(path);
        this.layer = layer;
    }

    private File[] getLayer() {
        File[] files = path.listFiles();
        // Directories should appear first.
        Arrays.sort(files, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                int file1 = o1.isDirectory() ? 1 : 0;
                int file2 = o2.isDirectory() ? 1 : 0;
                return file2 - file1;

            }

            ;
        });
        return files;
    }

    /**
     * Public method that recursively scans directories and sends information
     * to the static FilesTreeBuilder.showFileName method that prints out the file names.
     *
     * @see FilesTreeBuilder#showFileName(int, String)
     */
    public void scan() {
        File[] files = getLayer();
        for (File file : files) {
            FilesTreeBuilder.showFileName(layer, file.getName());
            if (file.isDirectory()) {
                PathScanner scanner = new PathScanner(file.getAbsolutePath(), layer + 1);
                scanner.scan();
            }
        }
    }
}
