package ru.dsemenko;

/**
 * A CLI app for displaying the directory tree
 *
 * @author Dmitry Semenko
 * @version 1.0
 */

public class App {
    public static void main(String[] args) {

        String pathToScan;

        if (args.length == 0) {
            System.out.println("At least one argument required.");
            System.out.println("Better it was a directory.");
            return;
        }
        /* Filename or directory may contain spaces.
           pathConstructor will collect all the arguments and consider them
           as the name of the file or directory */
        if (args.length > 1) {
            StringBuilder pathConstructor = new StringBuilder();
            pathConstructor.append(args[0]);
            for (int i = 1; i < args.length; i++) {
                pathConstructor.append('\u0020').append(args[i]);
            }
            pathToScan = pathConstructor.toString();
        } else {
            pathToScan = args[0];
        }

        try {
            PathScanner scanner = new PathScanner(pathToScan);
            scanner.scan();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Unknown error occured :(");
        }
    }
}
