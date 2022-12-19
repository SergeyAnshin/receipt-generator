package org.example.service.receipt;

import org.example.dto.ReceiptContent;
import org.example.exception.EntityNotExistsException;

import java.io.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class FileReceiptContentServiceDecorator extends ReceiptContentServiceDecorator {
    public static final String FILE_NOT_FOUND_MESSAGE = "File with arguments not found!";

    public FileReceiptContentServiceDecorator(ReceiptContentService<String[]> wrappee) {
        super(wrappee);
    }

    @Override
    public ReceiptContent getReceiptContent(String[] source) throws EntityNotExistsException {
        String[] arguments = getReceiptContentFromFileInConsoleArgument(source);
        return super.getReceiptContent(arguments);
    }

    private String[] getReceiptContentFromFileInConsoleArgument(String[] source) {
        Optional<File> fileWithArguments = getFileFromConsoleArguments(source);
        File file = fileWithArguments.orElseThrow(() -> new RuntimeException(FILE_NOT_FOUND_MESSAGE));
        String arguments = getArgumentsFromFile(file);
        return arguments.split(" ");
    }

    private Optional<File> getFileFromConsoleArguments(String[] source) {
        return Arrays.stream(source)
                .filter(Objects::nonNull)
                .map(File::new)
                .filter(File::exists)
                .findFirst();
    }

    private String getArgumentsFromFile(File file) {
        String arguments;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            arguments = bufferedReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return arguments;
    }
}
