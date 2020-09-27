package com.wangtk.security.visitor;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class VistorTest {
    public static void main(String[] args) throws IOException {







        Files.walkFileTree(Paths.get("."), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println("visit " + dir.getFileName());
                return super.preVisitDirectory(dir, attrs);
            }
        });

    }
}
