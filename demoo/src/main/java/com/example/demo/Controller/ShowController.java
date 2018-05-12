package com.example.demo.Controller;

import com.example.demo.Impl.ShowRepository;
import com.example.demo.Model.Showsth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShowController {

    @Autowired
    private ShowRepository showRepository;

    /*
     * 查询所有
     */
    @GetMapping(value = "/showsths")
    public List<Showsth> showsthAll(){
        return showRepository.findAll();
    }

    /*
     * 新增
     */
    @PostMapping(value = "/showsths")
    public Showsth sthAdd(@RequestParam("name") String name, @RequestParam("age") Integer age){

        Showsth showsth = new Showsth();
        showsth.setAge(age);
        showsth.setName(name);

        return showRepository.save(showsth);
    }

    /*
     * 通过Id查询
     * 使用Query中的Like进行模糊查询
     */
    @GetMapping(value = "/show/{id}")
    public List<Showsth> showById(@PathVariable("id") Integer id){
        return showRepository.findByIdLike(id);
    }


    /*
     * 通过age查询
     */
    @GetMapping(value = "/show/age/{age}")
    public List<Showsth> showByAge(@PathVariable("age") Integer age){
        return showRepository.findByAge(age);
    }

    /*
     * 通过name模糊查询
     */
    @GetMapping(value = "/show/name/{name}")
    public List<Showsth> showByName(@PathVariable("name") String name){
        return showRepository.findByNameLike(name);
    }

    @GetMapping(value = "/show/{id}/{name}")
    public List<Showsth> showByIdAndName(@PathVariable("id") Integer id, @PathVariable("name") String name){
        return showRepository.findAllByIdAndName(id,name);
    }

    /*
     * 通过Id更新
     */

    @PutMapping(value = "/show/{id}")
    public Showsth updateById(@PathVariable("id") Integer id, @RequestParam("name") String name, @RequestParam("age") Integer age){
        Showsth showsth = new Showsth();
        showsth.setName(name);
        showsth.setAge(age);
        showsth.setId(id);
        return showRepository.save(showsth);

    }

    /*
     * 通过Id删除
    */
    @DeleteMapping(value = "/show/{id}")
    public void sthDelete(@PathVariable("id") Integer id){
        showRepository.deleteById(id);
    }
}
