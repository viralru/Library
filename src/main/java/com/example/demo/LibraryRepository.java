package com.example.demo;

import java.util.List;

public interface LibraryRepository {
    int save(Library book);

    int update(Library book);

    Library findById(Long id);

    int deleteById(Long id);

    List<Library> findAll();

    List<Library> findByPublished(boolean published);

    List<Library> findByTitleContaining(String title);

    int deleteAll();
}