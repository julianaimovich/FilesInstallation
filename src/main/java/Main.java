import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        String rootDir = "C://Games";
        List<String> logs = new ArrayList<>();

        File srcDirectory = new File(rootDir + "/src");
        File resDirectory = new File(rootDir + "/res");
        File saveGamesDirectory = new File(rootDir + "/savegames");
        File tempDirectory = new File(rootDir + "/temp");
        logs.add(createDirectory(srcDirectory));
        logs.add(createDirectory(resDirectory));
        logs.add(createDirectory(saveGamesDirectory));
        logs.add(createDirectory(tempDirectory));

        File mainDirectory = new File(srcDirectory.getPath() + "/main");
        File testDirectory = new File(srcDirectory.getPath() + "/test");
        logs.add(createDirectory(mainDirectory));
        logs.add(createDirectory(testDirectory));

        File mainFile = new File(mainDirectory, "Main.java");
        File utilsFile = new File(mainDirectory, "Utils.java");
        logs.add(createFile(mainFile));
        logs.add(createFile(utilsFile));

        File drawablesDirectory = new File(resDirectory.getPath() + "/drawables");
        File vectorsDirectory = new File(resDirectory.getPath() + "/vectors");
        File iconsDirectory = new File(resDirectory.getPath() + "/icons");
        logs.add(createDirectory(drawablesDirectory));
        logs.add(createDirectory(vectorsDirectory));
        logs.add(createDirectory(iconsDirectory));

        File tempFile = new File(tempDirectory, "temp.txt");
        logs.add(createFile(tempFile));
        saveLogs(tempFile, logs);
    }

    public static String createDirectory(File file) {
        String directoryCreated = "Директория " + file.getName() + " не создана";
        if (file.mkdir()) {
            directoryCreated = "Директория " + file.getName() + " была создана";
        }
        return directoryCreated;
    }

    public static String createFile(File file) {
        String fileCreated = null;
        try {
            if (file.createNewFile()) {
                fileCreated = "Файл " + file.getName() + " был создан";
            }
        } catch (IOException ex) {
            fileCreated = ex.getMessage();
        }
        return fileCreated;
    }

    public static void saveLogs(File logsFile, List<String> messages) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String message : messages) {
            stringBuilder.append(message);
            stringBuilder.append(System.getProperty("line.separator"));
        }
        String logs = stringBuilder.toString();

        try (FileWriter writer = new FileWriter(logsFile.getPath())) {
            writer.write(logs);
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}