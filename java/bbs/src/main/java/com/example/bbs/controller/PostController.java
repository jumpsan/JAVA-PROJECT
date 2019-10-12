package com.example.bbs.controller;

import com.example.bbs.dto.PostDto;
import com.example.bbs.entity.*;
import com.example.bbs.service.PostService;
import com.example.bbs.utils.Authorization;
import com.example.bbs.utils.FileUtils;
import com.example.bbs.utils.UploadUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * (TPost)表控制层
 *
 * @author makejava
 * @since 2019-09-20 14:00:32
 */
@RestController
public class PostController {
    /**
     * 服务对象
     */
    @Resource
    private PostService postService;

    /**
     * 根据分区编号查询帖子
     *
     * @param id    分区编号
     * @param page 距离第一行的偏移量
     * @param size   行数
     * @return 帖子列表
     */
    @GetMapping("post/select/section/{id}/{page}/{size}")
    public Information<Page> selectPostBySectionId(@PathVariable Integer id, @PathVariable Integer page, @PathVariable Integer size) {
        Information<Page> information =new Information<>();
        if(id==null){
            information.setMsg("分区id不能为空");
            information.setStatus(406);
        }else{
            //总页数
            Integer totalPage=postService.selectAllPostBySectionId(id)/size+1;
            Integer start=(page-1)*size;
            List<Post> posts= postService.selectPostBySectionId(id,start, size);
            Page<Post> pageObject=new Page<>();
            pageObject.setDatas(posts);
            pageObject.setTotalPage(totalPage);
            if(posts!=null) {
                information.setData(pageObject);
                information.setMsg("帖子列表");
                information.setStatus(200);
            }else {
                information.setMsg("无");
                information.setStatus(204);
            }
        }
        return information;
    }


    /**
     * 根据用户id查询帖子
     *
     * @param id    用户id
     * @param page 距离第一行的偏移量
     * @param size   行数
     * @return 帖子列表
     */
    @GetMapping("post/select/user/{id}/{page}/{size}")
    public Information<Page> selectPostByUserId(@PathVariable Integer id, @PathVariable Integer page, @PathVariable Integer size) {
        Information<Page> information =new Information<>();
        if(id==null){
            information.setMsg("用户id不能为空");
            information.setStatus(406);
        }else{
            //总页数
            Integer totalPage=postService.selectAllPostByUserId(id)/size+1;

            Integer start=(page-1)*size;
            List<Post> posts= postService.selectPostByUserId(id,start, size);
            Page<Post> pageObject=new Page<>();
            pageObject.setDatas(posts);
            pageObject.setTotalPage(totalPage);
            if(posts!=null) {
                information.setData(pageObject);
                information.setMsg("帖子列表");
                information.setStatus(200);
            }else {
                information.setMsg("无");
                information.setStatus(204);
            }
        }
        return information;
    }

    /**
     * 根据类型查找帖子
     *
     * @param type  类型编号，0：图片或文字，1：视频
     * @param page 距离第一行的偏移量
     * @param size   行数
     * @return 帖子列表
     */
    @GetMapping("post/select/type/{type}/{page}/{size}")
    public Information<Page> selectPostByType(@PathVariable Integer type, @PathVariable Integer page,@PathVariable Integer size) {
        Information<Page> information =new Information<>();
        if(type==null){
            information.setMsg("类型不能为空");
            information.setStatus(406);
        }else{
            //总页数
            Integer totalPage=postService.selectAllPostByType(type)/size+1;

            Integer start=(page-1)*size;
            List<Post> posts= postService.selectPostByType(type,start, size);
            Page<Post> pageObject=new Page<>();
            pageObject.setDatas(posts);
            pageObject.setTotalPage(totalPage);
            if(posts!=null) {
                information.setData(pageObject);
                information.setMsg("帖子列表");
                information.setStatus(200);
            }else {
                information.setMsg("无");
                information.setStatus(204);
            }
        }
        return information;
    }

    /**
     * 根据帖子id查询
     *
     * @param id 帖子id
     * @return 帖子
     */
    @GetMapping("post/select/{id}")
    public Information<Post> selectPostById(@PathVariable Integer id) {
        Information<Post> information=new Information<>();
        if(id==null){
            information.setMsg("帖子id不能为空");
            information.setStatus(406);
        }else{
            Post post = postService.selectPostByIdForUser(id);
            if(post!=null) {
                information.setData(post);
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
     * 根据标题模糊查询
     *
     * @param title 标题
     * @param page 距离第一行的偏移量
     * @param size   行数
     * @return 帖子列表
     */
    @GetMapping("post/select/{title}/{page}/{size}")
    public Information<Page> selectPostByTile(@PathVariable String title, @PathVariable Integer page, @PathVariable Integer size) {
        Information<Page> information =new Information<>();
        if(title==null){
            information.setMsg("标题不能为空");
            information.setStatus(406);
        }else{
            //总页数
            Integer totalPage=postService.selectAllPostByTitle(title)/size+1;

            Integer start=(page-1)*size;
            List<Post> posts= postService.selectPostByTitle(title, start, size);
            Page<Post> pageObject=new Page<>();
            pageObject.setDatas(posts);
            pageObject.setTotalPage(totalPage);
            if(posts!=null) {
                information.setData(pageObject);
                information.setMsg("帖子列表");
                information.setStatus(200);
            }else {
                information.setMsg("无");
                information.setStatus(204);
            }
        }
        return information;
    }

    /**
     * 选择需要审核的帖子
     * @param sectionId
     * @param page
     * @param size
     * @return
     */
    @GetMapping("manager/post/select/check/{sectionId}/{page}/{size}")
    public Information selectCheckPostsBySection(@PathVariable Integer sectionId,@PathVariable Integer page,@PathVariable Integer size){
        Information<Page> information=new Information<>();
        if(sectionId==null || page==null || size==null){
            information.setMsg("关键信息不可为空");
            information.setStatus(406);
        }else{
            Integer start=(page-1)*size;
            List<Post> posts = postService.selectUncheckPostBySectionId(sectionId, start, size);
            Page<Post> postPage=new Page<>();
            postPage.setDatas(posts);
            Integer totalPage=postService.selectUncheckPostCountBySectionId(sectionId)/size+1;
            postPage.setTotalPage(totalPage);
            information.setMsg("审核列表");
            information.setData(postPage);
            information.setStatus(200);
        }
        return information;
    }



    /**
     * 添加图片类型帖子
     * 0 文字、图片 1 视频
     * @param postDto 帖子传输
     * @return 结果
     */
    @PostMapping("post/add/image")
    public Information addImagePost(PostDto postDto, HttpServletRequest request) {
        Information<Integer> information =new Information<>();
        String msg="";
        Integer status=202;
        Post post=postDto.getPost();
        boolean verify = Authorization.verify(request, post.getUserId());
        MultipartFile multipartFile = postDto.getMultipartFile();
        Integer fileType = FileUtils.judgeType(multipartFile);
        String newName = UploadUtils.getNewName(multipartFile);
        if(newName==null){
            msg="上传图片失败，重试";
            status=410;
        }else{
            post.setImage(newName);
            if (post.getUserId()==null || post.getSectionId()==null || post.getTitle()==null || post.getStatus()==null) {
                msg = "关键信息缺失，无法添加";
                status=406;
            }else if(!verify){
                msg="非法操作";
                status=411;
            }else if(post.getVideo()!=null || post.getImage()==null || fileType!=0){
                msg="上传内容与帖子类型不符合";
                status=409;
            }else{
                post.setType(0);
                Integer postId = postService.addImagePost(post);
                information.setData(postId);
                if(postId==-6){
                    msg = "无权发帖";
                    status=401;
                }
                else if (postId==-3) {
                    msg = "创建用户不存在";
                    status=404;
                }
                else if (postId==-4) {
                    msg = "分区被禁用，不允许操作";
                    status=405;
                }else if (postId==-5) {
                    msg = "所添加目标分区不存在";
                    status=407;
                } else if(postId==0){
                    msg="添加失败";
                    status=400;
                }
                else {
                    boolean result = UploadUtils.uploadFile(multipartFile, 0, newName);
                    if(result){
                        msg = "创建成功";
                        status=200;
                    }else{
                        postService.deletePostById(postId);
                        msg="图片上传失败";
                        status=410;
                    }
                }
            }
        }
        information.setMsg(msg);
        information.setStatus(status);
        return information;
    }

    @PostMapping("post/add/word")
    public Information addWordPost(Post post,HttpServletRequest request) {
        Information<Integer> information =new Information<>();
        String msg="";
        Integer status=202;
        boolean verify = Authorization.verify(request, post.getUserId());
        if (post.getUserId()==null || post.getSectionId()==null || post.getTitle()==null  || post.getStatus()==null) {
            msg = "关键信息缺失，无法添加";
            status=406;
        }else if(!verify){
           msg="非法操作";
           status=411;
       }else if(post.getVideo()!=null || post.getImage()!=null){
            msg="上传内容与帖子类型不符合";
            status=409;
        }else{
            post.setType(0);
            Integer postId = postService.addWordPost(post);
            information.setData(postId);
            if(postId==-6){
                msg = "无权发帖";
                status=401;
            }
            else if (postId==-3) {
                msg = "创建用户不存在";
                status=404;
            }
            else if (postId==-4) {
                msg = "分区被禁用，不允许操作";
                status=405;
            }else if (postId==-5) {
                msg = "所添加目标分区不存在";
                status=407;
            } else if(postId==0){
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


    @PostMapping("post/add/video")
    public Information addVideoPost(PostDto postDto,HttpServletRequest request) {
        Information<Integer> information =new Information<>();
        Post post = postDto.getPost();
        MultipartFile multipartFile = postDto.getMultipartFile();
        //上传视频
        String newVideoName = UploadUtils.getNewName(multipartFile);
        post.setVideo(newVideoName);
        String msg="";
        Integer status=202;
        Integer fileType = FileUtils.judgeType(multipartFile);
        boolean verify = Authorization.verify(request, post.getUserId());
        if(newVideoName==null){
            msg="视频上传失败";
            status=410;
        }else{
            if (post.getUserId()==null || post.getSectionId()==null || post.getTitle()==null  || post.getStatus()==null) {
                msg = "关键信息缺失，无法添加";
                status=406;
            }else if(!verify){
                msg="非法操作";
                status=411;
            }else if(post.getVideo()==null || post.getImage()!=null || fileType!=1){
                msg="上传内容与帖子类型不符合";
                status=409;
            }else{
                post.setType(1);
                Integer postId = postService.addVideoPost(post);
                information.setData(postId);
                if(postId==-6){
                    msg = "用户在黑名单，无权发帖";
                    status=401;
                }
                else if (postId==-3) {
                    msg = "创建用户不存在";
                    status=404;
                }
                else if (postId==-4) {
                    msg = "分区被禁用，不允许操作";
                    status=405;
                }else if (postId==-5) {
                    msg = "所添加目标分区不存在";
                    status=407;
                } else if(postId==0){
                    msg="添加失败";
                    status=400;
                }
                else {
                    boolean result = UploadUtils.uploadFile(multipartFile, 1, newVideoName);
                    if(result){
                        msg = "创建成功";
                        status=200;
                    }else{
                        //todo
                        postService.deletePostById(postId);
                        msg="视频上传失败";
                        status=410;
                    }
                }
            }
        }

        information.setMsg(msg);
        information.setStatus(status);
        return information;
    }




    /**
     * 根据帖子id删除帖子
     *
     * @param id 帖子id
     * @return 结果
     */
    @DeleteMapping("post/delete/{id}")
    public Information deletePost(@PathVariable Integer id,HttpServletRequest request) {
        Information<Integer> information =new Information<>();
        String msg="";
        Integer status=202;
        Post post = postService.selectPostById(id);
        boolean verify = Authorization.verify(request, post.getUserId());
        if(!verify){
            msg="非法操作";
            status=411;
        }else{
            String fileName="";
            Integer type=0;
            if(post.getType()==0 && post.getImage()!=null){
                fileName=post.getImage();
            }else{
                fileName=post.getVideo();
                type=1;
            }
            Integer result = postService.deletePostById(id);
            if(result>0){
                //todo 失败后的处理
                UploadUtils.deleteFile(fileName,type);
                msg="删除成功";
                status=200;
                information.setData(result);
            }
            else {
                msg = "删除失败";
                status=400;
            }
        }
        information.setStatus(status);
        information.setMsg(msg);
        return information;
    }



    /**
     * 修改帖子内容
     * 只能修改title, content
     * @param postDto 帖子
     * @return 结果
     */
    @PutMapping("post/update")
    public Information updatePost(PostDto postDto,HttpServletRequest request) {
        Information<Integer> information=new Information<>();
        Post post = postDto.getPost();
        Post checkPost= postService.selectPostById(post.getId());
        boolean verify = Authorization.verify(request, checkPost.getUserId());
        if(!verify){
            information.setMsg("非法操作");
            information.setStatus(411);
        }else{
            MultipartFile multipartFile = postDto.getMultipartFile();

            post.setType(checkPost.getType());//类型不可更改
            if(post.getId()==null){
                information.setMsg("帖子id不能为空");
                information.setStatus(403);
            }else {
                if(multipartFile!=null){
                    //判断文件类型
                    Integer fileType = FileUtils.judgeType(multipartFile);
                    if((fileType==0 && post.getType()!=0)  || (fileType==1 && post.getType()!=1) || fileType==-1){
                        information.setStatus(409);
                        information.setMsg("类型不符合");
                        return information;
                    }
                }
                Integer re=postService.updatePost(post);
                if(re==null || re==0){
                    information.setMsg("更新失败");
                    information.setStatus(400);
                }else{
                    //删除原来的文件
                    if(checkPost.getType()==0 && checkPost.getImage()!=null){
                        UploadUtils.deleteFile(checkPost.getImage(),0);
                    }else{
                        UploadUtils.deleteFile(checkPost.getVideo(),1);
                    }
                    information.setMsg("更新成功");
                    information.setStatus(200);
                }
                information.setData(re);
            }
        }
        return information;
    }


}