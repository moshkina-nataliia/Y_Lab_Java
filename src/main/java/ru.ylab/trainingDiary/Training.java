package ru.ylab.trainingDiary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Класс Training предоставляет объекты Тренировок,
 * создает список тренировок,
 * содержит методы для работы с тренировками
 */
public class Training implements Comparable<Training> {
    String type;
    public Date date;
    public int duration; //Длительность
    public int caloriesBurned;
    public User user;
    public static List<String> typeList = new ArrayList<>();
    public static List<Training> trainings = new ArrayList<>();
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public String toString() {
        SimpleDateFormat date1 = new SimpleDateFormat("dd.MM.yyyy");
        String resultDate = date1.format(date);
        return "Тип: " + type +
                ", дата: " + resultDate +
                ", Длительность: " + duration +
                ", Сожженные калории: " + caloriesBurned;
    }

    public Training(String type, Date date, int duration, int caloriesBurned) {
        this.type = type;
        this.date = date;
        this.duration = duration;
        this.caloriesBurned = caloriesBurned;
    }

    public Training(String type, Date date, int duration, int caloriesBurned, User user) {
        this.type = type;
        this.date = date;
        this.duration = duration;
        this.caloriesBurned = caloriesBurned;
        this.user = user;
    }

    /**
     * Метод newType создает новый тип тренировки и добавляет в спислк типов typeList
     *
     * @param newType
     */
    public static void newType(String newType) {
        boolean isNewType = true;
        for (int i = 0; i < typeList.size(); i++) {
            if (typeList.get(i).equals(newType)) {
                isNewType = false;
                break;
            }
        }
        if (isNewType) {
            typeList.add(newType);
        }
    }

    public static void newType() {
        try {
            System.out.println("Внесены следующие типы тренировок:");
            for (String type : typeList) {
                System.out.println(type);
            }
            System.out.println();
            System.out.println("Введите новый тип тренировки");
            String newType = reader.readLine();
            boolean isNewType = true;
            for (int i = 0; i < typeList.size(); i++) {
                if (typeList.get(i).equals(newType)) {
                    isNewType = false;
                    break;
                }
            }
            if (isNewType) {
                typeList.add(newType);
                System.out.println("Добавлен новый тип тренировки: " + newType);
                System.out.println();
            } else {
                System.out.println("Такой тип тренировки уже создан");
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("Неверный ввод");
        }
    }

    public static void create() {
        try {
            System.out.println("Добавлнение тренировки");
            System.out.println();
            System.out.println("Введите дату в формате dd.mm.yyyy");
            String strDate = reader.readLine();
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            Date newDate = format.parse(strDate);
            System.out.println("Выберите тип");
            int i = 0;
            for (String type : typeList) {
                i++;
                System.out.println(i + " - " + type);
            }
            int choiceType = Integer.parseInt(reader.readLine());
            System.out.println("Введите длительность");
            int duration = Integer.parseInt(reader.readLine());
            System.out.println("Введите количество сожженных калорий");
            int calloriesburned = Integer.parseInt(reader.readLine());
            boolean isExist = false;
            for (int j = 0; j < trainings.size(); j++) {
                if ((trainings.get(j).date.compareTo(newDate) == 0
                        && trainings.get(j).type.equals(typeList.get(choiceType - 1)))) {
                    isExist = true;
                    break;
                }
            }
            if (isExist) {
                System.out.println("Тренировка такого вида сегодня уже была добавлена");
                System.out.println();
            } else {
                Training training = new Training(typeList.get(choiceType - 1), newDate, duration, calloriesburned, User.getCurrentUser());
                trainings.add(training);
                System.out.println("Тренировка добавлена");
                System.out.println();
            }
        } catch (
                NumberFormatException | IOException e) {
            System.out.println("Неверный ввод");
        } catch (
                IndexOutOfBoundsException e) {
            System.out.println("Тип тренировки выбран неккоректно");
        } catch (ParseException e) {
            System.out.println("Введен неверный формат даты");
        }

    }

    public static void update() throws IOException {
        try {
            System.out.println("Изменение тренировки");
            System.out.println();
            System.out.println("Введите номер тренировки для изменения");
            allTraining();
            int index = Integer.parseInt(reader.readLine());
            System.out.println("Введите дату в формате dd.mm.yyyy");
            String strData = reader.readLine();
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            Date date = format.parse(strData);
            System.out.println("Выберите тип");
            int i = 0;
            for (String type : typeList) {
                i++;
                System.out.println(i + " - " + type);
            }
            int choiceType = Integer.parseInt(reader.readLine());
            System.out.println("Введите длительность");
            int duration = Integer.parseInt(reader.readLine());
            System.out.println("Введите количество сожженых калорий");
            int calloriesburned = Integer.parseInt(reader.readLine());
            Training training = new Training(typeList.get(choiceType - 1), date, duration, calloriesburned);
            trainings.set(index - 1, training);
            System.out.println("Тренировка успешно изменена");
            System.out.println();
        } catch (ParseException e) {
            System.out.println("Неверный формат даты");
        }
    }

    public static void delete() throws IOException {
        System.out.println("Укажите номер тренировки, которую хотите удалить");
        allTraining();
        int choice = Integer.parseInt(reader.readLine());
        trainings.remove(choice - 1);
        System.out.println("Тренировка удалена");
    }

    public static void allTraining() {
        System.out.println("Список тренировок, отсортированные по дате:");
        User currentUser = User.getCurrentUser();
        if (trainings.size() == 0) {
            System.out.println("Пусто. Добавьте первую тренировку");
        }
        int i = 0;

        Collections.sort(trainings);

        for (Training training : trainings) {
            if ((training.user.equals(currentUser)) || (currentUser.isAdmin())) {
                i++;
                System.out.println(i + ". " + training);
            }
        }
        System.out.println();
    }

    public static void statistic() {
        System.out.println("Статистика:");
        System.out.println("Всего тренировок: " + trainings.size());
        int sumDuration = 0;
        int sumCaloriesBurned = 0;
        for (int i = 0; i < trainings.size(); i++) {
            sumDuration += trainings.get(i).duration;
            sumCaloriesBurned += trainings.get(i).caloriesBurned;
        }
        System.out.println("Длительность всех тренировок: " + sumDuration);
        System.out.println("Сумма потраченных каллорий: " + sumCaloriesBurned);
        System.out.println();
    }

    //
    @Override
    public int compareTo(Training training) {
        return getDate().compareTo(training.getDate());
    }

    public Date getDate() {
        return date;
    }
}