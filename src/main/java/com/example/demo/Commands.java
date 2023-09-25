package com.example.demo;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.jdbc.core.JdbcTemplate;

public class Commands {

    public static void Run()
    {
        JdbcLibraryRepository libraryCommandController = new JdbcLibraryRepository();
        Scanner in = new Scanner(System.in);
        System.out.print("Input command: ");
        String command = in.nextLine();
        if(command.contains("add"))
        {
            Library library = new Library("123","Library",true);
            libraryCommandController.findAll();

        }
        else if (command.contains("create"))
        {
            System.out.println("lol");
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
