package com.threadmanager;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Manager {
    private static final int SIZE = 5;
    private Process[] processes = new Process[SIZE];
    private boolean exitFlag = false;
    private ExecutorService executors = null;

    public Manager() {

    }

    public void start() {
        printInterface();
        do {
            int code = readCode();
            switch (code) {
                case 1: {
                    printInterface();
                    continue;
                }
                case 2: {
                    runAllThreads();
                    break;
                }
                case 3: {
                    runAllThreads();
                    break;
                }
                case 4: {
                    runAllThreads();
                    switchExitFlag();
                    break;
                }
                case 5: {
                    switchExitFlag();
                    System.exit(0);
                }
                default: {
                    printErrorMessage("Пункта меню под номером \'" + code + "\' не существует!");
                }
            }
            printProcessesStatus();
        } while (!exitFlag);
    }

    private int readCode() {
        int code = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите номер пункта: ");
        try {
            code = scanner.nextInt();
        } catch (InputMismatchException ex) {
            printErrorMessage("Неверный формат вводимых данных. Введите целое положительное число!");
            readCode();
        }
        return code;
    }

    private void printInterface() {
        System.out.println("|----------------------------------------|");
        System.out.println("| 1. Вывести меню                        |");
        System.out.println("| 2. Запуск всех пяти дочерних потоков   |");
        System.out.println("| 3. Повторить                           |");
        System.out.println("| 4. Повторить и выйти                   |");
        System.out.println("| 5. Выйти из программы                  |");
        System.out.println("|----------------------------------------|");
    }

    private void prepareThreads() {
        for (int thread = 0; thread < SIZE; thread++) {
            processes[thread] = new Process("Child thread - " + (thread + 1));
        }
        executors = Executors.newFixedThreadPool(SIZE);
    }

    private void runAllThreads() {
        prepareThreads();
        executors.execute(processes[0]);
        executors.execute(processes[1]);
        executors.execute(processes[2]);
        executors.execute(processes[3]);
        executors.execute(processes[4]);
        executors.shutdown();
        do {
        } while (!executors.isTerminated());
    }

    private void switchExitFlag() {
        exitFlag = !exitFlag;
    }

    private void printErrorMessage(String message) {
        System.err.println(message);
    }

    private void printProcessesStatus() {
        String statusInProcess = "|Processing|";
        String statusCompleted = "|Completed|";
        for (int thread = 0; thread < SIZE; thread++) {
            Process process = processes[thread];
            if (process.isAlive())
                System.out.println(statusInProcess + process.getName());
            else
                System.err.println(statusCompleted + process.getName());
        }
    }
}
