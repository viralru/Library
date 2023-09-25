package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcLibraryRepository implements LibraryRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Library library) {
        return jdbcTemplate.update("INSERT INTO libraries (title, description, published) VALUES(?,?,?)",
                new Object[] { library.getTitle(), library.getDescription(), library.isPublished() });
    }

    @Override
    public int update(Library library) {
        return jdbcTemplate.update("UPDATE libraries SET title=?, description=?, published=? WHERE id=?",
                new Object[] { library.getTitle(), library.getDescription(), library.isPublished(), library.getId() });
    }

    @Override
    public Library findById(Long id) {
        try {
            Library library = jdbcTemplate.queryForObject("SELECT * FROM libraries WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Library.class), id);

            return library;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM libraries WHERE id=?", id);
    }

    @Override
    public List<Library> findAll() {
        return jdbcTemplate.query("SELECT * from library", BeanPropertyRowMapper.newInstance(Library.class));
    }

    @Override
    public List<Library> findByPublished(boolean published) {
        return jdbcTemplate.query("SELECT * from libraries WHERE published=?",
                BeanPropertyRowMapper.newInstance(Library.class), published);
    }

    @Override
    public List<Library> findByTitleContaining(String title) {
        String q = "SELECT * from libraries WHERE title LIKE '%" + title + "%'";

        return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(Library.class));
    }

    @Override
    public int deleteAll() {
        return jdbcTemplate.update("DELETE from libraries");
    }
}
