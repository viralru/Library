package com.example.demo;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component

public class Commands {


    private final JdbcLibraryRepository jdbcLibraryRepository;
    public Commands(JdbcLibraryRepository jdbcLibraryRepository){
        this.jdbcLibraryRepository = jdbcLibraryRepository;
    }
    public void run()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Input command: ");
        String command = in.nextLine();
        if(command.contains("add"))
        {
            Library library = new Library("1","2",true);
            jdbcLibraryRepository.save(library);
        }
        else if (command.contains("create"))
        {

        }
        else if (command.contains("delete"))
        {
            System.out.println("net");
        }
        else if (command.contains("find"))
        {
            System.out.println("net");
        }
        in.close();
    }

}
