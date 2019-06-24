package com.example.taurus.web.controller.core;

import cn.hutool.core.text.StrBuilder;

public abstract class BaseController {
    /**
     *
     * @param pagaName
     * @return
     */
    public static  String THEME="anatole";
    public String render(String pagaName) {
        final StrBuilder themStr =new StrBuilder("themes/");
        themStr.append(THEME);
        themStr.append("/");
        return themStr.append(pagaName).toString();
    }

    /**
     * 渲染404页面
     * @return redirect:/404
     */
    public String renderNotFound(){return "redirect:/404";}
}
