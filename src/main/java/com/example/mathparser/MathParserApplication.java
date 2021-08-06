package com.example.mathparser;

import com.example.mathparser.exceptions.InvalidEquationFormatException;
import com.example.mathparser.exceptions.UnknownOperationException;
import com.example.mathparser.service.CalculationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.nio.file.DirectoryIteratorException;
import java.util.Scanner;

@SpringBootApplication
public class MathParserApplication implements CommandLineRunner {

    @Value("${spring.profiles.active}")
    private String activeProfile;

    private final CalculationService calculationService;

    public MathParserApplication(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MathParserApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.printf("Current profile: %s%n", activeProfile);

        if (args.length > 0) {
            System.out.println("################# CALCULATION ############");
            System.out.printf("Your input: %s%n", args[0]);

            final BigDecimal result = calculationService.calculate(args[0]);
            System.out.printf("Result: %s%n", result.toString());

            final Scanner scan = new Scanner(System.in);

            while (true) {
                System.out.println("Put next expression...");
                try {
                    final String input = scan.nextLine();
                    System.out.printf("Result: %s%n", calculationService.calculate(input).toString());
                } catch (DirectoryIteratorException e) {
                    System.out.println("Cannot divide by 0");
                } catch (InvalidEquationFormatException e) {
                    System.out.println("Invalid equation format");
                } catch (UnknownOperationException e) {
                    System.out.println("Unknown operation");
                }
            }

        }
    }
}
