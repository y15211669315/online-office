package com.onlineoffice.api;

import com.zhuozhengsoft.pageoffice.FileSaver;
import com.zhuozhengsoft.pageoffice.OpenModeType;
import com.zhuozhengsoft.pageoffice.PageOfficeCtrl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Description :Office在线编辑、预览API
 * ---------------------------------
 * @Author : SG.Y
 * @Date : 2019/5/14 16:36
 */
@Controller
public class OfficeOnlineApi {

    @Value("${posyspath}")
    private String filePath;

    /**
     * 在线word文档编辑
     *
     * @param request
     * @param map
     * @return
     */
    @GetMapping("/word")
    public String showWord(HttpServletRequest request, Map<String, Object> map) {
        PageOfficeCtrl poCtrl = new PageOfficeCtrl(request);
        //设置授权程序servlet
        poCtrl.setServerPage("/poserver.zz");
        //添加自定义按钮
        poCtrl.addCustomToolButton("保存", "Save", 1);
        //保存接口地址
        poCtrl.setSaveFilePage("/save");
        //打开文件(打开的文件类型由OpenModeType决定，docAdmin是一个word，并且是管理员权限，如果是xls文件，则使用openModeType.xls开头的,其他的office格式同等)，最后一个参数是作者
        // TODO 这里有个坑，这里打开的文件是本地的，地址如果写成/结构的路径，页面就会找不到文件，会从http://xxxxx/G/id...去找，写成\\就是从本地找
        poCtrl.webOpen("G:\\ideaProject\\springboot-demo\\online-office\\src\\main\\resources\\file\\test.docx", OpenModeType.docAdmin, "光哥");
        //pageoffice 是文件的变量，前端页面通过这个变量加载出文件
        map.put("pageoffice", poCtrl.getHtmlCode("PageOfficeCtrl1"));
        //跳转到word.html
        return "word";
    }

    /**
     * 在线ppt文档编辑
     *
     * @param request
     * @param map
     * @return
     */
    @GetMapping("/ppt")
    public String showPPT(HttpServletRequest request, Map<String, Object> map) {
        PageOfficeCtrl poCtrl = new PageOfficeCtrl(request);
        //设置授权程序servlet
        poCtrl.setServerPage("/poserver.zz");
        //添加自定义按钮
        poCtrl.addCustomToolButton("保存", "Save", 1);
        //保存接口地址
        poCtrl.setSaveFilePage("/save");
        //打开文件(打开的文件类型由OpenModeType决定，docAdmin是一个word，并且是管理员权限，如果是xls文件，则使用openModeType.xls开头的,其他的office格式同等)，最后一个参数是作者
        // TODO 这里有个坑，这里打开的文件是本地的，地址如果写成/结构的路径，页面就会找不到文件，会从http://xxxxx/G/id...去找，写成\\就是从本地找
        poCtrl.webOpen("G:\\ideaProject\\springboot-demo\\online-office\\src\\main\\resources\\file\\test.pptx", OpenModeType.pptNormalEdit, "光哥");
        //pageoffice 是文件的变量，前端页面通过这个变量加载出文件
        map.put("pageoffice", poCtrl.getHtmlCode("PageOfficeCtrl1"));
        //跳转到word.html
        return "ppt";
    }

    /**
     * 保存文件接口
     *
     * @param request
     * @param response
     */
    @RequestMapping("/save")
    public void saveFile(HttpServletRequest request, HttpServletResponse response) {
        // 保存修改后的文件
        FileSaver fs = new FileSaver(request, response);
        fs.saveToFile("G:\\test\\在线编辑\\" + fs.getFileName());
        fs.close();
    }

}
