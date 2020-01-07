package ru.mail.romanov1234567890987;


import ru.mail.romanov1234567890987.repository.model.Car;
import ru.mail.romanov1234567890987.repository.model.CarModelsNamesEnum;
import ru.mail.romanov1234567890987.service.HomeworkService;
import ru.mail.romanov1234567890987.service.impl.HomeworkServiceImpl;
import ru.mail.romanov1234567890987.util.RandomUtil;

public class App {
    public static void main(String[] args) {
        HomeworkService homeworkService = new HomeworkServiceImpl();
        //homeworkService.runFirstTask();

        //homeworkService.addTenCars();
        //homeworkService.showCars();
        //homeworkService.deleteCars();
        homeworkService.countCars();
    }
}
