package com.example.bbs.controller;

import com.example.bbs.entity.*;
import com.example.bbs.service.SectionService;
import com.example.bbs.utils.Authorization;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * (TSection)表控制层
 *
 * @author makejava
 * @since 2019-09-20 14:01:07
 */
@RestController
public class SectionController {
    /**
     * 服务对象
     */
    @Resource
    private SectionService sectionService;

    /**
     * 查询某个板块下的分区
     *
     * @param id 板块编号
     * @return 分区列表
     */
    @GetMapping("section/select/plate/{id}/{page}/{size}")
    public Information selectSectionByPlateId(@PathVariable("id") Integer id,@PathVariable Integer page,@PathVariable Integer size) {
        Information<Page> information =new Information<>();
        if(id==null){
            information.setMsg("板块id不能为空");
            information.setStatus(406);
        }else{
            //总页数
            Integer totalPage=sectionService.selectAllSectionByPlateId(id)/size+1;

            Integer start=(page-1)*size;
            List<Section> sections= sectionService.selectSectionByPlateId(id,start, size);
            Page<Section> pageObject=new Page<>();
            pageObject.setDatas(sections);
            pageObject.setTotalPage(totalPage);
            if(sections!=null) {
                information.setData(pageObject);
                information.setMsg("分区列表");
                information.setStatus(200);
            }else {
                information.setMsg("无");
                information.setStatus(204);
            }
        }
        return information;
    }

    /**
     * 查询某个分区
     * @param id
     * @return
     */
    @GetMapping("section/select/{id}")
    public  Information selectSectionById(@PathVariable("id") Integer id){
        Information<Section> information=new Information<>();
        if(id==null){
            information.setMsg("板块id不能为空");
            information.setStatus(406);
        }else{
            Section section = sectionService.selectSectionById(id);
            if(section!=null) {
                information.setData(section);
                information.setStatus(200);
                information.setMsg("分区");
            }else {
                information.setMsg("无");
                information.setStatus(204);
            }
        }
        return information;
    }

    /**
     * 添加分区
     *
     * @param section 分区
     * @return 主键值，分区编号
     */
    @PostMapping("manager/section/add")
    public Information<Integer> addSection(Section section, HttpServletRequest request) {
        Information<Integer> information =new Information<>();
        String msg="";
        Integer status=202;
        boolean verify = Authorization.verify(request, section.getUserId());
        if (section.getUserId()==null && section.getName()!=null && section.getPlateId()==null) {
            msg = "创建用户或板块名字或分区名为空";
            status=406;
        }else if(!verify){
            msg="非法操作";
            status=411;
        }else{
            Integer sectionId = sectionService.addSection(section);
            information.setData(sectionId);
            if(sectionId==-2){
                msg="名称重复";
                status=402;
            }
            else if (sectionId==-3) {
                msg = "创建用户不存在";
                status=404;
            }
            else if (sectionId==-4) {
                msg = "板块被禁用，不允许操作";
                status=405;
            }else if (sectionId==-5) {
                msg = "所添加目标板块不存在";
                status=407;
            }
            else if(sectionId==0){
                msg="添加失败";
                status=400;
            }
            else {
                msg = "创建成功";
                status=200;
            }
        }
        information.setMsg(msg);
        information.setStatus(status);
        return information;
    }

    /**
     * 根据分区编号删除分区
     *
     * @param id 分区编号
     * @return 结果
     */
    @DeleteMapping("manager/section/delete/{id}")
    public Information deleteSectionById(@PathVariable Integer id) {
        Information<Integer> information =new Information<>();
        String msg="";
        Integer status=202;
        Integer result = sectionService.deleteSectionById(id);
        if(result>0){
            msg="删除成功";
            status=200;
        }
        else {
            msg = "删除失败";
            status=400;
        }
        information.setData(result);
        information.setStatus(status);
        information.setMsg(msg);
        return information;
    }

    /**
     * 修改分区
     *
     * @param section 分区
     * @return 结果
     */
    @PutMapping("manager/section/update")
    public Information updateSection(Section section) {
        Information<Integer> information=new Information<>();
       if(section.getId()==null){
            information.setMsg("分区id不能为空");
            information.setStatus(403);
        }else {
            Integer re=sectionService.updateSection(section);
            if(re==null || re==0){
                information.setMsg("更新失败");
                information.setStatus(400);
            }else if(re==-2){
                information.setMsg("名称重复");
                information.setStatus(402);
            } else{
                information.setMsg("更新成功");
                information.setStatus(200);
            }
            information.setData(re);
        }
        return information;
    }

//    /**
//     *
//     * @param id
//     * @param status
//     * @return
//     */
//    @PutMapping("enable/{id}")
//    public Information enableSection(@PathVariable Integer id,Integer status){
//        Section section=new Section();
//        section.setId(id);
//        section.setStatus(status);
//        Integer re=sectionService.updateSection(section);
//        Information<Integer> information=new Information<>();
//        information.setData(re);
//        if(re==null || re<=0){
//            information.setMsg("修改失败");
//            information.setStatus(400);
//        }else{
//            information.setMsg("修改成功");
//            information.setStatus(200);
//        }
//        return information;
//
//    }
}