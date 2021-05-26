package ru.dsemenko;

/**
 * Class that displays the file name provided by PathScanner.
 *
 * @see PathScanner#scan()
 * @since 1.0
 */
public class FilesTreeBuilder {
    private static final String SEPARATOR = "\u0020\u0020\u0020\u0020";

    /**
     * Static method that prints out file names from PathScanner
     *
     * @param layer - nesting level of the file.
     * @param name  - file name.
     */
    static void showFileName(int layer, String name) {
        if (layer > 0) {
            for (int i = 0; i < layer + 1; i++) {
                System.out.print(SEPARATOR);
            }
        }
        System.out.println(SEPARATOR + name);
    }

}
