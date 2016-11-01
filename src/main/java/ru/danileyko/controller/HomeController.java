package ru.danileyko.controller;

import org.hibernate.internal.HEMLogging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import ru.danileyko.dao.HelloDAO;
import ru.danileyko.model.Hello;
import ru.danileyko.service.HelloService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by danil on 27.10.2016.
 */
@Controller
public class HomeController {

    @Autowired
    private HelloService helloService;

    @Autowired
    @Qualifier(value = "helloService")
    public void setHelloService(HelloService helloService) {
        this.helloService = helloService;
    }
    //Вывод формы. При обращении к /  устанавливается представление index.jsp в привязкой к модели Hello.
    //Добавлен вывод соержимого таблицы CONTACTS в представление index.
    @RequestMapping(value = "/" , method = RequestMethod.GET)
    public ModelAndView main()
    {
        List<Hello> list = helloService.list();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("fromForm",new Hello()); //Создание объекта типа Hello.
        modelAndView.addObject("contactsList",list);
        modelAndView.setViewName("index");
        return modelAndView;
    }
    //Обработка формы. Установка представления result.jsp. Отображение данных на странице /outputForm
    @RequestMapping(value = "/outputForm")
    public ModelAndView takeName(@ModelAttribute("fromForm") Hello hello)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("result");
        modelAndView.addObject("fromForm",hello);
        helloService.addContact(hello);
        return  modelAndView;
    }
    //Удаление записи из БД. После удаления возврат на главную страницу.
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public ModelAndView removeContact(HttpServletRequest request)
    {
        int contactId = Integer.parseInt(request.getParameter("id"));
        helloService.removeContact(contactId);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public ModelAndView updateContact(@RequestParam("id") Integer id, HttpServletRequest request)
    {
        Hello contact = helloService.getContactById(id);
        helloService.updateContact(contact);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("fromForm",contact);
        return modelAndView;
    }
}

