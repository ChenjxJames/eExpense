package cn.chenjianxiong.expense.controller;

import cn.chenjianxiong.expense.po.Branch;
import cn.chenjianxiong.expense.po.Result;
import cn.chenjianxiong.expense.service.BranchService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author ：James Chen
 * @date ：Created in 2019/5/26 15:27
 * @description：部门管理
 * @modified By：
 * @version: 1.0$
 */
@RequestMapping(value = "/branch")
@Controller
public class BranchController {

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result add(@RequestBody Branch branch){
        Result result = new Result(-1, "部门添加失败。");
        try{
            ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
            BranchService branchService = ctx.getBean(BranchService.class);

            if (branchService.addBranch(branch)) {
                result.setState(0);
                result.setInfo("部门添加成功。");
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setState(-1);
            result.setInfo("服务器错误，请稍后尝试。");
        }
        return result;
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Result update(@RequestBody Branch branch){
        Result result = new Result(-1, "部门信息更改失败。");
        try{
            ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
            BranchService branchService = ctx.getBean(BranchService.class);

            if (branchService.updateBranch(branch)) {
                result.setState(0);
                result.setInfo("部门信息更改成功。");
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setState(-1);
            result.setInfo("服务器错误，请稍后尝试。");
        }
        return result;
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public Result del(@RequestBody Map<String, String> map){
        Result result = new Result(-1, "部门删除失败，请检查该部门是否留有员工。");
        try{
            String branchId = map.get("branchId");

            ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
            BranchService branchService = ctx.getBean(BranchService.class);

            if (branchService.delBranch(branchId)) {
                result.setState(0);
                result.setInfo("部门删除成功。");
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setState(-1);
            result.setInfo("服务器错误，请稍后尝试。");
        }
        return result;
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    @ResponseBody
    public List<Branch> show(){
        List<Branch> branchList = null;
        try{
            ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
            BranchService branchService = ctx.getBean(BranchService.class);
            branchList = branchService.findAllBranch();
        }catch (Exception e){
            e.printStackTrace();
        }
        return branchList;
    }

    @RequestMapping(value = "/getInfo/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Branch getBranchById(@PathVariable("id") String branchId){
        Branch branch = null;
        try{
            ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
            BranchService branchService = ctx.getBean(BranchService.class);
            branch = branchService.findBranchById(branchId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return branch;
    }
}
