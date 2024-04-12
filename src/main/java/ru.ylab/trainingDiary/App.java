package ru.ylab.trainingDiary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

/**
 * Запуск приложения и меню
 */
public class App {
    private static final Logger LOGGER = Logger.getLogger( App.class.getName() );
    public static void main(String[] args) throws IOException {
        LOGGER.info("Запуск приложения");
        //Добавляем стандартные типы тренировок
        Training.newType("Силовая");
        Training.newType("Йога");
        Training.newType("Кардио");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Добро пожаловать");
        System.out.println();
        User.initAdmin();
        boolean isLogin = true;
        while (true) {
            try {
                System.out.println("Меню:");
                System.out.println("1 - Авторизация 2 - Регистрация 3 - Закрыть");
                System.out.println("Пожалуйста, введите ваш выбор");
                int choice = Integer.parseInt(reader.readLine());

                switch (choice) {
                    //Авторизация
                    case 1:
                        System.out.println("Авторизация");
                        System.out.println();
                        System.out.println("Введите логин");
                        String login = reader.readLine();
                        System.out.println("Введите пароль");
                        String password = reader.readLine();
                        boolean isValid = User.validateUser(login, password);
                        if (isValid) {
                            System.out.println("Вход выполнен");
                            System.out.println();
                            isLogin = true;
                            while (isLogin) {
                                System.out.println("Меню:");
                                System.out.println("1 - Добавить тренировку 2 - Изменить тренировку " +
                                        "3 - Удалить тренировку 4 - Посмотреть все тренировки");
                                System.out.println("5 - Добавить новый тип тренировки " +
                                        "6 - Посмотреть статистику 7 - Смена пользователя 8 - Закрыть");
                                System.out.println("Пожалуйста, введите ваш выбор");
                                int choice2 = Integer.parseInt(reader.readLine());
                                switch (choice2) {
                                    //Добавить тренировку
                                    case 1:
                                        Training.create();
                                        isLogin = true;
                                        break;

                                    //Изменить тренировку
                                    case 2:
                                        Training.update();
                                        isLogin = true;
                                        break;

                                    //Удалить тренировку
                                    case 3:
                                        Training.delete();
                                        isLogin = true;
                                        break;

                                    //Показать все тренировки
                                    case 4:
                                        Training.allTraining();
                                        isLogin = true;
                                        break;

                                    //Добавить новый вид тренировки
                                    case 5:
                                        Training.newType();
                                        isLogin = true;
                                        break;

                                    //Показать статистику
                                    case 6:
                                        Training.statistic();
                                        isLogin = true;
                                        break;

                                    //Смена пользователя
                                    case 7:
                                        System.out.println("Смена пользователя");
                                        System.out.println();
                                        isLogin = false;
                                        break;

                                    case 8:
                                        System.out.println("До свидания");
                                        System.exit(0);
                                        break;

                                    default:
                                        System.out.println("Неверный ввод");
                                        System.out.println();
                                        break;
                                }
                            }
                            break;
                        } else {
                            System.out.println("Неверный логин или пароль");
                            System.out.println("--------------------------");
                            break;
                        }

                    //Регистрация
                    case 2:
                        User.register();
                        break;

                    //Закрыть
                    case 3:
                        System.out.println("До свидания");
                        reader.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Неверный ввод");
                        System.out.println();
                }
            } catch (NumberFormatException | IOException e) {
                System.out.println("Неверный ввод");
                System.out.println();

            }
        }
    }
}

